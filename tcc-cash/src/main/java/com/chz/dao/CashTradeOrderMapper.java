package com.chz.dao;

import com.chz.entity.CashTradeOrder;

public interface CashTradeOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashTradeOrder record);

    int insertSelective(CashTradeOrder record);

    CashTradeOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CashTradeOrder record);

    int updateByPrimaryKey(CashTradeOrder record);
}