package com.chz;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName : RedPacketApplication
 * @Description : 红包账户模块启动类
 * @Author : 陈寒哲
 * @Date: 2020-05-30 12:53
 */
@SpringBootApplication
@MapperScan("com.chz.dao")
public class RedPacketApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedPacketApplication.class,args);
    }
}
