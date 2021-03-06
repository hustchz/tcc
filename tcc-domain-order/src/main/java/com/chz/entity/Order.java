package com.chz.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {
    private Long orderId;

    private Long payerUserId;

    private Long payeeUserId;

    private BigDecimal redPacketPayAmount;

    private BigDecimal cashPayment;

    private String status;

    private String merchantOrderNo;

    private Integer version;

    private static final long serialVersionUID = 1L;

    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    public Order() {

    }
    public Order(long payerUserId, long payeeUserId) {
        this.payerUserId = payerUserId;
        this.payeeUserId = payeeUserId;
        this.merchantOrderNo = UUID.randomUUID().toString().replace("-","");
    }
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
        return cashPayment;
    }

    public void setCapitalPayAmount(BigDecimal cashPayment) {
        this.cashPayment = cashPayment;
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
    /**
     * 订单总额
     * */
    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLines) {
            totalAmount = totalAmount.add(orderLine.getTotalAmount());
        }
        return totalAmount;
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(this.orderLines);
    }

    public void pay(BigDecimal redPacketPayAmount, BigDecimal cashPayment) {
        this.redPacketPayAmount = redPacketPayAmount;
        this.cashPayment = cashPayment;
        this.status = "PAYING";
    }
    /**
     * 更新版本号
     * */
    public void updateVersion() {
        version++;
    }

    public void confirm() {
        this.status = "CONFIRMED";
    }

    public void cancelPayment() {
        this.status = "PAY_FAILED";
    }

}