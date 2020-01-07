package com.letsfly.mainstay.mvc;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.letsfly.mainstay.mvc.controller.AbstractController;

/**
 * 前后端http交互返回对象, 推荐是使用ofSuccess、ofFailure方法构造本对象
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public final class HttpResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 错误码(当且仅当errcode为0时，表示正确返回) */
	private Integer errcode;
	
	/** 错误消息(当errcode不为0时，errmsg表示出错原因) */
	private String errmsg;
	
	/** 返回结果(操作结果，如：查询结果等) */
	private T data;
	
	/**
	 * 构造函数
	 */
	public HttpResult() {}
	
	/**
	 * 构造函数
	 */
	public HttpResult(Integer errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}
	
	/**
	 * 构造函数
	 */
	public HttpResult(Integer errcode, String errmsg, T data) {
		this.errcode = errcode;
		this.errmsg = errmsg;
		this.data = data;
	}
	
	/**
     * 构造成功HttpResult
     * @return
     */
    public static HttpResult<?> ofSuccess() {
        return new HttpResult<String>(AbstractController.SUCCESS_CODE, AbstractController.SUCCESS_MSG);
    }
    
    /**
     * 构造成功HttpResult
     * @param data
     * @return
     */
    public static <T> HttpResult<T> ofSuccess(T data) {
        return new HttpResult<T>(AbstractController.SUCCESS_CODE, AbstractController.SUCCESS_MSG, data);
    }
    
    /**
     * 构造失败HttpResult
     * @return
     */
    public static HttpResult<?> ofFailure() {
        return new HttpResult<String>(AbstractController.FAILURE_CODE, AbstractController.FAILURE_MSG);
    }
    
    /**
     * 构造失败HttpResult
     * @param data
     * @return
     */
    public static <T> HttpResult<T> ofFailure(T data) {
        return new HttpResult<T>(AbstractController.FAILURE_CODE, AbstractController.FAILURE_MSG, data);
    }
    
    /**
     * 构造失败HttpResult
     * @param errcode
     * @param errmsg
     * @return
     */
    public static HttpResult<?> ofFailure(int errcode, String errmsg) {
        return new HttpResult<String>(errcode, errmsg);
    }
    
    /**
     * 构造失败HttpResult
     * @param errcode
     * @param errmsg
     * @param data
     * @return
     */
    public static <T> HttpResult<T> ofFailure(int errcode, String errmsg, T data) {
        return new HttpResult<T>(errcode, errmsg, data);
    }
	
	public Integer getErrcode() {
		return this.errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return this.errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this, SerializerFeature.WriteNonStringValueAsString, SerializerFeature.WriteDateUseDateFormat);
	}
}
