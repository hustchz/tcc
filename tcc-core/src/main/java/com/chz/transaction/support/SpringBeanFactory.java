package com.chz.transaction.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName : SpringBeanFactory
 * @Description : 通过spring容器加载Bean
 * @Author : 陈寒哲
 * @Date: 2020-06-01 21:32
 */
public class SpringBeanFactory implements BeanFactory, ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Override
    public <T> T getBean(Class<T> var1) {
        return (T) applicationContext.getBean(var1.getName());
    }

    @Override
    public <T> boolean isFactoryOf(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz).size() > 0;
    }

    /**
     * 当上下文加载完毕时
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Builder.registerBeanFactory(this);
    }
}
