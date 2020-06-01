package com.chz.dao;

import com.chz.entity.CashAccount;

public interface CashAccountMapper {
    int deleteByPrimaryKey(Long capitalAccountId);

    int insert(CashAccount record);

    int insertSelective(CashAccount record);

    CashAccount selectByPrimaryKey(Long capitalAccountId);

    int updateByPrimaryKeySelective(CashAccount record);

    int updateByPrimaryKey(CashAccount record);

    CashAccount selectByUserId(Long userId);

    int update(CashAccount record);

}