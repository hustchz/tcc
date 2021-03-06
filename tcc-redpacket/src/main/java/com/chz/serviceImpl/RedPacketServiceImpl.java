package com.chz.serviceImpl;
import com.chz.dao.RedPacketAccountMapper;
import com.chz.entity.RedPacketAccount;
import com.chz.exception.InsufficientBalanceException;
import com.chz.service.RedPacketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @ClassName : CashServiceImpl
 * @Description : 现金服务接口实现类
 * @Author : 陈寒哲
 * @Date: 2020-05-30 16:24
 */
@Service
public class RedPacketServiceImpl implements RedPacketService {

    @Resource
    private RedPacketAccountMapper redPacketAccountMapper;

    @Override
    public BigDecimal getRedPacketAccountByUserId(long userId) throws Exception {
        RedPacketAccount redPacketAccount = redPacketAccountMapper.selectByPrimaryKey(userId);
        if (null == redPacketAccount){
            return BigDecimal.ZERO;
        }
        return redPacketAccount.getBalanceAmount();
    }

    @Override
    public RedPacketAccount findByUserId(long userId) {
        return redPacketAccountMapper.findByUserId(userId);
    }

    /**
     * 数据库层面对数据有效性进一步保证
     * */
    @Override
    public void update(RedPacketAccount account) {
        int row = redPacketAccountMapper.update(account);
        if (row < 1){
            throw new InsufficientBalanceException("红包资源不足");
        }
    }
}
