package com.chz.serviceImpl;

import com.chz.entity.Order;
import com.chz.repository.OrderRepository;
import com.chz.service.PayService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * Created by changming.xie on 4/1/16.
 */
@Service
public class PaymentServiceImpl implements PayService {

//    @Autowired
//    TradeOrderServiceProxy tradeOrderServiceProxy;

    @Resource
    private OrderRepository orderRepository;

    @Override
    public void makePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal subtract) throws Exception {
        System.out.println("付款啦");
        if (1 > 0){
            throw new Exception("111");
        }
    }
}
