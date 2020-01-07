package com.letsfly.mainstay.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.StringUtil;
import com.letsfly.mainstay.context.AbelHttpServletContext;

/**
 * Abel System拦截器
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class AbelSystemInterceptor extends HandlerInterceptorAdapter {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(AbelSystemInterceptor.class);
    
    /**
     * 预处理
     * @param request
     * @param response
     * @param handler
     * @return 
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
            Object handler) throws Exception {
        String traceId = request.getHeader(GlobalConstant.ABEL_TRACE_ID);
        if (StringUtil.isEmpty(traceId)) {
            traceId = StringUtil.getUUID();
        }
        
        MDC.put(GlobalConstant.LOG_TRACE_ID, traceId);
        AbelHttpServletContext.setHttpContext(request, response);
        logger.info("HttpContext preHandle......");
        return super.preHandle(request, response, handler);
    }
    
    /**
     * 后处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return 
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
            Object handler, Exception ex) throws Exception {
    	AbelHttpServletContext.remove();
    	logger.info("HttpContext afterCompletion......");
        super.afterCompletion(request, response, handler, ex);
    }
}
