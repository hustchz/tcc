package com.chz;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName : OrderApplication
 * @Description : 订单模块启动类
 * @Author : 陈寒哲
 * @Date: 2020-05-30 12:46
 */
@SpringBootApplication
@MapperScan("com.chz.dao")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
