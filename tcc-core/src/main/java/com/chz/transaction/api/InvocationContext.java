package com.chz.transaction.api;

import java.io.Serializable;

/**
 * @ClassName : InvocationContext
 * @Description : 反射的上下文类
 * @Author : 陈寒哲
 * @Date: 2020-06-01 20:40
 */
public class InvocationContext implements Serializable {

    private static final long serialVersionUID = -7969140711432461165L;

    /**
     * 反射代理类
     * */
    private Class targetClass;

    /**
     * 反射代理方法名
     * */
    private String methodName = null;

    /**
     * 反射代理方法参数类型
     * */
    private Class[] parameterTypes;

    /**
     * 反射代理参数
     * */
    private Object[] args;

    public InvocationContext() {

    }

    public InvocationContext(Class targetClass, String methodName, Class[] parameterTypes, Object... args) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.targetClass = targetClass;
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }
}
