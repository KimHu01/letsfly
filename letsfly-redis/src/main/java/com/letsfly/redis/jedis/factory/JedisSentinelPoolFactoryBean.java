package com.letsfly.redis.jedis.factory;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.StringUtil;

import redis.clients.jedis.JedisSentinelPool;

/**
 * JedisSentinelPool
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class JedisSentinelPoolFactoryBean implements FactoryBean<JedisSentinelPool>, InitializingBean {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(JedisSentinelPoolFactoryBean.class);
    
    /** 地址 */
    private String addresses;
    
    /** 超时时间 */
    private Integer timeout;
    
    /** 密码 */
    private String password;
    
    /** masterName */
    private String masterName;
    
    /** 连接池配置 */
    private GenericObjectPoolConfig<Object> poolConfig;
    
    /** jedisSentinelPool */
    private JedisSentinelPool jedisSentinelPool;
    
    /**
     * 构造函数
     */
    public JedisSentinelPoolFactoryBean() {}
    
    @Override
    public void afterPropertiesSet() throws Exception {
        if(StringUtil.isEmpty(addresses)) {
            new IllegalArgumentException("Redis配置[address]异常");
        }
        
        Set<String> sentinels = this.getSentinels(addresses);
        jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig, timeout, password);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> jedisSentinelPool.close()));
        logger.info("初始化jedisSentinelPool完成...");
    }
    
    /**
     * 组装Sentinels
     * @param addresses
     * @return
     */
    private Set<String> getSentinels(String addresses) {
        Set<String> sentinels = new HashSet<String>();
        String[] hostPorts = addresses.trim().split(GlobalConstant.SYMBOL_COMMA);
        
        for(String hostPort : hostPorts) {
            sentinels.add(hostPort);
        }
        
        return sentinels;
    }
    
    @Override
    public JedisSentinelPool getObject() throws Exception {
        return this.jedisSentinelPool;
    }
    
    @Override
    public Class<? extends JedisSentinelPool> getObjectType() {
        return null == this.jedisSentinelPool ? JedisSentinelPool.class : this.jedisSentinelPool.getClass();
    }
    
    @Override
    public boolean isSingleton() {
        return true;
    }
    
    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public void setPoolConfig(GenericObjectPoolConfig<Object> poolConfig) {
        this.poolConfig = poolConfig;
    }
}
