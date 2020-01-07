package com.letsfly.mainstay.mvc.page.base;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Page抽象类
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public abstract class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteNonStringValueAsString);
    }
}
