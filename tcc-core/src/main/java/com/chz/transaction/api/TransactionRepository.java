package com.chz.transaction.api;

import java.util.Date;
import java.util.List;

/**
 *  事务操作接口定义
 * */
public interface TransactionRepository {

    int create(Transaction transaction);

    int update(Transaction transaction);

    int delete(Transaction transaction);

    Transaction findByXid(TransactionXId xId);

    // 哪些操作失败的事务
    List<Transaction> findAllUnmodifiedSince(Date date);
}


