package com.chz.configuration;

import com.chz.service.RedPacketService;
import com.chz.serviceImpl.RedPacketServiceImpl;
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
    private RedPacketService redPacketService;
    @Bean("/redPacketService")
    public HttpInvokerServiceExporter exportCashAccountService(){
        logger.info("准备暴露RedPacketService");
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(redPacketService);
        exporter.setServiceInterface(RedPacketService.class);
        logger.info("暴露RedPacketService成功");
        return exporter;
    }
}
