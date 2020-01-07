package com.letsfly.mainstay.mvc.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.StringUtil;
import com.letsfly.mainstay.context.AbelHttpServletContext;
import com.letsfly.mainstay.mvc.controller.base.Controller;

/**
 * Controller抽象实现
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public abstract class AbstractController implements Controller {
    
    /** 日志工具 */
    protected Log logger = LogFactory.getLog(getClass());
    
    /** HTTP交互success code */
    public static final int SUCCESS_CODE = 0;
    
    /** HTTP交互success msg */
    public static final String SUCCESS_MSG = "success";
    
    /** HTTP交互failure code */
	public static final int FAILURE_CODE = -1;
	
	/** HTTP交互failure msg */
	public static final String FAILURE_MSG = "failure";
	
    /** 转发URL前缀 */
    protected static StringBuffer FORWARD_PROFIX = new StringBuffer("forward:");
    
    /** 重定向URL前缀 */
    protected static StringBuffer REDIRECT_PROFIX = new StringBuffer("redirect:");
    
    /**
     * 转发URL
     * @param url
     * @return
     */
    @Override
    public String forwardUrl(String url) {
        if(StringUtil.isEmpty(url)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        return FORWARD_PROFIX.append(url).toString();
    }
    
    /**
     * 重定向URL
     * @param url
     * @return
     */
    @Override
    public String redirectUrl(String url) {
        if(StringUtil.isEmpty(url)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        return REDIRECT_PROFIX.append(url).toString();
    }
    
    /**
     * 获取cookie
     * @param name
     * @return
     */
    @Override
    public Cookie getCookie(String name) {
        if(StringUtil.isEmpty(name)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        Cookie[] cookies = this.getRequest().getCookies();
        for(int i=0; i<cookies.length; i++) {
            if(name.equals(cookies[i].getName())) {
                return cookies[i];
            }
        }
        
        return null;
    }
    
    /**
     * 获取cookie value
     * @param name
     * @return
     */
    @Override
    public String getCookieValue(String name) {
        if(StringUtil.isEmpty(name)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        Cookie cookie = this.getCookie(name);
        return null == cookie ? null : cookie.getValue();
    }
    
    /**
     * 添加cookie值到response中
     * @param name 键
     * @param value 值
     * @param expiry 有效期(可为空, 单位:秒)
     * @param domain 域(可为空)
     */
    @Override
    public void addCookie(String name, String value, Integer expiry, String domain) {
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(value)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        Cookie cookie = new Cookie(name, value);
        if(null != expiry) {
            cookie.setMaxAge(expiry.intValue());
        }
        
        if(StringUtil.isNotEmpty(domain)) {
            cookie.setDomain(domain);
        }
        
        cookie.setPath(GlobalConstant.SYMBOL_SOLIDUS);
        this.getResponse().addCookie(cookie);
    }
    
    /**
     * 添加一个生命周期为当前浏览器的cookie
     * @param name 键
     * @param value 值
     */
    @Override
    public void addCookie(String name, String value) {
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(value)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        this.addCookie(name, value, null, null);
    }
    
    /**
     * 清空cookie
     * @param name cookie键
     */
    @Override
    public void clearCookie(String name) {
        if(StringUtil.isEmpty(name)) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        Cookie[] cookies = this.getRequest().getCookies();
        for(int i=0; i<cookies.length; i++) {
            if(name.equals(cookies[i].getName())) {
                Cookie cookie = new Cookie(cookies[i].getName(), cookies[i].getValue());
                cookie.setMaxAge(0);
                cookie.setPath(GlobalConstant.SYMBOL_SOLIDUS);
                
                this.getResponse().addCookie(cookie);
            }
        }
    }
    
    /**
     * 设置对象到session中
     * @param name
     * @param value
     */
    @Override
    public abstract void setSessionValue(String name, String value);
    
    /**
     * 从session中获取对象
     * @return
     */
    @Override
    public abstract Object getSession();
    
    /**
     * 从session中获取对象
     * @param name
     * @return
     */
    @Override
    public abstract String getSessionValue(String name);
    
    /**
     * 从session中删除数据
     * @param name
     * @return
     */
    @Override
    public abstract boolean removeSessionValue(String name);
    
    /**
     * 清空session
     * @return
     */
    @Override
    public abstract boolean clearSession();
    
    /**
     * 获取request
     * @return
     */
    @Override
    public HttpServletRequest getRequest() {
        return AbelHttpServletContext.getRequest();
    }
    
    /**
     * 获取response
     * @return
     */
    @Override
    public HttpServletResponse getResponse() {
        return AbelHttpServletContext.getResponse();
    }
    
    /**
     * 获取请求method
     * @return
     */
    @Override
    public String getMethod() {
        return this.getRequest().getMethod();
    }
    
    /**
     * 获取请求参数
     * @return
     */
    @Override
    public Map<String, String[]> getParameters() {
        return this.getRequest().getParameterMap();
    }
    
    /**
     * 获取请求参数
     * @param name 参数名
     * @return
     */
    @Override
    public String getParameter(String name) {
        return this.getRequest().getParameter(name);
    }
    
    /**
     * 获取当前登录用户
     * @param key
     * @param clazz
     * @return
     */
    @Override
    public abstract <T> T getLoginUser(String key, Class<T> clazz);
    
    /**
     * 设置当前登录用户
     * @param key
     * @param t
     */
    @Override
    public abstract <T> void setLoginUser(String key, T t);
}
