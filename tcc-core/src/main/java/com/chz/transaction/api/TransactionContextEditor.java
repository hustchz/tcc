package com.chz.transaction.api;

import java.lang.reflect.Method;

/**
 * 事务上下文环境操作接口，存和取
 * */
public interface TransactionContextEditor {

     TransactionContext get(Object target, Method method, Object[] args);

     void set(TransactionContext transactionContext, Object target, Method method, Object[] args);
}
