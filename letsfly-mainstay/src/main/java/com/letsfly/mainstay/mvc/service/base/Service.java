package com.letsfly.mainstay.mvc.service.base;

import java.util.List;

import com.letsfly.common.model.AbstractDO;
import com.letsfly.mainstay.exception.CoreException;
import com.letsfly.mainstay.mvc.page.Pagination;

/**
 * Service接口定义
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public interface Service<M extends AbstractDO> {
    
    /**
     * 插入对象
     * @param m 待插入对象
     * @return 对象
     * @throws CoreException
     */
    M insert(M m) throws CoreException;
    
    /**
     * 执行insert sql
     * @param sql
     * @return
     * @throws CoreException
     */
    int insertBySql(String sql) throws CoreException;
    
    /**
     * 批量插入
     * @param list 待插入对象集合
     * @return 受影响记录数
     * @throws CoreException
     */
    int insertBatch(List<M> list) throws CoreException;
    
    /**
     * 更新对象
     * @param m 待更新对象
     * @return 受影响记录数
     * @throws CoreException
     */
    int update(M m) throws CoreException;
    
    /**
     * 执行update sql
     * @param sql
     * @return
     * @throws CoreException
     */
    int updateBySql(String sql) throws CoreException;
    
    /**
     * 批量更新
     * @param list 待更新对象集合
     * @return 受影响记录数
     * @throws CoreException
     */
    int updateBatch(List<M> list) throws CoreException;
    
    /**
     * 执行delete sql
     * @param sql
     * @return
     * @throws CoreException
     */
    int deleteBySql(String sql) throws CoreException;
    
    /**
     * 根据主键删除对象
     * @param pk 主键
     * @return 受影响记录数
     * @throws CoreException
     */
    <PK> int deleteById(PK pk) throws CoreException;
    
    /**
     * 根据主键批量删除对象
     * @param pks 主键集合
     * @return 受影响记录数
     * @throws CoreException
     */
    <PK> int deleteByIds(List<PK> pks) throws CoreException;
    
    /**
     * 根据主键查询对象
     * @param pk 主键
     * @return 查询结果
     * @throws CoreException
     */
    <PK> M selectById(PK pk) throws CoreException;
    
    /**
     * 根据主键集合批量查询对象
     * @param pks
     * @return 查询结果
     * @throws CoreException
     */
    <PK> List<M> selectByIds(List<PK> pks) throws CoreException;
    
    /**
     * 制定select sql
     * @param sql
     * @return
     * @throws CoreException
     */
    List<M> selectBySql(String sql) throws CoreException;
    
    /**
     * 根据条件参数查询对象集合
     * @param m 条件参数
     * @return 查询结果
     * @throws CoreException
     */
    List<M> selectListByModel(M m) throws CoreException;
    
    /**
     * 分页查询
     * @param pagination 分页对象
     * @return
     */
    Pagination<M> selectListByPage(Pagination<M> pagination) throws CoreException;
    
    /**
     * 查询全部:慎用
     * @return 查询结果
     * @throws CoreException
     */
    List<M> selectAll() throws CoreException;
}
