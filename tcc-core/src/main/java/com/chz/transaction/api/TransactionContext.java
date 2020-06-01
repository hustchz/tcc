package com.chz.transaction.api;

import java.io.Serializable;

/**
 * @ClassName : TransactionContext
 * @Description : 事务上下文实体
 * @Author : 陈寒哲
 * @Date: 2020-06-01 14:38
 */
public class TransactionContext implements Serializable {

    private static final long serialVersionUID = -8199390103169700387L;
    /**
     * 事务对象
     * */
    private TransactionXId xId;

    /**
     * 事务上下文状态,TRYING(1) CONFIRMING(2) CANCELING(3)
     * */
    private int status;

    public TransactionContext() {

    }

    public TransactionContext(TransactionXId xid, int status) {
        this.xId = xid;
        this.status = status;
    }

    public void setXId(TransactionXId xId) {
        this.xId = xId;
    }

    /**
     * 返回克隆出的新对象
     * */
    public TransactionXId getXId() {
        return xId.clone();
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
