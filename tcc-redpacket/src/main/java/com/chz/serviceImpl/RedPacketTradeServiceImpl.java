package com.chz.serviceImpl;
import com.chz.dao.RedPacketTradeOrderMapper;
import com.chz.dto.RedPacketTradeOrderDto;
import com.chz.entity.RedPacketAccount;
import com.chz.entity.RedPacketTradeOrder;
import com.chz.service.RedPacketService;
import com.chz.service.RedPacketTradeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * @ClassName : CashTradeServiceImpl
 * @Description : 现金账户交易接口实现类
 * @Author : 陈寒哲
 * @Date: 2020-06-01 09:39
 */
@Service
public class RedPacketTradeServiceImpl implements RedPacketTradeService {
    @Resource
    private RedPacketTradeOrderMapper redPacketTradeOrderMapper;
    @Resource
    private RedPacketService redPacketService;

    @Transactional
    @Override
    public void remainRedPacketTradeResource(RedPacketTradeOrderDto redPacketTradeOrder) {
        // 通过订单ID 找到红包账户是否支付过该订单，最多只能有一条记录
        String orderId = redPacketTradeOrder.getMerchantOrderNo();
        RedPacketTradeOrder tradeOrder = redPacketTradeOrderMapper.findByOrderId(orderId);
        //保证幂等
        if (null == tradeOrder){
            RedPacketTradeOrder newRedPacketTradeOrder = new RedPacketTradeOrder(
                    redPacketTradeOrder.getSelfUserId(),
                    redPacketTradeOrder.getOppositeUserId(),
                    orderId,
                    redPacketTradeOrder.getAmount()
            );

            // TRY预留资源
            try{
                // 事务一:插入红包交易记录(RED_TRADE_ORDER)，状态为DRAFT
                redPacketTradeOrderMapper.insert(newRedPacketTradeOrder);
                // 事务二：冻结红包现金账户资源，和事务一构成分布式事务
                RedPacketAccount redPacketAccount = redPacketService.findByUserId(
                        redPacketTradeOrder.getSelfUserId());
                // 如果资源不足，会抛出异常，引起cancel逻辑的执行
                redPacketAccount.freeze(redPacketTradeOrder.getAmount());
                redPacketService.update(redPacketAccount);

            }catch (DataIntegrityViolationException ex){
                // 说明插入现金交易记录产生了冲突，忽略即可，保证只会插入一条
            }
        }
    }
}
