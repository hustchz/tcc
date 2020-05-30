package com.chz.repository;

import com.chz.dao.ShopMapper;
import com.chz.entity.Shop;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by changming.xie on 4/1/16.
 */
@Repository
public class ShopRepository {
    @Resource
    private ShopMapper shopMapper;

    public Shop findById(long id) {
        return shopMapper.selectByPrimaryKey(id);
    }
}
