package com.chz.service;

import com.chz.dto.CashTradeOrderDto;
import com.chz.entity.CashTradeOrder;

public interface CashTradeService {
    // 现金账户预留资源——Try
    void remainCashTradeResource(CashTradeOrderDto cashTradeOrder);
}
