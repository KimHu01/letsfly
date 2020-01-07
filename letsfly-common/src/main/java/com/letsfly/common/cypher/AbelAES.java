package com.letsfly.common.cypher;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.letsfly.common.codec.AbelBase64;
import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.StringUtil;

/**
 * AES(AES加密算法, AES-128-ECB)[Key长度必须为16位]
 * @author kimhu
 * @create 2019/11/21
 * @version 1.0
 */
public class AbelAES {
    
    /**
     * 私有化构造函数，防止被外部实例化
     */
    private AbelAES() {}
    
    /**
     * 加密
     * @param key 密钥[长度必须为16位]
     * @param plainText 明文
     * @return
     */
    public static byte[] encrypt(String key, byte[] plainText) {
        if(StringUtil.isEmpty(key)) { //|| key.length() != 16
            throw new RuntimeException("key is illegal.");
        }
        
        if(null == plainText || plainText.length <= 0) {
            throw new RuntimeException("plainText can not be null.");
        }
        
        try {
            byte[] raw = key.getBytes(GlobalConstant.CHARSET_UTF8);
            SecretKeySpec keySpec = new SecretKeySpec(raw, GlobalConstant.ALGORITHM_AES);
            String transformation = GlobalConstant.ALGORITHM_AES.concat(GlobalConstant.SYMBOL_SOLIDUS)
                    .concat(GlobalConstant.ALGORITHM_AES_MODEL_ECB).concat(GlobalConstant.SYMBOL_SOLIDUS)
                    .concat(GlobalConstant.ALGORITHM_AES_PADDING_PKCS5);
            Cipher cipher = Cipher.getInstance(transformation); //算法/模式/补码方式
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] bytes = cipher.doFinal(plainText);
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 加密
     * @param key 密钥[长度必须为16位]
     * @param plainText 明文
     * @return
     */
    public static String encrypt(String key, String plainText) {
        try {
            byte[] bytes = encrypt(key, plainText.getBytes(GlobalConstant.CHARSET_UTF8));
            return AbelBase64.encodeUTF8(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 解密
     * @param key 密钥[长度必须为16位]
     * @param cipherText 密文
     * @return
     */
    public static byte[] decrypt(String key, byte[] cipherText) {
        if(StringUtil.isEmpty(key) ) { //|| key.length() != 16
            throw new RuntimeException("key is illegal.");
        }
        
        if(null == cipherText || cipherText.length <= 0) {
            throw new RuntimeException("cipherText can not be null.");
        }
        
        try {
            byte[] raw = key.getBytes(GlobalConstant.CHARSET_UTF8);
            SecretKeySpec keySpec = new SecretKeySpec(raw, GlobalConstant.ALGORITHM_AES);
            String transformation = GlobalConstant.ALGORITHM_AES.concat(GlobalConstant.SYMBOL_SOLIDUS)
                    .concat(GlobalConstant.ALGORITHM_AES_MODEL_ECB).concat(GlobalConstant.SYMBOL_SOLIDUS)
                    .concat(GlobalConstant.ALGORITHM_AES_PADDING_PKCS5);
            Cipher cipher = Cipher.getInstance(transformation); //算法/模式/补码方式
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] bytes = cipher.doFinal(cipherText);
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 解密
     * @param key 密钥[长度必须为16位]
     * @param cipherText 密文
     * @return
     */
    public static String decrypt(String key, String cipherText) {
        try {
            byte[] ctBytes = AbelBase64.decodeUTF8(cipherText);
            byte[] bytes = decrypt(key, ctBytes);
            return new String(bytes, GlobalConstant.CHARSET_UTF8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
