package com.letsfly.mainstay.mvc.controller.resolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.letsfly.common.annotation.JsonParam;
import com.letsfly.common.util.BeanUtil;
import com.letsfly.common.util.ClassUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 自定义http请求解析器[增强spring参数解析不足问题]
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public class ArbitraryMethodArgumentResolver implements HandlerMethodArgumentResolver {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(ArbitraryMethodArgumentResolver.class);
    
    private static final String HTTP_SERVLET_REQUEST_KEY = "http_request_arbitrary_method_argument_key";
    
    /**
     * 检查方法参数是否为本解析器所支持类型
     * @param parameter the method parameter to check
     * @return true/false
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonParam.class);
    }

    /**
     * 自定义解析方法参数
     * @param parameter the method parameter to resolve
     * @param mavContainer the ModelAndViewContainer for the current request
     * @param webRequest the current request
     * @param binderFactory a factory for creating {@link WebDataBinder} instances
     * @return the resolved argument value, or {@code null} if not resolvable
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        JsonParam jsonParam = parameter.getParameterAnnotation(JsonParam.class);
        
        String parameterName = parameter.getParameterName();
        Object parameterValue = this.getParameterValue(request, parameterName, jsonParam);
        if(null == parameterValue) {
            if(!jsonParam.required()) {
                return null;
            }
            
            logger.error(String.format("参数:{%s}不可为null", parameterName));
            throw new IllegalArgumentException(String.format("参数:{%s}不可为null", parameterName));
        }
        
        Class<?> parameterClazz = parameter.getParameterType();
        if(BeanUtil.hasClassLoador(parameterClazz)) {
            try {
                return parameterClazz.cast(parameterValue.toString());
            } catch (Exception e) {
                return ClassUtil.cast(parameterClazz, String.class, parameterValue.toString());
            }
        }
        
        return JSONObject.parseObject(parameterValue.toString(), parameterClazz);
    }
    
    /**
     * 获取参数值
     * @param request
     * @param parameterValues
     * @param jsonParam
     * @return
     */
    private Object getParameterValue(HttpServletRequest request, String parameterName, 
            JsonParam jsonParam) throws IOException {
        
        JSONObject parameterValues = this.getParameterValues(request);
        if(!jsonParam.prefix()) {
            return parameterValues.get(parameterName);
        }
        
        String separator = jsonParam.separator();
        JSONObject parameterValue = new JSONObject();
        
        for(Map.Entry<String, Object> entry : parameterValues.entrySet()) {
            String key = entry.getKey();
            
            if(key.startsWith(parameterName)) {
                parameterValue.put(key.substring(key.indexOf(separator) + 1), entry.getValue());
            }
        }
        
        return parameterValue;
    }
    
    /**
     * 获取http请求参数
     * @param request http请求
     * @return http请求参数
     * @throws IOException
     */
    private JSONObject getParameterValues(HttpServletRequest request) throws IOException {
        String method = request.getMethod();
        StringBuffer parameterValues = new StringBuffer();
        
        if(RequestMethod.GET.toString().equalsIgnoreCase(method)) {
            parameterValues.append(this.getParameterValuesForGet(request));
        }
        
        if(RequestMethod.POST.toString().equalsIgnoreCase(method)) {
            parameterValues.append(this.getParameterValuesForPost(request));
        }
        
        if(parameterValues.length() <= 0) {
            return new JSONObject();
        }
        
        logger.info("-------parameterValues----->" + parameterValues.toString());
        return JSONObject.parseObject(parameterValues.toString());
    }
    
    /**
     * GET方式获取请求参数
     * @param request
     * @return
     */
    private String getParameterValuesForGet(HttpServletRequest request) {
        //StringBuffer parameterValues = new StringBuffer();
        JSONObject parameterValues = new JSONObject();
        
        Map<String, String[]> parameterMap = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String value = entry.getValue().length == 1 ? entry.getValue()[0] : Arrays.toString(entry.getValue());
            parameterValues.put(entry.getKey(), value);
            //parameterValues.append(entry.getKey());
        }
        
        if(parameterValues.size() <= 0) {
            return new StringBuffer().toString();
        }
        
        return parameterValues.toString();
    }
    
    /**
     * POST方式获取请求参数
     * @param request
     * @return
     */
    private String getParameterValuesForPost(HttpServletRequest request) throws IOException {
        StringBuffer parameterValues = new StringBuffer();
        
        if(null != request.getAttribute(HTTP_SERVLET_REQUEST_KEY)) {
            parameterValues.append(request.getAttribute(HTTP_SERVLET_REQUEST_KEY));
        } else {
            BufferedReader reader = request.getReader();
            StringBuffer buffer = new StringBuffer();
            
            String line;
            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            
            request.setAttribute(HTTP_SERVLET_REQUEST_KEY, buffer.toString());
            parameterValues.append(buffer.toString());
        }
        
        return parameterValues.toString();
    }
}
