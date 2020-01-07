package com.letsfly.common.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Number工具类
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class NumberUtil extends NumberUtils {
    
    /**
     * 私有化构造方法，防止被外部实例化
     */
    private NumberUtil() {}
    
    /**
     * byte值转换为十六进制字符
     * @param value byte值
     * @return 十六进制字符
     */
    public static String byte2Hex(byte value) {
        return bytes2Hex(new byte[]{value});
    }
    
    /**
     * byte数组转换为十六进制字符串
     * @param bytes byte数组
     * @return 十六进制字符串
     */
    public static String bytes2Hex(byte[] bytes) {
        if(null == bytes || bytes.length <= 0) {
            return null;
        }
        
        return Hex.encodeHexString(bytes);
    }
    
    /**
     * byte 十进制转二进制
     * @param value
     * @return
     */
    public static String byte2Binary(byte value) {
        return number2Binary(value);
    }
    
    /**
     * short 十进制转二进制
     * @param value
     * @return
     */
    public static String short2Binary(short value) {
        return number2Binary(value);
    }
    
    /**
     * int 十进制转二进制
     * @param value
     * @return
     */
    public static String int2Binary(int value) {
        return number2Binary(value);
    }
    
    /**
     * long 十进制转二进制
     * @param value
     * @return
     */
    public static String long2Binary(long value) {
        return number2Binary(value);
    }
    
    /**
     * 十进制转二进制
     * @param value
     * @return
     */
    public static <T extends Number> String number2Binary(T t) {
        StringBuffer buffer = new StringBuffer();
        
        if(t.getClass().equals(Byte.class)) {
            Byte value = (Byte) t;
            for(int i=Byte.SIZE - 1; i>=0; i--) {
                buffer.append(value >>> i & 1);
            }
        }
        
        if(t.getClass().equals(Short.class)) {
            Short value = (Short) t;
            for(int i=Short.SIZE - 1; i>=0; i--) {
                buffer.append(value >>> i & 1);
            }
        }
        
        if(t.getClass().equals(Integer.class)) {
            Integer value = (Integer) t;
            for(int i=Integer.SIZE - 1; i>=0; i--) {
                buffer.append(value >>> i & 1);
            }
        }
        
        if(t.getClass().equals(Long.class)) {
            Long value = (Long) t;
            for(int i=Long.SIZE - 1; i>=0; i--) {
                buffer.append(value >>> i & 1);
            }
        }
        
        return buffer.toString();
    }
}
