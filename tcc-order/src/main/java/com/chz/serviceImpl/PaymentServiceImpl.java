package com.chz.serviceImpl;

import com.chz.dto.CashTradeOrderDto;
import com.chz.dto.RedPacketTradeOrderDto;
import com.chz.entity.Order;
import com.chz.repository.OrderRepository;
import com.chz.service.OrderService;
import com.chz.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * Created by changming.xie on 4/1/16.
 */
@Service
public class PaymentServiceImpl implements PayService {

    @Autowired
    private TradeOrderServiceProxy tradeOrderServiceProxy;
    @Resource
    private OrderService orderService;

    /**
     * 实际支付逻辑—— 将订单的状态修改为PAYING，并分别进行现金账户和红包账户的TCC逻辑
     * */
    @Transactional
    @Override
    public void makePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal cashPayment) throws Exception {
        order.pay(redPacketPayAmount,cashPayment);
        // 数据库CAS机制，保证修改订单状态是线程安全的
        try{
            orderService.updateOrder(order);
        }catch (OptimisticLockingFailureException ex){
            // 出现了多线程修改订单的情况，忽略即可
        }
        // 完成现金账户的TCC逻辑
        tradeOrderServiceProxy.remainCashTradeResource(buildCapitalTradeOrderDto(order));
        // 完成红包账户的TCC逻辑
        tradeOrderServiceProxy.remainRedPacketTradeResource(buildRedPacketTradeOrderDto(order));
    }
    /**
     * 封装账户DTO
     */
    private CashTradeOrderDto buildCapitalTradeOrderDto(Order order) {

        CashTradeOrderDto tradeOrderDto = new CashTradeOrderDto();
        // 订单现金金额
        tradeOrderDto.setAmount(order.getCapitalPayAmount());
        // 订单ID
        tradeOrderDto.setMerchantOrderNo(order.getMerchantOrderNo());
        // 订单支付人
        tradeOrderDto.setSelfUserId(order.getPayerUserId());
        // 商品商家ID
        tradeOrderDto.setOppositeUserId(order.getPayeeUserId());
        tradeOrderDto.setOrderTitle(String.format("order no:%s", order.getMerchantOrderNo()));

        return tradeOrderDto;
    }
    /**
     * 封装红包账户DTO
     * */
    private RedPacketTradeOrderDto buildRedPacketTradeOrderDto(Order order) {
        RedPacketTradeOrderDto tradeOrderDto = new RedPacketTradeOrderDto();
        tradeOrderDto.setAmount(order.getRedPacketPayAmount());
        tradeOrderDto.setMerchantOrderNo(order.getMerchantOrderNo());
        tradeOrderDto.setSelfUserId(order.getPayerUserId());
        tradeOrderDto.setOppositeUserId(order.getPayeeUserId());
        tradeOrderDto.setOrderTitle(String.format("order no:%s", order.getMerchantOrderNo()));

        return tradeOrderDto;
    }
}
