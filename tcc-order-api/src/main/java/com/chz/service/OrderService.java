package com.chz.service;

import com.chz.entity.Order;
import com.chz.util.Pair;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order findOrderById(Long orderId)throws Exception;
    // 支付，返回订单号
    String placeOrder(long payerUserId, long shopId, List<Pair<Long, Integer>> productQuantities, BigDecimal redPacketPayAmount);
}
