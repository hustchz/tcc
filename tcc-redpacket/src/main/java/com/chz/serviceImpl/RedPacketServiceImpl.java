package com.chz.serviceImpl;
import com.chz.dao.RedPacketAccountMapper;
import com.chz.entity.RedPacketAccount;
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
}
