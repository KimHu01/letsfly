package com.letsfly.common.pattern;

/**
 * 常用正则表达式
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class RegularExpressionPattern {
    
    /**
     * 私有化构造函数, 防止被外部实例化
     */
    private RegularExpressionPattern() {}
    
    /** 占位符 */
    public final static String REG_EXP_PLACEHOLDER = "\\{(.*?)\\}";
    
    /** IP地址 */
    public final static String REG_EXP_IP = "\\d{1,3}(\\.\\d{1,3}){3,5}$";
}
