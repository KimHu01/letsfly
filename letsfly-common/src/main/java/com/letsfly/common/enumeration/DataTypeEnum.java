package com.letsfly.common.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础类型/对象类型映射关系
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public enum DataTypeEnum {
    BYTE(byte.class,        Byte.class),
    SHORT(short.class,      Short.class),
    INT(int.class,          Integer.class),
    LONG(long.class,        Long.class),
    FLOAT(float.class,      Float.class),
    DOUBLE(double.class,    Double.class),
    BOOLEAN(boolean.class,  Boolean.class),
    CHAR(char.class,        Character.class);
    
    /** 基础类型 */
    private Class<?> basicType;
    
    /** 对象类型 */
    private Class<?> objectType;
    
    /**
     * 私有构造方法
     * @param basicType 基础类型
     * @param objectType 对象类型
     */
    private DataTypeEnum(Class<?> basicType, Class<?> objectType) {
        this.basicType = basicType;
        this.objectType = objectType;
    }

    /**
     * 获取所有基本类型/对象类型对应关系
     * @return 基本类型/对象类型对应关系
     */
    public static Map<Class<?>, Class<?>> getDataType() {
        Map<Class<?>, Class<?>> result = new HashMap<Class<?>, Class<?>>();
        for(DataTypeEnum dataType : DataTypeEnum.values()) {
            result.put(dataType.getBasicType(), dataType.getObjectType());
        }
        
        return result;
    }
    
    /**
     * 根据基础类型获取DataTypeEnum
     * @param basicType 基础类型
     * @return DataTypeEnum
     */
    public static DataTypeEnum getByBasicType(Class<?> basicType) {
        if(null == basicType) {
            throw new IllegalArgumentException("basicType can not be null.");
        }
        
        for(DataTypeEnum dataType : DataTypeEnum.values()) {
            if(dataType.getBasicType().equals(basicType)) {
                return dataType;
            }
        }
        
        throw new IllegalArgumentException(basicType + "is not Basic Type");
    }
    
    /**
     * 根据对象类型获取DataTypeEnum
     * @param objectType 对象类型
     * @return DataTypeEnum
     */
    public static DataTypeEnum getByObjectType(Class<?> objectType) {
        if(null == objectType) {
            throw new IllegalArgumentException("basicType can not be null.");
        }
        
        for(DataTypeEnum dataType : DataTypeEnum.values()) {
            if(dataType.getObjectType().equals(objectType)) {
                return dataType;
            }
        }
        
        throw new IllegalArgumentException(objectType + "is not Object Type");
    }

    public Class<?> getBasicType() {
        return basicType;
    }

    public Class<?> getObjectType() {
        return objectType;
    }
}
