package com.letsfly.common.cypher;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.letsfly.common.codec.AbelBase64;
import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.RandomUtil;
import com.letsfly.common.util.StringUtil;

/**
 * RSA(RSA加密算法)[cipherText Data must not be longer than 128 bytes, plainText Data must not be longer than 117 bytes]
 * @author kimhu
 * @create 2019/11/20
 * @version 1.0
 */
public class AbelRSA {
    
    /**
     * 私有化构造函数，防止被外部实例化
     */
    private AbelRSA() {}
    
    /**
     * 生成密钥对
     * @return
     */
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(GlobalConstant.ALGORITHM_RSA);
            keyPairGenerator.initialize(GlobalConstant.ALGORITHM_RSA_KEY_SIZE1024, new SecureRandom());
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * encode key[公钥/私钥转字符串]
     * @param key 公钥/私钥
     * @return
     */
    public static String encodeKey(Key key) {
        if(null == key) {
            throw new RuntimeException("key can not be null.");
        }
        
        return AbelBase64.encodeUTF8(key.getEncoded());
    }
    
    /**
     * decode publicKey[字符串公钥转对象]
     * @param publicKey
     * @return
     */
    public static PublicKey decodePublicKey(String publicKey) {
        if(StringUtil.isEmpty(publicKey)) {
            throw new RuntimeException("publicKey can not be null.");
        }
        
        try {
            KeySpec keySpec = new X509EncodedKeySpec(AbelBase64.decodeUTF8(publicKey));
            return KeyFactory.getInstance(GlobalConstant.ALGORITHM_RSA).generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * decode privateKey[字符串私钥转对象]
     * @param privateKey
     * @return
     */
    public static PrivateKey decodePrivateKey(String privateKey) {
        if(StringUtil.isEmpty(privateKey)) {
            throw new RuntimeException("privateKey can not be null.");
        }
        
        try {
            KeySpec keySpec = new PKCS8EncodedKeySpec(AbelBase64.decodeUTF8(privateKey));
            return KeyFactory.getInstance(GlobalConstant.ALGORITHM_RSA).generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 验证公钥/私钥对儿是否匹配[采用随机生成字符串加解密比对完成,要注意效率]
     * @param publicKey
     * @param privateKey
     * @return
     */
    public static boolean verify(String publicKey, String privateKey) {
        if(StringUtil.isEmpty(publicKey) || StringUtil.isEmpty(privateKey)) {
            throw new RuntimeException("publicKey & privateKey can not be null.");
        }
        
        return verify(decodePublicKey(publicKey), decodePrivateKey(privateKey));
    }
    
    /**
     * 验证公钥/私钥对儿是否匹配[采用随机生成字符串加解密比对完成,要注意效率]
     * @param publicKey
     * @param privateKey
     * @return
     */
    public static boolean verify(PublicKey publicKey, PrivateKey privateKey) {
        if(null == publicKey || null == privateKey) {
            throw new RuntimeException("publicKey & privateKey can not be null.");
        }
        
        String plainText = RandomUtil.randomizeAlphanum(64);
        String cipherTextResult = encrypt(publicKey, plainText);
        String plainTextResult = decrypt(privateKey, cipherTextResult);
        
        return plainText.equals(plainTextResult);
    }
    
    /**
     * PublicKey加密
     * @param publicKey 公钥
     * @param plainText 明文[Data must not be longer than 117 bytes]
     * @return
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] plainText) {
        if(null == publicKey || null == plainText || plainText.length <= 0) {
            throw new RuntimeException("publicKey & plainText can not be null.");
        }
        
        try {
            Cipher cipher = Cipher.getInstance(GlobalConstant.ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] cipherText = cipher.doFinal(plainText);
            return cipherText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * privateKey解密
     * @param privateKey 私钥
     * @param cipherText 密文[Data must not be longer than 128 bytes]
     * @return
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] cipherText) {
        if(null == privateKey || null == cipherText || cipherText.length <= 0) {
            throw new RuntimeException("privateKey & cipherText can not be null.");
        }
        
        try {
            Cipher cipher = Cipher.getInstance(GlobalConstant.ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] plainText = cipher.doFinal(cipherText);
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * PrivateKey加密
     * @param privateKey 私钥
     * @param plainText 明文[Data must not be longer than 117 bytes]
     * @return
     */
    public static byte[] encrypt(PrivateKey privateKey, byte[] plainText) {
        if(null == privateKey || null == plainText || plainText.length <= 0) {
            throw new RuntimeException("publicKey & plainText can not be null.");
        }
        
        try {
            Cipher cipher = Cipher.getInstance(GlobalConstant.ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] cipherText = cipher.doFinal(plainText);
            return cipherText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * publicKey解密
     * @param publicKey 公钥
     * @param cipherText 密文[Data must not be longer than 128 bytes]
     * @return
     */
    public static byte[] decrypt(PublicKey publicKey, byte[] cipherText) {
        if(null == publicKey || null == cipherText || cipherText.length <= 0) {
            throw new RuntimeException("privateKey & cipherText can not be null.");
        }
        
        try {
            Cipher cipher = Cipher.getInstance(GlobalConstant.ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] plainText = cipher.doFinal(cipherText);
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * publicKey加密
     * @param publicKey 公钥
     * @param plainText 明文[Data must not be longer than 117 bytes]
     * @return
     */
    public static String encrypt(PublicKey publicKey, String plainText) {
        if(null == publicKey || StringUtil.isEmpty(plainText)) {
            throw new RuntimeException("publicKey & plainText can not be null.");
        }
        
        try {
            byte[] bytes = encrypt(publicKey, plainText.getBytes(GlobalConstant.CHARSET_UTF8));
            return AbelBase64.encodeUTF8(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * privateKey解密
     * @param privateKey 私钥
     * @param cipherText 密文[Data must not be longer than 128 bytes]
     * @return
     */
    public static String decrypt(PrivateKey privateKey, String cipherText) {
        if(null == privateKey || StringUtil.isEmpty(cipherText)) {
            throw new RuntimeException("privateKey & cipherText can not be null.");
        }
        
        try {
            byte[] ctBytes = AbelBase64.decodeUTF8(cipherText);
            byte[] bytes = decrypt(privateKey, ctBytes);
            return new String(bytes, GlobalConstant.CHARSET_UTF8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * privateKey加密
     * @param privateKey 私钥
     * @param plainText 明文[Data must not be longer than 117 bytes]
     * @return
     */
    public static String encrypt(PrivateKey privateKey, String plainText) {
        if(null == privateKey || StringUtil.isEmpty(plainText)) {
            throw new RuntimeException("publicKey & plainText can not be null.");
        }
        
        try {
            byte[] bytes = encrypt(privateKey, plainText.getBytes(GlobalConstant.CHARSET_UTF8));
            return AbelBase64.encodeUTF8(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * publicKey解密
     * @param publicKey 公钥
     * @param cipherText 密文[Data must not be longer than 128 bytes]
     * @return
     */
    public static String decrypt(PublicKey publicKey, String cipherText) {
        if(null == publicKey || StringUtil.isEmpty(cipherText)) {
            throw new RuntimeException("privateKey & cipherText can not be null.");
        }
        
        try {
            byte[] ctBytes = AbelBase64.decodeUTF8(cipherText);
            byte[] bytes = decrypt(publicKey, ctBytes);
            return new String(bytes, GlobalConstant.CHARSET_UTF8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
