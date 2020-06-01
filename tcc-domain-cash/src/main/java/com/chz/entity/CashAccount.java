package com.chz.entity;

import com.chz.exception.InsufficientBalanceException;

import javax.naming.InsufficientResourcesException;
import java.io.Serializable;
import java.math.BigDecimal;

public class CashAccount implements Serializable {
    private Long capitalAccountId;

    /**
     * 账户可用资源
     * */
    private BigDecimal balanceAmount;

    /**
     * 账户被冻结资源
     * */
    private BigDecimal freezeAmount = BigDecimal.ZERO;

    private Long userId;

    private static final long serialVersionUID = 1L;

    public Long getCapitalAccountId() {
        return capitalAccountId;
    }

    public void setCapitalAccountId(Long capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 冻结相应资源
     * */
    public void freeze(BigDecimal amount){
        this.balanceAmount = this.balanceAmount.subtract(amount);
        if (BigDecimal.ZERO.compareTo(balanceAmount) > 0){
            throw new InsufficientBalanceException("资源不足");
        }
        // 冻结资源是负数
        this.freezeAmount = this.freezeAmount.add(amount.negate());
    }

    /**
     * 解冻相应资源
     */
    public void unFreeze(BigDecimal amount){
        this.balanceAmount = this.balanceAmount.add(amount);
        // freezeAmount 是负数，因此是加
        this.freezeAmount = this.freezeAmount.add(amount);
    }
    public void cancelFreeze(BigDecimal amount) {
        unFreeze(amount);
    }
}