package com.letsfly.mainstay.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abel自定义HttpContext上下文
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class AbelHttpContext {
    
    /** http请求 */
    private HttpServletRequest request;
    
    /** http响应 */
    private HttpServletResponse response;
    
    /**
     * 构造方法
     */
    public AbelHttpContext() {}
    
    /**
     * 构造方法
     * @param request
     * @param response
     */
    public AbelHttpContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    public HttpServletRequest getRequest() {
        return request;
    }
    
    public HttpServletResponse getResponse() {
        return response;
    }
}
