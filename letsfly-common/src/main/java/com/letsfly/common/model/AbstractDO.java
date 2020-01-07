package com.letsfly.common.model;

import java.io.Serializable;

import com.letsfly.common.model.base.AbstractPO;

/**
 * 抽象DO定义[DO属性应避免使用基础类型, 改为使用对象类型]<br>
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public abstract class AbstractDO extends AbstractPO implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
