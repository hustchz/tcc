package com.chz.transaction.inceptor;

import org.aspectj.lang.annotation.Aspect;

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
public class AbstractCompensableTransactionAspectJ {


}
