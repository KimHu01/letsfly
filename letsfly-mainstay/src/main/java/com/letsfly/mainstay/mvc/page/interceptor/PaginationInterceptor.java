package com.letsfly.mainstay.mvc.page.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.letsfly.mainstay.mvc.page.Pagination;

/**
 * Mybatis分页拦截器<br>
 * 拦截StatementHandler接口的prepare方法,<br>
 * 在拦截器方法中把Sql语句改成对应的分页查询Sql语句,<br>
 * 再调用StatementHandler对象的prepare方法<br>
 * @author KimHu
 * @create 2019/08/13
 * @version 1.0
 */
@Intercepts({@Signature(type=StatementHandler.class, method="prepare", args={Connection.class, Integer.class})})
public class PaginationInterceptor implements Interceptor {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(PaginationInterceptor.class);
    
    private static final String DELEGATE_BOUNDSQL = "delegate.boundSql";
    
    private static final String DELEGATE_MAPPEDSTATEMENT = "delegate.mappedStatement";
    
    private static final String DELEGATE_BOUNDSQL_SQL = "delegate.boundSql.sql";
    
    private static final String DIALECT_NAME = "dialect";
    
    /** 数据源名称[MySQL/oracle/PostgreSQL] */
    private String dialect;
    
    /** 数据源枚举 */
    private enum DIALECTS {
        MYSQL, ORACLE, POSTGRESQL;
    }
    
    /**
     * 拦截器要执行的方法
     * @param invocation
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getTarget() instanceof RoutingStatementHandler) {
            MetaObject metaStatementHandler = SystemMetaObject.forObject((StatementHandler) invocation.getTarget());
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue(DELEGATE_MAPPEDSTATEMENT);
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue(DELEGATE_BOUNDSQL);
            
            if(boundSql.getParameterObject() instanceof Pagination<?>) {
                Pagination<?> pagination = (Pagination<?>) boundSql.getParameterObject();
                String countSql = this.concatCountSql(boundSql.getSql());
                String pageSql = this.concatPageSql(boundSql.getSql(), pagination);
                int totalRows = this.getTotalRows(countSql, invocation, boundSql, pagination, mappedStatement);
                
                pagination.setTotalRows(totalRows);
                metaStatementHandler.setValue(DELEGATE_BOUNDSQL_SQL, pageSql);
            }
        }
        
        return invocation.proceed();
    }

    /**
     * 拦截器需要拦截的对象<br>
     * 仅拦截StatementHandler对象
     * @param target
     */
    @Override
    public Object plugin(Object target) {
        if(target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        
        return target;
    }

    /**
     * 设置初始化的属性值
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        this.dialect = properties.getProperty(DIALECT_NAME);
    }
    
    /**
     * 执行countSql得到总记录数
     * @param countSql
     * @param invocation
     */
    private int getTotalRows(String countSql, Invocation invocation, BoundSql boundSql, Pagination<?> pagination, MappedStatement mappedStatement) {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, pagination);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, pagination, countBoundSql);
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int total = 0;
        
        try {
            Connection connection = (Connection) invocation.getArgs()[0];
            preparedStatement = connection.prepareStatement(countSql);
            parameterHandler.setParameters(preparedStatement);
            
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                total = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("execute SQLException", e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("close SQLException", e);
            }
        }
        
        return total;
    }
    
    /**
     * 根据源sql组装count sql
     * @param sql 源sql
     * @return count sql
     */
    private String concatCountSql(String sql) {
        StringBuffer countSql = new StringBuffer("select count(*) ");
        sql = sql.toLowerCase();
        
        if(sql.lastIndexOf("order") > sql.lastIndexOf("where")) {
            return countSql.append(sql.substring(sql.indexOf("from"), sql.lastIndexOf("order"))).toString();
        }
        
        return countSql.append(sql.substring(sql.indexOf("from"))).toString();
    }
    
    /**
     * 组装page sql
     * @param sql 源sql
     * @param pagination 分页对象
     * @return page sql
     */
    private String concatPageSql(String sql, Pagination<?> pagination) {
        if(DIALECTS.MYSQL.toString().equalsIgnoreCase(dialect)) {
            return this.getMysqlPageSql(new StringBuffer(sql), pagination);
        }
        
        if(DIALECTS.ORACLE.toString().equalsIgnoreCase(dialect)) {
            return this.getOraclePageSql(new StringBuffer(sql), pagination);
        }
        
        if(DIALECTS.POSTGRESQL.toString().equalsIgnoreCase(dialect)) {
            return this.getPostgreSQLPageSql(new StringBuffer(sql), pagination);
        }
        
        throw new IllegalArgumentException(String.format("Invalid Dialect:{%s}", dialect));
    }
    
    /**
     * mysql[limit的start从0开始]
     * @param pageSql
     * @param pagination
     * @return
     */
    private String getMysqlPageSql(StringBuffer pageSql, Pagination<?> pagination) {
        int start = (pagination.getPageNum() - 1) * pagination.getPageSize();
        
        return pageSql.append(" limit ")
            .append(start)
            .append(" , ")
            .append(pagination.getPageSize()).toString();
    }
    
    /**
     * oracle[rownum从1开始]
     * @param pageSql
     * @param pagination
     * @return
     */
    private String getOraclePageSql(StringBuffer pageSql, Pagination<?> pagination) {
        int index = (pagination.getPageNum() - 1) * pagination.getPageSize() + 1;
        pageSql.insert(0, "select _u_pagination.*, rownum _u_pagination_rn from (")
            .append(") _u_pagination where rownum < ")
            .append(index + pagination.getPageSize());
        
        pageSql.insert(0, "select * from (")
            .append(") where _u_pagination_rn >= ")
            .append(index);
        
        return pageSql.toString();
    }
    
    /**
     * PostgreSQL[offset从0开始]
     * @param pageSql
     * @param pagination
     * @return
     */
    private String getPostgreSQLPageSql(StringBuffer pageSql, Pagination<?> pagination) {
        int offset = (pagination.getPageNum() - 1) * pagination.getPageSize();
        
        return pageSql.append(" limit ")
                .append(pagination.getPageSize())
                .append(" offset ")
                .append(offset).toString();
    }
}
