package com.chz.serviceImpl;

import com.chz.entity.Order;
import com.chz.entity.Shop;
import com.chz.exception.CancellingException;
import com.chz.exception.ConfirmingException;
import com.chz.factory.OrderFactory;
import com.chz.repository.OrderRepository;
import com.chz.repository.ShopRepository;
import com.chz.service.OrderService;
import com.chz.service.PayService;
import com.chz.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderFactory orderFactory;

    @Resource
    private ShopRepository shopRepository;

    @Resource
    private PayService payService;

    /**
     * 通过 用户ID、商家ID、购买商品明细信息 构建订单，由于涉及到入库操作，用事务
     * */
    @Transactional
    public Order createOrder(long payerUserId, long payeeUserId, List<Pair<Long, Integer>> productQuantities) {

        Order order = orderFactory.buildOrder(payerUserId, payeeUserId, productQuantities);
        // 将订单和订单明细入库
        orderRepository.createOrder(order);
        return order;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        return orderRepository.findByMerchantOrderNo(orderId);
    }

    @Override
    public String placeOrder(long payerUserId, long shopId, List<Pair<Long, Integer>> productQuantities, BigDecimal redPacketPayAmount) {
        Shop shop = shopRepository.findById(shopId);
        // 创建订单，订单状态默认为DRAFT(创建中)
        Order order = createOrder(payerUserId, shop.getOwnerUserId(), productQuantities);
        // 支付
        try {
            payService.makePayment(order, redPacketPayAmount, order.getTotalAmount().subtract(redPacketPayAmount));

        } catch (ConfirmingException confirmingException) {

        } catch (CancellingException cancellingException) {

        } catch (Throwable e) {

            e.printStackTrace();
        }

        return order.getMerchantOrderNo();
    }
}
