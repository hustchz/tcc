package com.chz.controller;

import com.chz.entity.Order;
import com.chz.entity.Product;
import com.chz.repository.ProductRepository;
import com.chz.service.CashService;
import com.chz.service.OrderService;
import com.chz.service.RedPacketService;
import com.chz.util.Pair;
import com.chz.dvo.OrderRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * @ClassName : IndexController
 * @Description : 订单首页
 * @Author : 陈寒哲
 * @Date: 2020-05-30 14:53
 */
@Controller
public class OrderController {

    private static final int DEFAULT_PRODUCT_AMOUNT = 1;

    @Resource
    private OrderService orderService;

    @Resource
    private ProductRepository productRepository;

    /**
     * 使用HttpInvoker进行远程调用，在tcc-cash中暴露服务
     * */
    @Resource
    private CashService cashService;

    @Resource
    private RedPacketService redpacketService;

    /**
     * 跳转至首页
     * */
    @RequestMapping("")
    public String index(){
        return "index";
    }
    /**
     * 商品详情
     * */
    @RequestMapping(value = "/user/{userId}/shop/{shopId}", method = RequestMethod.GET)
    public ModelAndView getProductsInShop(@PathVariable long userId,
                                          @PathVariable long shopId) {
        List<Product> products = productRepository.findByShopId(shopId);
        ModelAndView mv = new ModelAndView("shop");
        mv.addObject("products", products);
        mv.addObject("userId", String.valueOf(userId));
        mv.addObject("shopId", String.valueOf(shopId));
        return mv;
    }
    /**
     * 订单明细，可以跳转至订单支付页面
     */
    @RequestMapping(value = "/user/{userId}/shop/{shopId}/product/{productId}/confirm",
            method = RequestMethod.GET)
    public ModelAndView productDetail(@PathVariable String userId,
                                      @PathVariable String shopId,
                                      @PathVariable String productId) throws Exception {

        ModelAndView mv = new ModelAndView("product_detail");

        mv.addObject("capitalAmount",
                cashService.getCashAccountByUserId(Long.parseLong(userId)));
        mv.addObject("redPacketAmount",
                redpacketService.getRedPacketAccountByUserId(Long.parseLong(userId)));
        mv.addObject("product",
                productRepository.findById(Long.parseLong(productId)));
        mv.addObject("userId", userId);
        mv.addObject("shopId", shopId);

        return mv;
    }
    /**
     *  订单支付
     */
    @RequestMapping(value = "/placeorder", method = RequestMethod.POST)
    public RedirectView placeOrder(@RequestParam String redPacketPayAmount,
                                   @RequestParam String shopId,
                                   @RequestParam String payerUserId,
                                   @RequestParam String productId) {

        // 检查使用红包金额合法性，并封装成订单支付请求
        OrderRequest request = buildRequest(
                redPacketPayAmount,
                Long.parseLong(shopId),
                Long.parseLong(payerUserId),
                Long.parseLong(productId)
        );
        // TCC事务的应用层，开始调用TCC事务
        String merchantOrderNo = orderService.placeOrder(request.getPayerUserId(), request.getShopId(),
                request.getProductQuantities(), request.getRedPacketPayAmount());

        // 重定向到支付结果页面
        return new RedirectView("payresult/" + merchantOrderNo);
    }

    /**
     * 支付结果页面
     */
    @RequestMapping(value = "/payresult/{merchantOrderNo}", method = RequestMethod.GET)
    public ModelAndView getPayResult(@PathVariable String merchantOrderNo) throws Exception {

        ModelAndView mv = new ModelAndView("pay_success");

        String payResultTip = null;

        Order order = orderService.findOrderById(
                Long.parseLong(merchantOrderNo)
        );
        // 订单的状态
        payResultTip = order.getStatus();
        mv.addObject("payResult", payResultTip);
        // 找到用户的现金账户，并传给页面
        mv.addObject("capitalAmount", cashService.getCashAccountByUserId(order.getPayerUserId()));
        // 找到用户的红包账户，并传给页面
        mv.addObject("redPacketAmount", redpacketService.getRedPacketAccountByUserId(order.getPayerUserId()));

        return mv;
    }


    private OrderRequest buildRequest(String redPacketPayAmount, long shopId, long payerUserId, long productId) {
        BigDecimal redPacketPayAmountInBigDecimal = new BigDecimal(redPacketPayAmount);
        if (redPacketPayAmountInBigDecimal.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidParameterException("invalid red packet amount :" + redPacketPayAmount);

        OrderRequest request = new OrderRequest();
        request.setPayerUserId(payerUserId);
        request.setShopId(shopId);
        request.setRedPacketPayAmount(new BigDecimal(redPacketPayAmount));
        request.getProductQuantities().add(new Pair<Long, Integer>(productId, DEFAULT_PRODUCT_AMOUNT));
        return request;
    }
}
