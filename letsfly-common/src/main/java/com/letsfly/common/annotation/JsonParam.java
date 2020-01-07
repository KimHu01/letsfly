package com.letsfly.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.letsfly.common.constant.GlobalConstant;

/**
 * 自定义注解，增强springmvc中controller层接收参数功能
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JsonParam {
    
    /**
     * 参数是否必填[默认为true]
     * @return
     */
    boolean required() default true;
    
    /**
     * 参数是否有前缀[默认为false]
     * @return
     */
    boolean prefix() default false;
    
    /**
     * 默认[前缀.属性名]的分隔符
     * @return
     */
    String separator() default GlobalConstant.SYMBOL_DOT;
}
