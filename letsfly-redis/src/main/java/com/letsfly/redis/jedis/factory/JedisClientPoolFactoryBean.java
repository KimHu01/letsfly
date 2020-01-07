package com.letsfly.redis.jedis.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.util.StringUtil;

import redis.clients.jedis.JedisPool;

/**
 * JedisPool
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class JedisClientPoolFactoryBean implements FactoryBean<JedisPool>, InitializingBean {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(JedisClientPoolFactoryBean.class);
    
    /** 地址 */
    private String addresses;
    
    /** 超时时间 */
    private Integer timeout;
    
    /** 密码 */
    private String password;
    
    /** 连接池配置 */
    private GenericObjectPoolConfig<Object> poolConfig;
    
    /** jedisPool */
    private JedisPool jedisPool;
    
    /**
     * 构造函数
     */
    public JedisClientPoolFactoryBean() {}
    
    @Override
    public void afterPropertiesSet() throws Exception {
        if(StringUtil.isEmpty(addresses)) {
            new IllegalArgumentException("Redis配置[address]异常");
        }
        
        String host = addresses.split(GlobalConstant.SYMBOL_COLON)[0];
        int port = Integer.parseInt(addresses.split(GlobalConstant.SYMBOL_COLON)[1]);
        jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> jedisPool.close()));
        logger.info("初始化jedisPool完成...");
    }
    
    @Override
    public JedisPool getObject() throws Exception {
        return this.jedisPool;
    }
    
    @Override
    public Class<? extends JedisPool> getObjectType() {
        return null == this.jedisPool ? JedisPool.class : this.jedisPool.getClass();
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

    public void setPoolConfig(GenericObjectPoolConfig<Object> poolConfig) {
        this.poolConfig = poolConfig;
    }
}
