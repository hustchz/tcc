package com.chz.service;

import com.chz.entity.Order;

import java.math.BigDecimal;

public interface PayService {
    /**
     * 给现金账户和红包账户扣款
     * */
    void makePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal cashPayment) throws Exception;
}
