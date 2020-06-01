package com.chz.transaction.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @ClassName : AbstractCompensableTransactionAspectJ
 * @Description : 为所有实现了compensable注解的方法提供切面
 * @Author : 陈寒哲
 * @Date: 2020-06-01 15:35
 */
/**
 * 整个切面的处理流程
 * around1->before->method->around2  (环绕结束)->after->afterReturning/afterThrowing(有异常)
 * */
@Aspect
public abstract class AbstractCompensableTransactionAspectJ {

    private CompenableTransactionInterceptor interceptor;

    public AbstractCompensableTransactionAspectJ(CompenableTransactionInterceptor compenableTransactionInterceptor){
        this.interceptor = compenableTransactionInterceptor;
    }

    /**
     * 定义切点,表达式为当使用注解时生效
     */
    @Pointcut(value = "@annotation(com.chz.transaction.api.Compensable)")
    public void compensablePointCut(){
        // nothing to do
    }

    /**
     *  定义增强 ProceedingJoinPoint中封装了注解信息以及方法参数，类型等等
     * */
    @Around(value = "compensablePointCut()")
    public Object interceptor(ProceedingJoinPoint pjp){
        return interceptor.interceptor(pjp);
    }

    public abstract int getOrder();

}
