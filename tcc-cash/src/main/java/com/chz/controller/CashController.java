package com.chz.controller;

import com.chz.service.CashService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName : CashController
 * @Description : 现金处理器
 * @Author : 陈寒哲
 * @Date: 2020-05-30 11:02
 */
@RequestMapping("/cash")
@RestController
public class CashController {
    @Resource
    private CashService cashService;
    @RequestMapping("/test")
    public String test() throws Exception {
        return cashService.getCashAccountByUserId(2000)+"";
    }

}
