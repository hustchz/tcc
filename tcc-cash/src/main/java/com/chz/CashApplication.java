package com.chz;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @ClassName : CashApplication
 * @Description : 现金模块启动类
 * @Author : 陈寒哲
 * @Date: 2020-05-30 10:33
 */
@SpringBootApplication
@MapperScan("com.chz.dao")
public class CashApplication {
    public static void main(String[] args) {
        SpringApplication.run(CashApplication.class,args);
    }
}
