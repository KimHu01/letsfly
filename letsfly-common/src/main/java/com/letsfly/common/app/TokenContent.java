package com.letsfly.common.app;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Token
 * @author KimHu
 * @create 2019/11/20
 * @version 1.0
 */
public final class TokenContent {
    
    /** 设备ID */
    public static final transient String DEVICE_ID = "deviceId";
    
    /** 随机数 */
    public static final transient String CRYP_SEED = "crypSeed";
    
    /** timestamp */
    public static final transient String TIMESTAMP = "timestamp";
    
    /** sign */
    public static final transient String SIGN = "sign";
    
    /** 设备ID */
    private String deviceId;
    
    /** 随机数 */
    private String crypSeed;
    
    /** 时间戳 */
    private String timestamp;
    
    /** 签名 */
    private String sign;
    
    /** content内容 */
    private String content;
    
    /**
     * 构造函数
     */
    public TokenContent() {}
    
    /**
     * 解析json为Token对象
     * @param json
     * @return
     */
    public static AppToken parseJson(String json) {
        return JSONObject.parseObject(json, AppToken.class);
    }
    
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCrypSeed() {
        return crypSeed;
    }

    public void setCrypSeed(String crypSeed) {
        this.crypSeed = crypSeed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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
