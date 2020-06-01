package com.chz.transaction.api;

import com.chz.transaction.common.TransactionType;

import javax.transaction.xa.Xid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : Transaction
 * @Description : 事务实体
 * @Author : 陈寒哲
 * @Date: 2020-06-01 20:11
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 7291423944314337931L;

    /**
     * 全局事务、分支事务id
     * */
    private TransactionXId xId;

    /**
     * 事务状态
     * */
    private TransactionStatus transactionStatus;

    /**
     * 事务类型（Root、Branch）
     * */
    private TransactionType transactionType;

    /**
     * 已经重试过的次数
     * */
    private volatile int retriedCount = 0;

    /**
     * 创建时间
     * */
    private Date createTime = new Date();

    /**
     * 上次更新时间
     * */
    private Date lastUpdateTime = new Date();

    /**
     * 事务版本号
     * */
    private long version = 1;

    /**
     * 参与该事务的集合
     * */
    private List<Participant> participants = new ArrayList<Participant>();

    public Transaction() {

    }

    /**
     * 根据事务上下文可以构造得到事务实体
     * */
    public Transaction(TransactionContext transactionContext) {
        this.xId = transactionContext.getXId();
        this.transactionStatus = TransactionStatus.TRYING;
        this.transactionType = TransactionType.BRANCH;
    }

    public Transaction(Object uniqueIdentity,TransactionType transactionType) {
        this.xId = new TransactionXId(uniqueIdentity);
        this.transactionStatus = TransactionStatus.TRYING;
        this.transactionType = transactionType;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }


    public Xid getXid() {
        return xId.clone();
    }

    public TransactionStatus getStatus() {
        return transactionStatus;
    }


    public List<Participant> getParticipants() {
        return participants;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void changeStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public int getRetriedCount() {
        return retriedCount;
    }

    public void addRetriedCount() {
        this.retriedCount++;
    }

    public void resetRetriedCount(int retriedCount) {
        this.retriedCount = retriedCount;
    }


    public long getVersion() {
        return version;
    }

    public void updateVersion() {
        this.version++;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date date) {
        this.lastUpdateTime = date;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void updateTime() {
        this.lastUpdateTime = new Date();
    }

    /**
     * 每一个事务参与者都confirm
     * */
    public void confirm() {

        for (Participant participant : participants) {
            participant.confirm();
        }
    }

    /**
     * 每一个事务参与者都cancel
     * */
    public void rollback() {
        for (Participant participant : participants) {
            participant.cancel();
        }
    }
}
