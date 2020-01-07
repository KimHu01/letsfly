package com.letsfly.mainstay.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abel System ServletContext监听器
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class AbelSystemServletContextListener implements ServletContextListener {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(AbelSystemServletContextListener.class);
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Context Initialized...........");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Context Destroyed...........");
    }
}
