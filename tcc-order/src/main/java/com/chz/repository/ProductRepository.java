package com.chz.repository;

import com.chz.dao.ProductMapper;
import com.chz.entity.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by twinkle.zhou on 16/11/10.
 */
@Repository
public class ProductRepository {

    @Resource
    private ProductMapper productMapper;

    public Product findById(long productId){
        return productMapper.selectByPrimaryKey(productId);
    }

    public List<Product> findByShopId(long shopId){
        return productMapper.findByShopId(shopId);
    }
}
