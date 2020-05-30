package com.chz.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderLine implements Serializable {
    private Long orderLineId;

    private Long productId;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private static final long serialVersionUID = 1L;

    public Long getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(Long orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}