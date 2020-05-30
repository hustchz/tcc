package com.chz.configuration;

import com.chz.service.CashService;
import com.chz.serviceImpl.CashServiceImpl;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import javax.annotation.Resource;

/**
 * @ClassName : ExportServiceConfiguration
 * @Description : 暴露服务配置类
 * @Author : 陈寒哲
 * @Date: 2020-05-30 18:53
 */
@Configuration
public class ExportServiceConfiguration {

    private static Logger logger = Logger.getLogger(ExportServiceConfiguration.class);

    @Resource
    private CashService cashService;

    /**
     * springMVC中BeanNameUrlHandlerMapping 将beanName带有/的远程暴露
     * */
    @Bean("/cashService")
    public HttpInvokerServiceExporter exportCashAccountService(){
        logger.info("准备暴露CashService");
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(cashService);
        exporter.setServiceInterface(CashService.class);
        logger.info("暴露CashService成功");
        return exporter;
    }
}
