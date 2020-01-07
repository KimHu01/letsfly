package com.letsfly.mainstay.exception;

import java.io.Serializable;

/**
 * 核心业务自定义异常
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public class CoreException extends BizException implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 构造函数
     */
    public CoreException() {
        super();
    }
    
    /**
     * 构造函数
     */
    public CoreException(String message) {
        super(message);
    }
    
    /**
     * 构造函数
     */
    public CoreException(Throwable cause) {
        super(cause);
    }
    
    /**
     * 构造函数
     */
    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
