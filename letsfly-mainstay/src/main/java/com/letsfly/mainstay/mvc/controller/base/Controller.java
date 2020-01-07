package com.letsfly.mainstay.mvc.controller.base;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller顶级接口定义
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public interface Controller {
    
    /**
     * 转发URL
     * @param url
     * @return
     */
    String forwardUrl(String url);
    
    /**
     * 重定向URL
     * @param url
     * @return
     */
    String redirectUrl(String url);
    
    /**
     * 获取cookie
     * @param name
     * @return
     */
    Cookie getCookie(String name);
    
    /**
     * 获取cookie value
     * @param name
     * @return
     */
    String getCookieValue(String name);
    
    /**
     * 添加cookie值到response中
     * @param name 键
     * @param value 值
     * @param expiry 有效期(可为空, 单位:秒)
     * @param domain 域(可为空)
     */
    void addCookie(String name, String value, Integer expiry, String domain);
    
    /**
     * 添加一个生命周期为当前浏览器的cookie
     * @param name 键
     * @param value 值
     */
    void addCookie(String name, String value);
    
    /**
     * 清空cookie
     * @param name cookie键
     */
    void clearCookie(String name);
    
    /**
     * 设置对象到session中
     * @param name
     * @param value
     */
    void setSessionValue(String name, String value);
    
    /**
     * 从session中获取对象
     * @return
     */
    Object getSession();
    
    /**
     * 从session中获取对象
     * @param name
     * @return
     */
    String getSessionValue(String name);
    
    /**
     * 从session中删除数据
     * @param name
     * @return
     */
    boolean removeSessionValue(String name);
    
    /**
	 * 清空session
	 * @return
	 */
	boolean clearSession();
	
    /**
     * 获取request
     * @return
     */
    HttpServletRequest getRequest();
    
    /**
     * 获取response
     * @return
     */
    HttpServletResponse getResponse();
    
    /**
     * 获取请求method
     * @return
     */
    String getMethod();
    
    /**
     * 获取请求参数
     * @return
     */
    Map<String, String[]> getParameters();
    
    /**
     * 获取请求参数
     * @param name 参数名
     * @return
     */
    String getParameter(String name);
    
    /**
     * 获取当前登录用户
     * @param key
     * @param clazz
     * @return
     */
    <T> T getLoginUser(String key, Class<T> clazz);
    
    /**
     * 设置当前登录用户
     * @param key
     * @param t
     */
    <T> void setLoginUser(String key, T t);
}
