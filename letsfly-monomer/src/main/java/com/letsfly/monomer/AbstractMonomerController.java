package com.letsfly.monomer;

import com.alibaba.fastjson.JSONObject;
import com.letsfly.common.util.StringUtil;
import com.letsfly.mainstay.mvc.controller.AbstractController;

/**
 * Controller抽象实现[单体应用-PC端]
 * @author KimHu
 * @create 2019/11/14
 * @version 1.0
 */
public abstract class AbstractMonomerController extends AbstractController {
    
    protected static final String LOGIN_USER_IN_SESSION_KEY = "login_user_in_session_key";
    
    /**
     * 设置对象到session中
     * @param name
     * @param value
     */
    @Override
    public void setSessionValue(String name, String value) {
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(value)) {
            throw new IllegalArgumentException("name and value can not be null.");
        }
        
        this.getRequest().getSession().setAttribute(name, value);
    }
    
    /**
     * 从session中获取对象
     * @return
     */
    @Override
    public Object getSession() {
        return this.getRequest().getSession();
    }
    
    /**
     * 从session中获取对象
     * @param name
     * @return
     */
    @Override
    public String getSessionValue(String name) {
        if(StringUtil.isEmpty(name)) {
            throw new IllegalArgumentException("name can not be null.");
        }
        
        return this.getRequest().getSession().getAttribute(name).toString();
    }
    
    /**
     * 从session中删除数据
     * @param name
     * @return
     */
    @Override
    public boolean removeSessionValue(String name) {
        if(StringUtil.isEmpty(name)) {
            throw new IllegalArgumentException("name can not be null.");
        }
        
        this.getRequest().getSession().removeAttribute(name);
        return true;
    }
    
    /**
     * 清空session
     * @return
     */
    @Override
    public boolean clearSession() {
        this.getRequest().getSession().invalidate();
        return true;
    }
    
    /**
     * 获取当前登录用户
     * @param key 传null即可, 忽略该参数
     * @param clazz
     * @return
     */
    @Override
    public <T> T getLoginUser(String key, Class<T> clazz) {
        if(StringUtil.isEmpty(key) || null == clazz) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        String loginUserJson = this.getRequest().getSession().getAttribute(LOGIN_USER_IN_SESSION_KEY).toString();
        return JSONObject.parseObject(loginUserJson, clazz);
    }
    
    /**
     * 设置当前登录用户
     * @param key 传null即可, 忽略该参数
     * @param t
     */
    @Override
    public <T> void setLoginUser(String key, T t) {
        if(StringUtil.isEmpty(key) || null == t) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        String loginUserJson = JSONObject.toJSONString(t);
        this.getRequest().getSession().setAttribute(LOGIN_USER_IN_SESSION_KEY, loginUserJson);
    }
}
