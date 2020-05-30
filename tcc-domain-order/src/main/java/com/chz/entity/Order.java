package com.chz.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
    private Long orderId;

    private Long payerUserId;

    private Long payeeUserId;

    private BigDecimal redPacketPayAmount;

    private BigDecimal capitalPayAmount;

    private String status;

    private String merchantOrderNo;

    private Integer version;

    private static final long serialVersionUID = 1L;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPayerUserId() {
        return payerUserId;
    }

    public void setPayerUserId(Long payerUserId) {
        this.payerUserId = payerUserId;
    }

    public Long getPayeeUserId() {
        return payeeUserId;
    }

    public void setPayeeUserId(Long payeeUserId) {
        this.payeeUserId = payeeUserId;
    }

    public BigDecimal getRedPacketPayAmount() {
        return redPacketPayAmount;
    }

    public void setRedPacketPayAmount(BigDecimal redPacketPayAmount) {
        this.redPacketPayAmount = redPacketPayAmount;
    }

    public BigDecimal getCapitalPayAmount() {
        return capitalPayAmount;
    }

    public void setCapitalPayAmount(BigDecimal capitalPayAmount) {
        this.capitalPayAmount = capitalPayAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}