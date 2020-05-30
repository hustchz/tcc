package com.chz.dao;

import com.chz.entity.RedPacketTradeOrder;

public interface RedPacketTradeOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RedPacketTradeOrder record);

    int insertSelective(RedPacketTradeOrder record);

    RedPacketTradeOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedPacketTradeOrder record);

    int updateByPrimaryKey(RedPacketTradeOrder record);
}