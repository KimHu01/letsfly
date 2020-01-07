package com.letsfly.mainstay.mvc.dao;

import java.util.List;

import com.letsfly.common.model.AbstractDO;
import com.letsfly.mainstay.exception.DataAccessException;
import com.letsfly.mainstay.mvc.page.Pagination;

/**
 * Dao接口定义
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public interface MyBatisDao<M extends AbstractDO> {
    
    /**
     * 执行ddl
     * @param sql
     * @return
     * @throws DataAccessException
     */
    int executeDDLSql(String sql) throws DataAccessException;
    
    /**
     * 插入对象
     * @param m 待插入对象
     * @return 受影响记录数
     * @throws DataAccessException
     */
    int insert(M m) throws DataAccessException;
    
    /**
     * 执行insert sql
     * @param sql
     * @return
     * @throws DataAccessException
     */
    int insertBySql(String sql) throws DataAccessException;
    
    /**
     * 批量插入
     * @param list 待插入对象集合
     * @return 受影响记录数
     * @throws DataAccessException
     */
    int insertBatch(List<M> list) throws DataAccessException;
    
    /**
     * 更新对象
     * @param m 待更新对象
     * @return 受影响记录数
     * @throws DataAccessException
     */
    int update(M m) throws DataAccessException;
    
    /**
     * 执行update sql
     * @param sql
     * @return
     * @throws DataAccessException
     */
    int updateBySql(String sql) throws DataAccessException;
    
    /**
     * 批量更新
     * @param list 待更新对象集合
     * @return 受影响记录数
     * @throws DataAccessException
     */
    int updateBatch(List<M> list) throws DataAccessException;
    
    /**
     * 执行delete sql:[慎用]
     * @param sql
     * @return
     * @throws DataAccessException
     */
    int deleteBySql(String sql) throws DataAccessException;
    
    /**
     * 根据主键删除对象:[慎用]
     * @param pk 主键
     * @return 受影响记录数
     * @throws DataAccessException
     */
    <PK> int deleteById(PK pk) throws DataAccessException;
    
    /**
     * 根据主键批量删除对象:[慎用]
     * @param pks 主键集合
     * @return 受影响记录数
     * @throws DataAccessException
     */
    <PK> int deleteByIds(List<PK> pks) throws DataAccessException;
    
    /**
     * 根据主键查询对象
     * @param pk 主键
     * @return 查询结果
     * @throws DataAccessException
     */
    <PK> M selectById(PK pk) throws DataAccessException;
    
    /**
     * 根据主键集合批量查询对象
     * @param pks
     * @return 查询结果
     * @throws DataAccessException
     */
    <PK> List<M> selectByIds(List<PK> pks) throws DataAccessException;
    
    /**
     * 制定select sql
     * @param sql
     * @return
     * @throws DataAccessException
     */
    List<M> selectBySql(String sql) throws DataAccessException;
    
    /**
     * 根据条件参数查询对象集合
     * @param m 条件参数
     * @return 查询结果
     * @throws DataAccessException
     */
    List<M> selectListByModel(M m) throws DataAccessException;
    
    /**
     * 分页查询
     * @param pagination 分页对象
     * @return
     * @throws DataAccessException
     */
    List<M> selectListByPage(Pagination<M> pagination) throws DataAccessException;
    
    /**
     * 查询全部:[慎用]
     * @return 查询结果
     * @throws DataAccessException
     */
    List<M> selectAll() throws DataAccessException;
}
