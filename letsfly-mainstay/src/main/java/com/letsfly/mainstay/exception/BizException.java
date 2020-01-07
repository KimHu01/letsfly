package com.letsfly.mainstay.exception;

import java.io.Serializable;

/**
 * 业务异常
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public class BizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 构造函数
     */
    public BizException() {
        super();
    }
    
    /**
     * 构造函数
     */
    public BizException(String message) {
        super(message);
    }
    
    /**
     * 构造函数
     */
    public BizException(Throwable cause) {
        super(cause);
    }
    
    /**
     * 构造函数
     */
    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
