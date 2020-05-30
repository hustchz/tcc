package com.chz.service;

import java.math.BigDecimal;

/**
 * 现金服务接口
 * */
public interface CashService {
    BigDecimal getCashAccountByUserId(long userId)throws Exception;
}
