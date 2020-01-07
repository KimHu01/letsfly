package com.letsfly.mainstay.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * 自定义DispatcherServlet
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class DispatcherServletDS extends DispatcherServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * 处理方法
     */
    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("DispatcherServletDS->doService");
        super.doService(request, response);
    }
}
