package com.chz.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName : BeansConfiguration
 * @Description : 加载自定义配置文件
 * @Author : 陈寒哲
 * @Date: 2020-05-30 16:52
 */
@Configuration
@ImportResource(locations = {"beans.xml"})
public class LoadConfiguration {

}
