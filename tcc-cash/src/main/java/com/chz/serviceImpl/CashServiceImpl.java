package com.chz.serviceImpl;

import com.chz.dao.CashAccountMapper;
import com.chz.entity.CashAccount;
import com.chz.exception.InsufficientBalanceException;
import com.chz.service.CashService;
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
public class CashServiceImpl implements CashService {

    @Resource
    private CashAccountMapper cashAccountMapper;

    @Override
    public BigDecimal getCashAccountByUserId(long userId) throws Exception {
        CashAccount cashAccount = cashAccountMapper.selectByUserId(userId);
        if (null == cashAccount){
            return BigDecimal.ZERO;
        }
        return cashAccount.getBalanceAmount();
    }

    /**
     * 在数据库层面进一步校验资源有效性
     * */
    @Override
    public void update(CashAccount cashAccount) {
        int row = cashAccountMapper.update(cashAccount);
        if (row < 1){
            throw new InsufficientBalanceException("资源不足");
        }
    }

    @Override
    public CashAccount findByUserId(Long userId) {
        return cashAccountMapper.selectByUserId(userId);
    }
}
