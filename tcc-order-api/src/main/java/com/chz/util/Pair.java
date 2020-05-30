package com.chz.util;

/**
 * @ClassName : Pair
 * @Description : 自定义Pair对
 * @Author : 陈寒哲
 * @Date: 2020-05-30 15:50
 */
public class Pair<L,R> {
    /**
     * 商品ID
     * */
    private L left;
    /**
     * 商品数量
     * */
    private R right;

    private static final long serialVersionUID = 4954918890077093841L;

    public static <L,R> Pair of(L left,R right){
        return new Pair(left,right);
    }

    public Pair(L left, R right){
        this.left = left;
        this.right = right;
    }
    public L getLeft(){
        return left;
    }
    public R getRight() {
        return right;
    }
    public R setRight(R right){
        throw new UnsupportedOperationException();
    }

}
