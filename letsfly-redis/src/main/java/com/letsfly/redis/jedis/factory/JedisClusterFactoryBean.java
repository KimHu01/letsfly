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

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Jedis Cluster
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class JedisClusterFactoryBean implements FactoryBean<JedisCluster>, InitializingBean {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(JedisClusterFactoryBean.class);
    
    /** 地址 */
    private String addresses;
    
    /** 超时时间 */
    private Integer timeout;
    
    /** 最大重定向数 */
    private Integer maxRedirections;
    
    /** 密码 */
    private String password;
    
    /** 连接池配置 */
    private GenericObjectPoolConfig<Object> poolConfig;
    
    /** jedisCluster */
    private JedisCluster jedisCluster;
    
    /**
     * 构造函数
     */
    public JedisClusterFactoryBean() {}
    
    @Override
    public void afterPropertiesSet() throws Exception {
        if(StringUtil.isEmpty(addresses)) {
            new IllegalArgumentException("Redis配置[address]异常");
        }
        
        Set<HostAndPort> hostAndPort = this.getHostAndPort(addresses);
        this.jedisCluster = new JedisCluster(hostAndPort, timeout, timeout, maxRedirections, password, poolConfig);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            jedisCluster.close();
        }));
        
        logger.info("初始化jedisCluster完成...");
    }
    
    /**
     * 组装HostAndPort
     * @param addresses
     * @return
     */
    private Set<HostAndPort> getHostAndPort(String addresses) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        String[] hostPorts = addresses.trim().split(GlobalConstant.SYMBOL_COMMA);
        
        for(String hostPort : hostPorts) {
            String[] hp = hostPort.trim().split(GlobalConstant.SYMBOL_COLON);
            jedisClusterNodes.add(new HostAndPort(hp[0].trim(), Integer.parseInt(hp[1].trim())));
        }
        
        return jedisClusterNodes;
    }
    
    @Override
    public JedisCluster getObject() throws Exception {
        return this.jedisCluster;
    }
    
    @Override
    public Class<? extends JedisCluster> getObjectType() {
        return null == this.jedisCluster ? JedisCluster.class : this.jedisCluster.getClass();
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

    public void setMaxRedirections(Integer maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoolConfig(GenericObjectPoolConfig<Object> poolConfig) {
        this.poolConfig = poolConfig;
    }
}
