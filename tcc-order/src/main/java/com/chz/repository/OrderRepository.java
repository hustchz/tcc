package com.chz.repository;


import com.chz.dao.OrderLineMapper;
import com.chz.dao.OrderMapper;
import com.chz.entity.Order;
import com.chz.entity.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by changming.xie on 4/1/16.
 */
@Repository
public class OrderRepository {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderLineMapper orderLineMapper;
    /**
     * 创建订单，并插入订单明细表
     * */
    public void createOrder(Order order) {
        orderMapper.insert(order);
        for (OrderLine orderLine : order.getOrderLines()) {
            orderLineMapper.insert(orderLine);
        }
    }
    public void updateOrder(Order order) {
        order.updateVersion();
        int effectCount = orderMapper.updateByPrimaryKey(order);

        if (effectCount < 1) {
            throw new OptimisticLockingFailureException("update order failed");
        }
    }
    /**
     * 根据订单ID找订单信息
     * */
    public Order findByMerchantOrderNo(long merchantOrderNo) {
        return orderMapper.selectByPrimaryKey(merchantOrderNo);
    }
}
