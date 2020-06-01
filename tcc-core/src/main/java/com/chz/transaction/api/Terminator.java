package com.chz.transaction.api;

import com.chz.transaction.support.Builder;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName : Terminator
 * @Description : 对事务上下文进行confirm或者cancel
 * @Author : 陈寒哲
 * @Date: 2020-06-01 20:51
 */
public class Terminator {

    public static Object invoke(TransactionContext transactionContext,
                                          InvocationContext invocationContext,
                                          Class<? extends TransactionContextEditor> transactionContextEditor) {
        // 判断是否需要执行confirm或者cancel
        String methodName = invocationContext.getMethodName();
        if (null == methodName || "".equals(methodName)){
            return null;
        }

        //得到反射类
        Class target = invocationContext.getTargetClass();

        Object instance = Builder.factoryOf(target).getInstance();

        Object[] args = invocationContext.getArgs();

        Class[] parameterTypes = invocationContext.getParameterTypes();

        Object result = null;
        try {
            Method method = instance.getClass().getMethod(methodName, parameterTypes);

            result = method.invoke(target, args);

            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
