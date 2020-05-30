package com.chz.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class RedPacketAccount implements Serializable {
    private Long redPacketAccountId;

    private BigDecimal balanceAmount;

    private Long userId;

    private static final long serialVersionUID = 1L;

    public Long getRedPacketAccountId() {
        return redPacketAccountId;
    }

    public void setRedPacketAccountId(Long redPacketAccountId) {
        this.redPacketAccountId = redPacketAccountId;
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