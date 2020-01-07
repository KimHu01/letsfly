package com.letsfly.mainstay.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abel System过滤器
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class AbelSystemFilter implements Filter {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(AbelSystemFilter.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        try {
            logger.info("---start--->" + request.getMethod() + ", " + request.getRequestURL());
            chain.doFilter(request, response);
        } finally {
            logger.info("---end----->" + request.getMethod() + ", " + request.getRequestURL());
        }
    }
    
    @Override
    public void destroy() {}
}
