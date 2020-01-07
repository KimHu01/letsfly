package com.letsfly.mainstay.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * SpringContext工具类
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public final class SpringContext implements ApplicationContextAware {
    
    /** spring上下文 */
    private static ApplicationContext applicationContext;
    
    /**
     * 设置spring上下文
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }
    
    /**
     * 获取spring上下文
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    /**
     * 根据beanName获取bean实例
     * @param beanName 名称
     * @return bean实例
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
    
    /**
     * 根据beanName和bean类型获取bean实例
     * @param beanName 名称
     * @param clazz 类型
     * @return bean实例
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }
    
    /**
     * 根据bean类型获取bean实例
     * @param clazz bean类型
     * @return bean实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
