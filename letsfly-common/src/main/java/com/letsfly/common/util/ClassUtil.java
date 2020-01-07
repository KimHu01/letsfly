package com.letsfly.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;

import com.letsfly.common.enumeration.DataTypeEnum;
import com.letsfly.common.pattern.DatePattern;

/**
 * Class工具类
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class ClassUtil extends ClassUtils {
    
    /**
     * 私有化构造方法，防止被外部实例化
     */
    private ClassUtil() {}
    
    /**
     * 获取类对象有效属性[非final, 非static]
     * @param clazz 目标类对象
     * @return 有效属性
     */
    public static List<Field> getGenericFields(Class<?> clazz) {
        List<Field> fields = ClassUtil.getFields(clazz);
        if(fields.size() <= 0) {
            return fields;
        }
        
        Iterator<Field> iterator = fields.iterator();
        while(iterator.hasNext()) {
            Field field = iterator.next();
            if(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                iterator.remove();
            }
        }
        
        return fields;
    }
    
    /**
     * 循环获取类对象所有属性
     * @param clazz 目标类对象
     * @return 所有属性
     */
    public static List<Field> getFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<Field>();
        
        while(!clazz.equals(Object.class)) {
            Field[] arr = clazz.getDeclaredFields();
            if(null != arr && arr.length > 0) {
                fields.addAll(Arrays.asList(arr));
            }
            
            clazz = clazz.getSuperclass();
        }
        
        return fields;
    }
    
    /**
     * 判断两个属性类型是否相同[基本型和对应的对象类型视为相同,如:int Integer]
     * @param target 目标field
     * @param source 源field
     * @return
     */
    public static boolean isSameType(Field target, Field source) {
        if(target.getGenericType().equals(source.getGenericType())) {
            return true;
        }
        
        if(target.getType().isPrimitive()) {
            DataTypeEnum dataType = DataTypeEnum.getByBasicType(target.getType());
            if(null == dataType) {
                return false;
            }
            
            return dataType.getObjectType().equals(source.getType());
        }
        
        if(source.getType().isPrimitive()) {
            DataTypeEnum dataType = DataTypeEnum.getByBasicType(source.getType());
            if(null == dataType) {
                return false;
            }
            
            return dataType.getObjectType().equals(target.getType());
        }
        
        return false;
    }
    
    /**
     * 将源类型value值转换为目标类型的值
     * @param targetType 目标类型
     * @param sourceType 源类型
     * @param value 源值
     * @return 目标类型值
     * @throws ParseException 
     */
    public static Object cast(Class<?> targetType, Class<?> sourceType, Object value) throws ParseException {
        if(null == targetType || null == sourceType || null == value) {
            return null;
        }
        
        if(!targetType.equals(String.class) && !sourceType.equals(String.class)) {
            return null;
        }
        
        if(targetType.equals(String.class)) {
            if(sourceType.equals(java.util.Date.class)) {
                return DateUtil.formatDateTime((java.util.Date) value, DatePattern.YMDHMS_DASH);
            }
            
            if(sourceType.equals(java.sql.Timestamp.class)) {
                return String.valueOf(((java.sql.Timestamp) value).getTime());
            }
            
            return String.valueOf(value) ;
        }
        
        if(sourceType.equals(String.class)) {
            if(targetType.equals(byte.class) || targetType.equals(Byte.class)) {
                return Byte.parseByte(value.toString());
            }
            
            if(targetType.equals(short.class) || targetType.equals(Short.class)) {
                return Short.parseShort(value.toString());
            }
            
            if(targetType.equals(int.class) || targetType.equals(Integer.class)) {
                return Integer.parseInt(value.toString());
            }
            
            if(targetType.equals(long.class) || targetType.equals(Long.class)) {
                return Long.parseLong(value.toString());
            }
            
            if(targetType.equals(float.class) || targetType.equals(Float.class)) {
                return Float.parseFloat(value.toString());
            }
            
            if(targetType.equals(double.class) || targetType.equals(Double.class)) {
                return Double.parseDouble(value.toString());
            }
            
            if(targetType.equals(boolean.class) || targetType.equals(Boolean.class)) {
                return Boolean.parseBoolean(value.toString());
            }
            
            if(targetType.equals(char.class) || targetType.equals(Character.class)) {
                return (Character) value;
            }
            
            if(targetType.equals(BigDecimal.class)) {
                return new BigDecimal(value.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            
            if(targetType.equals(java.util.Date.class)) {
                return DateUtil.parseDateTime(value.toString(), DatePattern.YMDHMS_DASH);
            }
            
            return targetType.cast(value);
        }
        
        return null;
    }
}
