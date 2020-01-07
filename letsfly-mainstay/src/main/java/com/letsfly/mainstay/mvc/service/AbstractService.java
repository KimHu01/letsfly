package com.letsfly.mainstay.mvc.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.letsfly.common.model.AbstractDO;
import com.letsfly.common.util.StringUtil;
import com.letsfly.mainstay.exception.CoreException;
import com.letsfly.mainstay.mvc.dao.MyBatisDao;
import com.letsfly.mainstay.mvc.page.Pagination;
import com.letsfly.mainstay.mvc.service.base.Service;

/**
 * Service抽象实现
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public abstract class AbstractService<M extends AbstractDO, D extends MyBatisDao<M>> 
    implements Service<M> {
    
    /** 日志工具 */
    protected Log logger = LogFactory.getLog(getClass());
    
    /** 依赖的Dao对象 */
    @Autowired
    protected D baseDao;
    
    /** mybatis SqlSessionTemplate */
    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;
    
    /**
     * 插入对象
     * @param m 待插入对象
     * @return 对象
     * @throws CoreException
     */
    @Transactional
    @Override
    public M insert(M m) throws CoreException {
        if(null == m) {
            throw new CoreException("m can not be null.");
        }
        
        int rows = this.baseDao.insert(m);
        if(rows == 1) {
            return m;
        }
        
        throw new CoreException("Save Faild");
    }
    
    /**
     * 执行insert sql
     * @param sql
     * @return
     * @throws CoreException
     */
    @Transactional
    @Override
    public int insertBySql(String sql) throws CoreException {
        if(StringUtil.isEmpty(sql)) {
            throw new CoreException("sql can not be null.");
        }
        
        return this.baseDao.insertBySql(sql);
    }
    
    /**
     * 批量插入
     * @param list 待插入对象集合
     * @return 受影响记录数
     * @throws CoreException
     */
    @Transactional
    @Override
    public int insertBatch(List<M> list) throws CoreException {
        if(null == list || list.size() <= 0) {
            throw new CoreException("list can not be null.");
        }
        
        return this.baseDao.insertBatch(list);
    }
    
    /**
     * 更新对象
     * @param m 待更新对象
     * @return 受影响记录数
     * @throws CoreException
     */
    @Transactional
    @Override
    public int update(M m) throws CoreException {
        if(null == m) {
            throw new CoreException("m can not be null.");
        }
        
        return this.baseDao.update(m);
    }
    
    /**
     * 执行update sql
     * @param sql
     * @return
     * @throws CoreException
     */
    @Transactional
    @Override
    public int updateBySql(String sql) throws CoreException {
        if(StringUtil.isEmpty(sql)) {
            throw new CoreException("sql can not be null.");
        }
        
        return this.baseDao.updateBySql(sql);
    }
    
    /**
     * 批量更新
     * @param list 待更新对象集合
     * @return 受影响记录数
     * @throws CoreException
     */
    @Transactional
    @Override
    public int updateBatch(List<M> list) throws CoreException {
        if(null == list || list.size() <= 0) {
            throw new CoreException("list can not be null.");
        }
        
        return this.baseDao.updateBatch(list);
    }
    
    /**
     * 执行delete sql
     * @param sql
     * @return
     * @throws CoreException
     */
    @Transactional
    @Override
    public int deleteBySql(String sql) throws CoreException {
        if(StringUtil.isEmpty(sql)) {
            throw new CoreException("sql can not be null.");
        }
        
        return this.baseDao.deleteBySql(sql);
    }
    
    /**
     * 根据主键删除对象
     * @param pk 主键
     * @return 受影响记录数
     * @throws CoreException
     */
    @Transactional
    @Override
    public <PK> int deleteById(PK pk) throws CoreException {
        if(null == pk) {
            throw new CoreException("pk can not be null.");
        }
        
        return this.baseDao.deleteById(pk);
    }
    
    /**
     * 根据主键批量删除对象
     * @param pks 主键集合
     * @return 受影响记录数
     * @throws CoreException
     */
    @Transactional
    @Override
    public <PK> int deleteByIds(List<PK> pks) throws CoreException {
        if(null == pks || pks.size() <= 0) {
            throw new CoreException("pks can not be null.");
        }
        
        return this.baseDao.deleteByIds(pks);
    }
    
    /**
     * 根据主键查询对象
     * @param pk 主键
     * @return 查询结果
     * @throws CoreException
     */
    @Override
    public <PK> M selectById(PK pk) throws CoreException {
        if(null == pk) {
            throw new CoreException("pk can not be null.");
        }
        
        return this.baseDao.selectById(pk);
    }
    
    /**
     * 根据主键集合批量查询对象
     * @param pks
     * @return 查询结果
     * @throws CoreException
     */
    @Override
    public <PK> List<M> selectByIds(List<PK> pks) throws CoreException {
        if(null == pks || pks.size() <= 0) {
            throw new CoreException("pks can not be null.");
        }
        
        return this.baseDao.selectByIds(pks);
    }
    
    /**
     * 指定select sql
     * @param sql
     * @return
     * @throws CoreException
     */
    @Override
    public List<M> selectBySql(String sql) throws CoreException {
        if(StringUtil.isEmpty(sql)) {
            throw new CoreException("sql can not be null.");
        }
        
        return this.baseDao.selectBySql(sql);
    }
    
    /**
     * 根据条件参数查询对象集合
     * @param m 条件参数
     * @return 查询结果
     * @throws CoreException
     */
    @Override
    public List<M> selectListByModel(M m) throws CoreException {
        if(null == m) {
            throw new CoreException("m can not be null.");
        }
        
        return this.baseDao.selectListByModel(m);
    }
    
    /**
     * 分页查询
     * @param pagination 分页对象
     * @return
     * @throws CoreException
     */
    @Override
    public Pagination<M> selectListByPage(Pagination<M> pagination) throws CoreException {
        if(null == pagination || pagination.getPageNum() < 0 || pagination.getPageSize() <= 0 
                || null == pagination.getParam()) {
            throw new CoreException("pagination invalid.");
        }
        
        List<M> list = this.baseDao.selectListByPage(pagination);
        pagination.setResult(list);
        
        int totalPages = pagination.getTotalRows() / pagination.getPageSize();
        totalPages += (pagination.getTotalRows() % pagination.getPageSize() > 0 ? 1 : 0);
        
        pagination.setPreviousPageNum(pagination.getPageNum() - 1);
        pagination.setNextPageNum(pagination.getPageNum() + 1);
        pagination.setTotalPages(totalPages);
        
        if(totalPages <= 0 || pagination.getPageNum() == 1) {
            pagination.setPreviousPageNum(pagination.getPageNum());
        }
        
        if(totalPages <= 0 || pagination.getPageNum() == totalPages) {
            pagination.setNextPageNum(pagination.getPageNum());
        }
        
        return pagination;
    }
    
    /**
     * 查询全部:慎用
     * @return 查询结果
     * @throws CoreException
     */
    @Override
    public List<M> selectAll() throws CoreException {
        return this.baseDao.selectAll();
    }
}
