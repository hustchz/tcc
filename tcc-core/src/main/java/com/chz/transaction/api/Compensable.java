package com.chz.transaction.api;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * @ClassName : Compensable
 * @Description : 核心注解，完成TCC流程
 * @Author : 陈寒哲
 * @Date: 2020-06-01 14:27
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Compensable {

    public Propagation propagation()default Propagation.REQUIRED;

    public String confirmMethod()default "";

    public String cancelMethod()default "";

    public boolean asyncConfirm() default false;

    public boolean asyncCancel() default false;

    /**
     * 事务上下文编辑器
     * */
    public Class<? extends TransactionContextEditor>[] getTransactionContextEditor() default DefaultTransactionContextEditor.class;

    public Class<? extends Exception>[] delayCancelExceptions() default {};
    /**
     * 默认事务上下文处理器
     * */
    class DefaultTransactionContextEditor implements TransactionContextEditor {

        @Override
        public TransactionContext get(Object target, Method method, Object[] args) {
            int position = getTransactionContextParamPosition(method.getParameterTypes());

            if (position > -1) {
                return (TransactionContext) args[position];
            }

            return null;
        }

        @Override
        public void set(TransactionContext transactionContext, Object target, Method method, Object[] args) {
            int position = getTransactionContextParamPosition(method.getParameterTypes());

            if (position > -1){
                args[position] = transactionContext;
            }
        }

        public static int getTransactionContextParamPosition(Class<?>[] parameterTypes) {

            int position = -1;

            //从方法参数中找到TransactionContext类的索引，找不到为-1
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i].equals(TransactionContext.class)) {
                    position = i;
                    break;
                }
            }
            return position;
        }
    }
}
