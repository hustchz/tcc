package com.chz.service;

import com.chz.entity.CashAccount;

import java.math.BigDecimal;

/**
 * 现金服务接口
 * */
public interface CashService {
    BigDecimal getCashAccountByUserId(long userId)throws Exception;
    // 更新现金账户
    void update(CashAccount cashAccount);
    // 根据用户ID找到账户信息
    CashAccount findByUserId(Long userId);

}
