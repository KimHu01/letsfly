package com.letsfly.common.app;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.letsfly.common.util.SignUtil;
import com.letsfly.common.util.StringUtil;

/**
 * Token
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public final class AppToken implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** 设备ID */
    public static final transient String DEVICE_ID = "deviceId";
    
    /** timestamp */
    public static final transient String TIMESTAMP = "timestamp";
    
    /** 随机数密钥[16为长度] */
    public static final transient String CRYP_SEED = "crypSeed";
    
    /** token ID */
    public static final transient String TOKEN_ID = "tokenId";
    
    /** sign */
    public static final transient String SIGN = "sign";
    
    /** timestamp有效间隔(单位:毫秒) */
    public static final transient int TIMESTAMP_EXPIRY = 600 * 1000;
    
    /** token有效时长为X秒(单位:秒) */
    public static final transient int TOKEN_EXPIRY = 2 * 60 * 60;
    
    /** token前缀 */
    public static final transient String TOKEN_PREFIX = "token_";
    
    /** token过期后的备份缓存key过期时间(单位:秒) */
    public static final transient int TOKEN_OVERDUE_EXPIRY = 15 * 24 * 60 * 60;
    
    /** token过期之后的key前缀 */
    public static final transient String TOKEN_OVERDUE_SUFFIX = "_OVERDUE";
    
    /** token */
    private String tokenId;
    
    /** token content */
    private String content;
    
    /**
     * 构造函数
     */
    public AppToken() {
        this.tokenId = TOKEN_PREFIX.concat(SignUtil.signByMD5(StringUtil.getUUID()));
    }
    
    /**
     * 构造函数
     * @param content
     */
    public AppToken(String content) {
        this.tokenId = TOKEN_PREFIX.concat(SignUtil.signByMD5(StringUtil.getUUID()));
        this.content = content;
    }
    
    /**
     * 重新设置tokenId
     */
    public void reloadTokenId() {
        this.tokenId = TOKEN_PREFIX.concat(SignUtil.signByMD5(StringUtil.getUUID()));
    }
    
    public String getTokenId() {
        return tokenId;
    }
    
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteNonStringValueAsString);
    }
}
