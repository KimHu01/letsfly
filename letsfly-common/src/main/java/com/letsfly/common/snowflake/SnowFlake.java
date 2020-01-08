package com.letsfly.common.snowflake;

import com.letsfly.common.util.DateUtil;

/**
 * <ul>
 * <li>snowflake[雪花算法]整体上按照时间自增,</li>
 * <li>整个分布式系统内不会产生ID碰撞,</li>
 * <li>数据结构共64个比特位(对应java的long数据类型),</li>
 * <li>比特位划分: 1-41-4-6-12, 数据中心Id/机器(进程)Id可调整</li>
 * <li>第01位: 符号位, 值恒等于0, 表示正数</li>
 * <li>第02-42位: 41位毫秒级时间差(自定义一个起始时间后, 可以使用69年)</li>
 * <li>第43-47位: 4位数据中心Id(dataCenterId)</li>
 * <li>第48-52位: 6位工作机器(进程)Id(workerId)</li>
 * <li>第53-64位: 12位毫秒内序列号(每毫秒可产生4096个Id序号)</li>
 * </ul>
 * @author kimhu
 * @create 2019/11/14
 * @version 1.0
 */
public final class SnowFlake {
    
    /** 锁 */
    private static final Object lock = new Object();
    
    /** 开始时间截 (2019-01-01 00:00:00) */
    private static final long twepoch = 1546272000000L;
    
    /** 比特位数:数据中心Id */
    private static final long dataCenterIdBits = 4L;
    
    /** 比特位数:机器id */
    private static final long workerIdBits = 6L;
    
    /** 比特位数:序列号 */
    private static final long sequenceBits = 12L;
    
    /** 最大值:数据中心Id */
    private static final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    
    /** 最大值:机器id */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    
    /** 最大值:序列号 */
    private static final long maxSequence = -1L ^ (-1L << sequenceBits);
    
    /** 左移位数:时间截向左移22位 */
    private final long diffTimestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    
    /** 左移位数:数据中心Id左移17位 */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    
    /** 左移位数:机器ID左移12位 */
    private final long workerIdShift = sequenceBits;
    
    /** 序列号掩码 */
    private final long sequenceMask = maxSequence;
    
    /** 数据中心ID */
    private long dataCenterId;
    
    /** 工作机器ID */
    private long workerId;
    
    /** 毫秒内序列 */
    private long sequence = 0L;
    
    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;
    
    /**
     * 构造函数
     * @param dataCenterId 数据中心ID
     * @param workerId 工作机器ID
     */
    SnowFlake(long dataCenterId, long workerId) {
        if(dataCenterId > maxDataCenterId || dataCenterId < 0){
            throw new IllegalArgumentException(String.format("dataCenterId can't be greater than %d or less than 0", maxDataCenterId));
        }
        
        if(workerId > maxWorkerId || workerId < 0){
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", maxWorkerId));
        }
        
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }
    
    /**
     * 获取下一个Id
     * @return id
     */
    public long next() {
        synchronized(lock) {
            long timestamp = DateUtil.getCurrentTimeMillis();
            
            //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
            if(timestamp < lastTimestamp) {
                throw new RuntimeException(String.format("Clock moved backwards: %d milliseconds", lastTimestamp - timestamp));
            }
            
            if(timestamp == lastTimestamp) {
                sequence = (sequence + 1) & sequenceMask;
                //毫秒内序列溢出
                if(sequence == 0){
                    timestamp = tillNextMillis(lastTimestamp);
                }
            } else { //时间戳改变，毫秒内序列重置
                sequence = 0L;
            }
            
            //上次生成ID的时间截
            lastTimestamp = timestamp;
            
            //毫秒时间差
            long diffTimestamp = timestamp - twepoch;
            
            //移位并通过或运算拼到一起组成64位的ID
            return (diffTimestamp << diffTimestampLeftShift) 
                    | (dataCenterId << dataCenterIdShift) 
                    | (workerId << workerIdShift) 
                    | sequence;
        }
    }
    
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tillNextMillis(long lastTimestamp) {
        long timestamp = DateUtil.getCurrentTimeMillis();
        while(timestamp <= lastTimestamp) {
            timestamp = DateUtil.getCurrentTimeMillis();
        }
        
        return timestamp;
    }
}
