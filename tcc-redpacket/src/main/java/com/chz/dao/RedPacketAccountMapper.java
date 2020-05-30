package com.chz.dao;

import com.chz.entity.RedPacketAccount;

public interface RedPacketAccountMapper {
    int deleteByPrimaryKey(Long redPacketAccountId);

    int insert(RedPacketAccount record);

    int insertSelective(RedPacketAccount record);

    RedPacketAccount selectByPrimaryKey(Long redPacketAccountId);

    int updateByPrimaryKeySelective(RedPacketAccount record);

    int updateByPrimaryKey(RedPacketAccount record);
}