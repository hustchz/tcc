package com.chz.serviceImpl;
import com.chz.dao.CashTradeOrderMapper;
import com.chz.dto.CashTradeOrderDto;
import com.chz.entity.CashAccount;
import com.chz.entity.CashTradeOrder;
import com.chz.service.CashService;
import com.chz.service.CashTradeService;
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
public class CashTradeServiceImpl implements CashTradeService {
    @Resource
    private CashTradeOrderMapper cashTradeOrderMapper;
    @Resource
    private CashService cashService;

    @Transactional
    @Override
    public void remainCashTradeResource(CashTradeOrderDto cashTradeOrder) {
        // 通过订单ID 找到现金账户是否支付过该订单，最多只能有一条记录
        String orderId = cashTradeOrder.getMerchantOrderNo();
        CashTradeOrder tradeOrder = cashTradeOrderMapper.findByOrderId(orderId);
        //保证幂等
        if (null == tradeOrder){
            CashTradeOrder newCashTradeOrder = new CashTradeOrder(
                    cashTradeOrder.getSelfUserId(),
                    cashTradeOrder.getOppositeUserId(),
                    orderId,
                    cashTradeOrder.getAmount()
            );

            // TRY预留资源
            try{
                // 事务一:插入现金交易记录(CAP_TRADE_ORDER)，状态为DRAFT
                cashTradeOrderMapper.insert(newCashTradeOrder);
                // 事务二：冻结用户现金账户资源，和事务一构成分布式事务
                CashAccount cashAccount = cashService.findByUserId(
                        cashTradeOrder.getSelfUserId());
                // 如果资源不足，会抛出异常，引起cancel逻辑的执行
                cashAccount.freeze(cashTradeOrder.getAmount());
                cashService.update(cashAccount);

            }catch (DataIntegrityViolationException ex){
                // 说明插入现金交易记录产生了冲突，忽略即可，保证只会插入一条
            }
        }
    }
}
