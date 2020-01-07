package com.letsfly.common.model.base;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 抽象PO定义
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public abstract class AbstractPO implements PO, Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteNonStringValueAsString);
    }
}
