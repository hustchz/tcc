package com.chz.service;

import java.math.BigDecimal;

/**
 * 红包服务接口
 * */
public interface RedPacketService {
    BigDecimal getRedPacketAccountByUserId(long userId)throws Exception;
}
