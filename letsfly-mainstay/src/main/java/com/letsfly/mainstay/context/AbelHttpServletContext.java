package com.letsfly.mainstay.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abel自定义HttpServlet上下文设置
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class AbelHttpServletContext {
    
    /** 绑定自定义AbelHttpContext到线程级 */
    private static ThreadLocal<AbelHttpContext> localHttpContext = new ThreadLocal<AbelHttpContext>();
    
    /**
     * 绑定请求/响应到线程
     * @param request
     * @param response
     */
    public static void setHttpContext(HttpServletRequest request, HttpServletResponse response) {
        localHttpContext.set(new AbelHttpContext(request, response));
    }
    
    /**
     * 获取HttpServletRequest
     * @return
     */
    public static HttpServletRequest getRequest(){
        return localHttpContext.get().getRequest();
    }
    
    /**
     * 获取HttpServletResponse
     * @return
     */
    public static HttpServletResponse getResponse(){
        return localHttpContext.get().getResponse();
    }
    
    /**
     * 清除
     */
    public static void remove(){
        localHttpContext.remove();
    }
}
