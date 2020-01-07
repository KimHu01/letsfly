package com.letsfly.common.util;

import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

import com.letsfly.common.constant.GlobalConstant;

/**
 * Random工具类
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class RandomUtil extends RandomUtils {
    
    /**
     * 私有化构造方法，防止被外部实例化
     */
    private RandomUtil() {}
    
    /**
     * 随机生成长度为len的数字字符串
     * @param len 长度
     * @return 
     */
    public static String randomizeNumeric(int len) {
        if(len <= 0) {
            throw new IllegalArgumentException("Illegal Parameter.");
        }
        
        StringBuffer res = new StringBuffer();
        for(int i=0; i<len; i++) {
            res.append(RandomUtils.nextInt() % 10);
        }
        
        return res.toString();
    }
    
    /**
     * 随机生成长度为len的字母(纯小写)字符串
     * @param len 长度
     * @return 
     */
    public static String randomizeAlphabetic(int len) {
        if(len <= 0) {
            throw new IllegalArgumentException("Illegal Parameter.");
        }
        
        StringBuffer res = new StringBuffer();
        for(int i=0; i<len; i++) {
            //a-z:97-122
            res.append((char)(RandomUtils.nextInt() % 26 + 97));
        }
        
        return res.toString();
    }
    
    /**
     * 随机生成长度为len的数字&字母(纯小写)字符串
     * @param len 长度
     * @return 
     */
    public static String randomizeAlphanum(int len) {
        if(len <= 0) {
            throw new IllegalArgumentException("Illegal Parameter.");
        }
        
        StringBuffer res = new StringBuffer();
        for(int i=0; i<len; i++) {
            int seed = RandomUtils.nextInt() % GlobalConstant.BASIC_STR.length();
            res.append(GlobalConstant.BASIC_STR.charAt(seed));
        }
        
        return res.toString();
    }
    
    /**
     * 从startInclusive到endExclusive生成number个不重复的随机数
     * @param startInclusive
     * @param endExclusive
     * @param number
     * @return
     */
    public static Integer[] randomizeNumeric(int startInclusive, int endExclusive, int number) {
        Map<Integer, Integer> map = new Hashtable<Integer, Integer>();
        
        while(true) {
            int tmp = RandomUtils.nextInt(startInclusive, endExclusive);
            map.put(tmp, tmp);
            if(map.size() == number) {
                break;
            }
        }
        
        Integer[] result = new Integer[number];
        return map.keySet().toArray(result);
    }
}
