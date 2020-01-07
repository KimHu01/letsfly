package com.letsfly.common.codec;

import java.security.MessageDigest;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.StringUtil;

/**
 * RIPEMD(RACE Integrity Primitives Evaluation Message Digest, RACE原始完整性校验消息摘要)
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class AbelRIPEMD {
    
    /**
     * 私有化构造函数，防止被外部实例化
     */
    private AbelRIPEMD() {}
    
    /**
     * RipeMD128
     * @param message
     * @return 
     */
    public static String ripeMD128(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            if(null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {
                Security.addProvider(new BouncyCastleProvider());
            }
            
            MessageDigest messageDigest = MessageDigest.getInstance(GlobalConstant.ALGORITHM_RIPEMD_128);
            byte[] hash = messageDigest.digest(message.getBytes(GlobalConstant.CHARSET_UTF8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException("No Such Algorithm or Unsupported Encoding", e);
        }
    }
    
    /**
     * RipeMD160
     * @param message
     * @return 
     */
    public static String ripeMD160(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            if(null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {
                Security.addProvider(new BouncyCastleProvider());
            }
            
            MessageDigest messageDigest = MessageDigest.getInstance(GlobalConstant.ALGORITHM_RIPEMD_160);
            byte[] hash = messageDigest.digest(message.getBytes(GlobalConstant.CHARSET_UTF8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException("No Such Algorithm or Unsupported Encoding", e);
        }
    }
    
    /**
     * RipeMD256
     * @param message
     * @return 
     */
    public static String ripeMD256(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            if(null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {
                Security.addProvider(new BouncyCastleProvider());
            }
            
            MessageDigest messageDigest = MessageDigest.getInstance(GlobalConstant.ALGORITHM_RIPEMD_256);
            byte[] hash = messageDigest.digest(message.getBytes(GlobalConstant.CHARSET_UTF8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException("No Such Algorithm or Unsupported Encoding", e);
        }
    }
    
    /**
     * RipeMD320
     * @param message
     * @return 
     */
    public static String ripeMD320(String message) {
        if(StringUtil.isEmpty(message)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        try {
            if(null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {
                Security.addProvider(new BouncyCastleProvider());
            }
            
            MessageDigest messageDigest = MessageDigest.getInstance(GlobalConstant.ALGORITHM_RIPEMD_320);
            byte[] hash = messageDigest.digest(message.getBytes(GlobalConstant.CHARSET_UTF8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException("No Such Algorithm or Unsupported Encoding", e);
        }
    }
}
