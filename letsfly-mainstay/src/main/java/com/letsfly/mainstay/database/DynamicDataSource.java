package com.letsfly.mainstay.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    /**
     * 取得当前使用那个数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
