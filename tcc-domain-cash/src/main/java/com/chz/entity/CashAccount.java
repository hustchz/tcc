package com.chz.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CashAccount implements Serializable {
    private Long capitalAccountId;

    private BigDecimal balanceAmount;

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
}