package com.letsfly.common.util;

import java.io.UnsupportedEncodingException;

import com.letsfly.common.codec.AbelMD5;
import com.letsfly.common.codec.AbelSHA;
import com.letsfly.common.constant.GlobalConstant;

/**
 * 签名算法
 * @author kimhu
 * @date 2019/11/13
 * @version 1.0
 */
public final class SignUtil {
    
    /** 默认盐 */
    public static String DEFAULT_SALT;
    
    /**
     * 构造默认盐
     */
    static {
        try {
            byte[] bytes = new byte[]{-128, -64, -32, -16, -8, -4, -2, 0, 2, 4, 8, 16, 32, 64, 127};
            DEFAULT_SALT = new String(bytes, GlobalConstant.CHARSET_UTF8);
        } catch (Exception e) {
            throw new RuntimeException("signByMD5 Exception", e);
        }
    }
    
    /**
     * 私有化构造方法,防止外部实例化
     */
    private SignUtil() {}
    
    /**
     * MD5签名
     * @param message
     * @return
     */
    public static String signByMD5(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            return AbelMD5.md5(message);
        } catch (Exception e) {
            throw new RuntimeException("signByMD5 Exception", e);
        }
    }
    
    /**
     * MD5签名[16位结果]
     * @param message
     * @return
     */
    public static String signByMD5Hex(String message) {
        return signByMD5(message).substring(8, 24);
    }
    
    /**
     * MD5加盐签名
     * @param message 原数据
     * @param salt 盐
     * @return 数据签名
     */
    public static String signByMD5(String message, String salt) {
        if(StringUtil.isEmpty(message) || StringUtil.isEmpty(salt)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            return signByMD5(merge(message, salt));
        } catch (Exception e) {
            throw new RuntimeException("signByMD5.salt Exception", e);
        }
    }
    
    /**
     * MD5加盐签名[16位结果]
     * @param message 原数据
     * @param salt 盐
     * @return 数据签名
     */
    public static String signByMD5Hex(String message, String salt) {
        return signByMD5(message, salt).substring(8, 24);
    }
    
    /**
     * SHA256签名
     * @param message
     * @return
     */
    public static String signBySHA256(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            return AbelSHA.sha256(message);
        } catch (Exception e) {
            throw new RuntimeException("signBySHA256 Exception", e);
        }
    }
    
    /**
     * SHA256加盐签名
     * @param message 原数据
     * @param salt 盐
     * @return 数据签名
     */
    public static String signBySHA256(String message, String salt) {
        if(StringUtil.isEmpty(message) || StringUtil.isEmpty(salt)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            return signBySHA256(merge(message, salt));
        } catch (Exception e) {
            throw new RuntimeException("signBySHA256.salt Exception", e);
        }
    }
    
    /**
     * SHA384签名
     * @param message
     * @return
     */
    public static String signBySHA384(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            return AbelSHA.sha384(message);
        } catch (Exception e) {
            throw new RuntimeException("signBySHA384 Exception", e);
        }
    }
    
    /**
     * SHA384加盐签名
     * @param message 原数据
     * @param salt 盐
     * @return 数据签名
     */
    public static String signBySHA384(String message, String salt) {
        if(StringUtil.isEmpty(message) || StringUtil.isEmpty(salt)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            return signBySHA384(merge(message, salt));
        } catch (Exception e) {
            throw new RuntimeException("signBySHA384.salt Exception", e);
        }
    }
    
    /**
     * SHA512签名
     * @param message
     * @return
     */
    public static String signBySHA512(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            return AbelSHA.sha512(message);
        } catch (Exception e) {
            throw new RuntimeException("signBySHA512 Exception", e);
        }
    }
    
    /**
     * SHA512加盐签名
     * @param message 原数据
     * @param salt 盐
     * @return 数据签名
     */
    public static String signBySHA512(String message, String salt) {
        if(StringUtil.isEmpty(message) || StringUtil.isEmpty(salt)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            return signBySHA512(merge(message, salt));
        } catch (Exception e) {
            throw new RuntimeException("signBySHA512.salt Exception", e);
        }
    }
    
    /**
     * 原数据加盐
     * @param message 原数据
     * @param salt 盐
     * @return 加盐后结果
     * @throws UnsupportedEncodingException 
     */
    private static String merge(String message, String salt) throws UnsupportedEncodingException {
        byte[] bytes = message.getBytes(GlobalConstant.CHARSET_UTF8);
        byte[] saltBytes = salt.getBytes(GlobalConstant.CHARSET_UTF8);
        
        byte[] longBytes = bytes.length < saltBytes.length ? saltBytes : bytes;
        int minLength = bytes.length < saltBytes.length ? bytes.length : saltBytes.length;
        
        int resultLength = bytes.length + saltBytes.length;
        byte[] result = new byte[resultLength];
        
        int i = 0;
        for(int j=0; j<minLength; j++) {
            result[i++] = bytes[j];
            result[i++] = saltBytes[j];
        }
        
        for(int j=minLength; j<longBytes.length; j++) {
            result[i++] = longBytes[j];
        }
        
        return new String(result, GlobalConstant.CHARSET_UTF8);
    }
}
