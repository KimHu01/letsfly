package com.letsfly.common.codec;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.StringUtil;

/**
 * SHA(Secure Hash Algorithm, 安全散列算法)
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class AbelSHA {
    
    /**
     * 私有化构造函数，防止被外部实例化
     */
    private AbelSHA() {}
    
    /**
     * sha256
     * @param message
     * @return
     */
    public static String sha256(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(GlobalConstant.ALGORITHM_SHA_256);
            byte[] hash = messageDigest.digest(message.getBytes(GlobalConstant.CHARSET_UTF8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException("sha256 Exception", e);
        }
    }
    
    /**
     * sha384
     * @param message
     * @return
     */
    public static String sha384(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(GlobalConstant.ALGORITHM_SHA_384);
            byte[] hash = messageDigest.digest(message.getBytes(GlobalConstant.CHARSET_UTF8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException("sha384 Exception", e);
        }
    }
    
    /**
     * sha512
     * @param message
     * @return
     */
    public static String sha512(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(GlobalConstant.ALGORITHM_SHA_512);
            byte[] hash = messageDigest.digest(message.getBytes(GlobalConstant.CHARSET_UTF8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException("sha512 Exception", e);
        }
    }
}
