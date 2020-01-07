package com.letsfly.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.StringUtil;

/**
 * properties配置文件解析器
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class ConfigResolver {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(ConfigResolver.class);
    
    /**
     * 私有化构造函数, 防止被外部实例化
     */
    private ConfigResolver() {};
    
    /**
     * 获取ConfigLoader对象
     * @return
     */
    public static ConfigResolver getInstance() {
        return new ConfigResolver();
    }
    
    /**
     * 根据相对src的相对路径返回配置文件
     * @param path 文件路径，如：classpath
     * @return
     */
    public Properties getProperties(String path) {
        if(StringUtil.isEmpty(path)) {
            throw new IllegalArgumentException("Illegal Parameter.");
        }
        
        InputStream is = null;
        Reader reader = null;
        
        try {
            is = new ClassPathResource(path).getInputStream();
            reader = new InputStreamReader(is, GlobalConstant.CHARSET_UTF8);
            
            Properties properties = new Properties();
            properties.load(reader);
            
            return properties;
        } catch (IOException e) {
            logger.error("properties load faild", e);
            throw new RuntimeException("properties load faild", e);
        } finally {
            if(null != is) {
                try {
                    is.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            
            if(null != reader) {
                try {
                    reader.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
