package com.chz.service;

import com.chz.dto.RedPacketTradeOrderDto;

public interface RedPacketTradeService {
    // 现金账户预留资源——Try
    void remainRedPacketTradeResource(RedPacketTradeOrderDto redPacketTradeOrderDto);
}
