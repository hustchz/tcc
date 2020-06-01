package com.chz.serviceImpl;

import com.chz.dto.CashTradeOrderDto;
import com.chz.dto.RedPacketTradeOrderDto;
import com.chz.entity.CashTradeOrder;
import com.chz.entity.RedPacketTradeOrder;
import com.chz.service.CashService;
import com.chz.service.CashTradeService;
import com.chz.service.RedPacketService;
import com.chz.service.RedPacketTradeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName : TradeOrderServiceProxy
 * @Description : 交易订单的代理类，负责完成红包账户和现金账户的TCC逻辑
 * @Author : 陈寒哲
 * @Date: 2020-06-01 09:30
 */
@Component
public class TradeOrderServiceProxy {
    @Resource
    private CashTradeService cashTradeService;
    @Resource
    private RedPacketTradeService redPacketTradeService;

    /**
     * 预留现金账户交易资源
     * */
    public void remainCashTradeResource(CashTradeOrderDto order) {
        cashTradeService.remainCashTradeResource(order);
    }
    /**
     * 预留红包账户交易资源
     * */
    public void remainRedPacketTradeResource(RedPacketTradeOrderDto order){
        redPacketTradeService.remainRedPacketTradeResource(order);
    }
}
