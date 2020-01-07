package com.letsfly.common.codec;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.letsfly.common.constant.GlobalConstant;

/**
 * Base64
 * @author kimhu
 * @create 2019/11/20
 * @version 1.0
 */
public class AbelBase64 extends Base64 {
    
    /**
     * 私有化构造函数，防止被外部实例化
     */
    private AbelBase64() {}
    
    /**
     * base64编码
     * @param bytes
     * @return
     */
    public static String encodeUTF8(byte[] bytes) {
        try {
            return new String(Base64.encodeBase64(bytes), GlobalConstant.CHARSET_UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * base64解码
     * @param text
     * @return
     */
    public static byte[] decodeUTF8(String text) {
        try {
            return Base64.decodeBase64(text.getBytes(GlobalConstant.CHARSET_UTF8));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
