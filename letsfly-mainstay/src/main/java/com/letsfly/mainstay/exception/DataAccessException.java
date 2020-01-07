package com.letsfly.mainstay.exception;

/**
 * 数据访问层自定义异常
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public class DataAccessException extends CoreException {
    private static final long serialVersionUID = 1L;
    
    /**
     * 构造函数
     */
    public DataAccessException() {
        super();
    }
    
    /**
     * 构造函数
     */
    public DataAccessException(String message) {
        super(message);
    }
    
    /**
     * 构造函数
     */
    public DataAccessException(Throwable cause) {
        super(cause);
    }
    
    /**
     * 构造函数
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
