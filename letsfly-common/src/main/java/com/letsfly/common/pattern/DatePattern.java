package com.letsfly.common.pattern;

/**
 * 日期表达式
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public enum DatePattern {
    YEAR("yyyy"),
    MONTH("MM"),
    DAY("dd"),
    YM("yyyyMM"),
    YM_DASH("yyyy-MM"),
    YMD("yyyyMMdd"),
    YMD_DASH("yyyy-MM-dd"),
    YMDHMS("yyyyMMddHHmmss"),
    YMDHMS_DASH("yyyy-MM-dd HH:mm:ss"),
    YMDHMSS("yyyyMMddHHmmssSSS"),
    YMDHMSS_DASH("yyyy-MM-dd HH:mm:ss.SSS"),
    HOUR("HH"),
    MINUTE("mm"),
    SECONDS("ss"),
    HMS("HHmmss"),
    HMS_DASH("HH:mm:ss"),
    HMSS("HHmmssSSS"),
    HMSS_DASH("HH:mm:ss.SSS");
    
    /** 日期表达式 */
    private String pattern;
    
    /**
     * 构造方法
     * @param pattern
     */
    private DatePattern(String pattern) {
        this.pattern = pattern;
    }
    
    public String getPattern() {
        return pattern;
    }
}
