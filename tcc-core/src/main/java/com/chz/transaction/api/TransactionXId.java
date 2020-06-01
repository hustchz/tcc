package com.chz.transaction.api;

import javax.transaction.xa.Xid;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

/**
 * @ClassName : Transaction
 * @Description : 事务实体
 * @Author : 陈寒哲
 * @Date: 2020-06-01 14:40
 */
public class TransactionXId implements Xid, Serializable {

    private static final long serialVersionUID = -6817267250789142043L;

    private static final byte[] CUSTOMIZED_TRANSACTION_ID = "UniqueIdentity".getBytes();;

    private int formatId = 1;

    /**
     * 全局事务ID
     * */
    private byte[] globalTransactionId;

    /**
     * 分支事务ID
     * */
    private byte[] branchQualifier;

    public TransactionXId(byte[] globalTransactionId, byte[] branchQualifier) {
        this.globalTransactionId = globalTransactionId;
        this.branchQualifier = branchQualifier;
    }

    public TransactionXId(byte[] globalTransactionId) {
        this.globalTransactionId = globalTransactionId;
        this.branchQualifier = uuidToByteArray(UUID.randomUUID());
    }

    public TransactionXId(Object uniqueIdentity) {
        if (uniqueIdentity == null) {
            globalTransactionId = uuidToByteArray(UUID.randomUUID());
            branchQualifier = uuidToByteArray(UUID.randomUUID());

        } else {
            this.globalTransactionId = CUSTOMIZED_TRANSACTION_ID;
            this.branchQualifier = uniqueIdentity.toString().getBytes();
        }
    }

    private byte[] uuidToByteArray(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        // 最重要的64位
        bb.putLong(uuid.getMostSignificantBits());
        //最不重要的64位
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public int getFormatId() {
        return formatId;
    }

    @Override
    public byte[] getGlobalTransactionId() {
        return globalTransactionId;
    }

    @Override
    public byte[] getBranchQualifier() {
        return branchQualifier;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (Arrays.equals(CUSTOMIZED_TRANSACTION_ID, globalTransactionId)) {
            stringBuilder.append(new String(globalTransactionId));
            stringBuilder.append(":").append(new String(branchQualifier));
        } else {
            stringBuilder.append(UUID.nameUUIDFromBytes(globalTransactionId).toString());
            stringBuilder.append(":").append(UUID.nameUUIDFromBytes(branchQualifier).toString());
        }
        return stringBuilder.toString();
    }

    public TransactionXId clone() {
        byte[] cloneGlobalTransactionId = null;
        byte[] cloneBranchQualifier = null;

        if (globalTransactionId != null) {
            cloneGlobalTransactionId = new byte[globalTransactionId.length];
            System.arraycopy(globalTransactionId, 0, cloneGlobalTransactionId, 0, globalTransactionId.length);
        }

        if (branchQualifier != null) {
            cloneBranchQualifier = new byte[branchQualifier.length];
            System.arraycopy(branchQualifier, 0, cloneBranchQualifier, 0, branchQualifier.length);
        }

        return new TransactionXId(cloneGlobalTransactionId, cloneBranchQualifier);
    }

    /**
     * 重写equals
     * */
    public boolean equals(Object obj) {
        // 指向同一块空间，必然相等
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }
        // 不指向同一块空间，并且两个对象都是TransactionXId类
        // 要求formatId globalTransactionId branchQualifier 均相等
        TransactionXId other = (TransactionXId) obj;
        if (this.getFormatId() != other.getFormatId()) {
            return false;
        } else if (!Arrays.equals(branchQualifier, other.branchQualifier)) {
            return false;
        } else if (!Arrays.equals(globalTransactionId, other.globalTransactionId)) {
            return false;
        }
        return true;
    }

    /**
     * 重写hashcode 考虑formatId globalTransactionId branchQualifier
     * */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getFormatId();
        result = prime * result + Arrays.hashCode(branchQualifier);
        result = prime * result + Arrays.hashCode(globalTransactionId);
        return result;
    }
}

