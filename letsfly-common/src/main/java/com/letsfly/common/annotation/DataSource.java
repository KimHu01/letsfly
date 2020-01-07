package com.letsfly.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * 数据源注解[该注解放置在接口、实现类及方法三个级别均可]
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    
    /**
     * 数据库key值，参考jdbc配置文件
     * @return
     */
	@AliasFor("cluster")
	String value() default "";
	
	/**
     * 数据库key值，参考jdbc配置文件
     * @return
     */
	@AliasFor("value")
	String cluster() default "";
}
