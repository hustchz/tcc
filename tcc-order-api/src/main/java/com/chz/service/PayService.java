package com.chz.service;

import com.chz.entity.Order;

import java.math.BigDecimal;

public interface PayService {
    void makePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal subtract) throws Exception;
}
