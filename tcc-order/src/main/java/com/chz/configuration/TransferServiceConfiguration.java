package com.chz.configuration;

import com.chz.service.CashService;
import com.chz.service.RedPacketService;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * @ClassName : TransferServiceConfiguration
 * @Description : 调用服务配置类
 * @Author : 陈寒哲
 * @Date: 2020-05-30 19:08
 */
@Configuration
public class TransferServiceConfiguration {

    private static Logger logger = Logger.getLogger(TransferServiceConfiguration.class);

    private static final String TCC_CASH_URL = "http://localhost:9090/cashService";

    private static final String TCC_REDPACKET_URL = "http://localhost:9092/redPacketService";
    /**
     * 调用远程服务———— tcc-cash
     */
    @Bean
    public HttpInvokerProxyFactoryBean transferCashService(){
        logger.info("正在加载远程服务["+"cashService]");
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceUrl(TCC_CASH_URL);
        factoryBean.setServiceInterface(CashService.class);
        logger.info("加载远程服务["+"cashService]完成");
        return factoryBean;
    }
    /**
     * 调用远程服务———— tcc-redpacket
     */
    @Bean
    public HttpInvokerProxyFactoryBean transferRedPacketService(){
        logger.info("正在加载远程服务["+"redPacketService]");
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceUrl(TCC_REDPACKET_URL);
        factoryBean.setServiceInterface(RedPacketService.class);
        logger.info("加载远程服务["+"redPacketService]完成");
        return factoryBean;
    }
}
