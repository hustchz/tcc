package com.chz.service;

import com.chz.entity.RedPacketAccount;

import java.math.BigDecimal;

/**
 * 红包服务接口
 * */
public interface RedPacketService {
    BigDecimal getRedPacketAccountByUserId(long userId)throws Exception;

    RedPacketAccount findByUserId(long userId);

    void update(RedPacketAccount account);
}
