package com.chz.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class TradeOrder implements Serializable {
    private Long id;

    private Long selfUserId;

    private Long oppositeUserId;

    private String merchantOrderNo;

    private BigDecimal amount;

    private String status;

    private Integer version;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(Long selfUserId) {
        this.selfUserId = selfUserId;
    }

    public Long getOppositeUserId() {
        return oppositeUserId;
    }

    public void setOppositeUserId(Long oppositeUserId) {
        this.oppositeUserId = oppositeUserId;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}