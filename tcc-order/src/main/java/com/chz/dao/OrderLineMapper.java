package com.chz.dao;

import com.chz.entity.OrderLine;

public interface OrderLineMapper {
    int deleteByPrimaryKey(Long orderLineId);

    int insert(OrderLine record);

    int insertSelective(OrderLine record);

    OrderLine selectByPrimaryKey(Long orderLineId);

    int updateByPrimaryKeySelective(OrderLine record);

    int updateByPrimaryKey(OrderLine record);
}