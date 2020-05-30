package com.chz.dao;

import com.chz.entity.Product;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    /**
     * 根据商铺ID 找所有商品
     * */
    List<Product> findByShopId(long shopId);
}