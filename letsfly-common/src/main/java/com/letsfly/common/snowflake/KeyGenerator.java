package com.letsfly.common.snowflake;

/**
 * KeyGenerator
 * @author kimhu
 * @create 2019/11/14
 * @version 1.0
 */
public final class KeyGenerator {
    
    private static SnowFlake snowFlake = new SnowFlake(0, 0);
    
    /**
     * 生成key(Long类型)
     * @return
     */
    public static long generateLongKey() {
        return snowFlake.next();
    }
    
    /**
     * 生成key(String类型)
     * @return
     */
    public static String generateStringKey() {
        return Long.toString(generateLongKey());
    }
}
