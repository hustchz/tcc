package com.chz.transaction.api;

import java.io.Serializable;

/**
 * @ClassName : Participant
 * @Description :事务的参与者
 * @Author : 陈寒哲
 * @Date: 2020-06-01 20:25
 */
public class Participant implements Serializable {

    private static final long serialVersionUID = 4127729421281425247L;

    /**
     * 事务Id
     * */
    private TransactionXId xId;

    /**
     * 事务上下文处理器，用来得到事务上下文
     * */
    Class<? extends TransactionContextEditor> transactionContextEditor;

    /**
     * confirm 方法的反射上下文
     * */
    private InvocationContext confirmInvocationContext;

    /**
     * cancel 方法的反射上下文
     * */
    private InvocationContext cancelInvocationContext;

    public Participant() {

    }

    public Participant(TransactionXId xId, InvocationContext confirmInvocationContext, InvocationContext cancelInvocationContext, Class<? extends TransactionContextEditor> transactionContextEditorClass) {
        this.xId = xId;
        this.confirmInvocationContext = confirmInvocationContext;
        this.cancelInvocationContext = cancelInvocationContext;
        this.transactionContextEditor = transactionContextEditorClass;
    }

    public Participant(InvocationContext confirmInvocationContext, InvocationContext cancelInvocationContext, Class<? extends TransactionContextEditor> transactionContextEditorClass) {
        this.confirmInvocationContext = confirmInvocationContext;
        this.cancelInvocationContext = cancelInvocationContext;
        this.transactionContextEditor = transactionContextEditorClass;
    }

    public void setXid(TransactionXId xId) {
        this.xId = xId;
    }

    /**
     * 参与者执行confirm
     * */
    public void confirm() {

        TransactionContext transactionContext = new TransactionContext(
                this.xId,
                TransactionStatus.CONFIRMING.getId()
        );
        Terminator.invoke(transactionContext,confirmInvocationContext,transactionContextEditor);
    }

    /**
     * 参与者执行cancel
     * */
    public void cancel() {

        TransactionContext transactionContext = new TransactionContext(
                this.xId,
                TransactionStatus.CANCELLING.getId()
        );
        Terminator.invoke(transactionContext,cancelInvocationContext,transactionContextEditor);
    }

    public TransactionXId getXid() {
        return xId;
    }
}
