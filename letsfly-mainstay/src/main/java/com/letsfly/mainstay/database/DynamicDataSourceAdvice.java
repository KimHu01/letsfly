package com.letsfly.mainstay.database;

import java.lang.reflect.Method;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import com.letsfly.common.annotation.DataSource;
import com.letsfly.common.util.StringUtil;

/**
 * 动态数据源设置
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class DynamicDataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(DynamicDataSourceAdvice.class);
    
    /** 数据源栈 */
    private static final ThreadLocal<Stack<String>> DATASOURCE_STACK = new ThreadLocal<Stack<String>>();
    
    /**
     * 初始化方法
     */
    public void init() {
    	DATASOURCE_STACK.set(new Stack<String>());
    }
    
    /**
     * 方法调用前
     * @param method 通知的方法
     * @param args 传递的参数
     * @param target 被调用方法所在的对象
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        Stack<String> stack = DATASOURCE_STACK.get();
        stack.push(DynamicDataSourceHolder.getDataSource());
        DATASOURCE_STACK.set(stack);
        
        DataSource dataSource = this.getDataSourceAnnotation(method, args, target);
        if(null == dataSource || StringUtil.isEmpty(dataSource.cluster())) {
            return;
        }
        
        DynamicDataSourceHolder.setDataSource(dataSource.cluster());
    }
    
    /**
     * 方法调用后
     * @param returnValue 被调用方法返回的值
     * @param method 通知的方法
     * @param args 传递的参数
     * @param target 被调用方法所在的对象
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) 
            throws Throwable {
        DynamicDataSourceHolder.setDataSource(DATASOURCE_STACK.get().pop());
    }
    
    /**
     * 方法抛出异常时
     * @param method 通知的方法
     * @param args 传递的参数
     * @param target 被调用方法所在的对象
     * @param ex 被抛出异常
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
    	logger.info("---DynamicDataSourceAdvice.afterThrowing()---{}---" + method.getName());
        logger.error("---DynamicDataSourceAdvice.afterThrowing()---", ex);
    }
    
    private DataSource getDataSourceAnnotation(Method method, Object[] args, Object target) {
        DataSource dataSourceInterface = null;
        DataSource dataSourceClazz = target.getClass().getAnnotation(DataSource.class);
        DataSource dataSourceMethod = method.getAnnotation(DataSource.class);
        
        Class<?>[] interfaces = target.getClass().getInterfaces();
        if(null != interfaces && interfaces.length > 0) {
            for(Class<?> inter : interfaces) {
                dataSourceInterface = inter.getAnnotation(DataSource.class);
                if(null != dataSourceInterface) {
                    break;
                }
            }
        }
        
        if(null == dataSourceInterface && null == dataSourceClazz && null == dataSourceMethod) {
            return null;
        }
        
        DataSource dataSource = dataSourceInterface;
        dataSource = (null != dataSourceClazz) ? dataSourceClazz : dataSource;
        dataSource = (null != dataSourceMethod) ? dataSourceMethod : dataSource;
        
        return dataSource;
    }
}
