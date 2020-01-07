package com.letsfly.common.codec;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.StringUtil;

/**
 * MD5(Message Digest Algorithm MD5, 消息摘要算法)
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class AbelMD5 {
    
    /**
     * 私有化构造函数，防止被外部实例化
     */
    private AbelMD5() {}
    
    /**
     * MD5
     * @param bytes
     * @return
     */
    public static String md5(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(GlobalConstant.ALGORITHM_MD5);
            byte[] hash = messageDigest.digest(message.getBytes(GlobalConstant.CHARSET_UTF8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException("MD5 Exception", e);
        }
    }
}
