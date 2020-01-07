package com.letsfly.mainstay.database;

/**
 * 动态数据源上下文
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class DynamicDataSourceHolder {
	
	/** 本地线程 */
    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();
    
    /**
     * 设置当前数据库
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        dataSourceHolder.set(dataSource);
    }
    
    /**
     * 获取当前数据源
     * @return
     */
    public static String getDataSource() {
        return dataSourceHolder.get();
    }
    
    /**
     * 清除当前数据源
     */
    public static void clearDataSource() {
        dataSourceHolder.remove();
    }
}
