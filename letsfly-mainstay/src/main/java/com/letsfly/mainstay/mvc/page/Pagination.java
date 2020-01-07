package com.letsfly.mainstay.mvc.page;

import java.io.Serializable;
import java.util.List;

import com.letsfly.mainstay.mvc.page.base.Page;

/**
 * 分页实体
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public class Pagination<M extends Serializable> extends Page implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** 上一页页码 */
    private int previousPageNum;
    
    /** 下一页页码 */
    private int nextPageNum;
    
    /** 当前页页码 */
    private int pageNum;
    
    /** 分页大小 */
    private int pageSize;
    
    /** 总记录数 */
    private int totalRows;
    
    /** 总页数 */
    private int totalPages;
    
    /** 查询条件 */
    private M param;
    
    /** 查询结果 */
    private List<M> result;
    
    /**
     * 无参构造函数
     */
    public Pagination() {}
    
    /**
     * 构造函数
     */
    public Pagination(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    
    /**
     * 构造函数
     */
    public Pagination(int pageNum, int pageSize, M param) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.param = param;
    }
    
    public int getPreviousPageNum() {
        return previousPageNum;
    }

    public void setPreviousPageNum(int previousPageNum) {
        this.previousPageNum = previousPageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public M getParam() {
        return param;
    }

    public void setParam(M param) {
        this.param = param;
    }

    public List<M> getResult() {
        return result;
    }

    public void setResult(List<M> result) {
        this.result = result;
    }
}
