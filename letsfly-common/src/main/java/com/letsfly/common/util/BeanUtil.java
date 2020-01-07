package com.letsfly.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Bean工具类
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class BeanUtil extends BeanUtils {
    
    /**
     * 私有化构造方法，防止被外部实例化
     */
    private BeanUtil() {}
    
    /**
     * 判断对象是否为java对象(非自定义对象)
     * @param clazz 待判断对象
     * @return true/false
     */
    public static boolean hasClassLoador(Class<?> clazz) {
        return clazz.getClassLoader() == null;
    }
    
    /**
     * 深层克隆
     * @param src
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T deepClone(T src) {
        ByteArrayOutputStream memoryBytes = new ByteArrayOutputStream();
        
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        T dist = null;
        
        try {
            out = new ObjectOutputStream(memoryBytes);
            out.writeObject(src);
            out.flush();
            
            in = new ObjectInputStream(new ByteArrayInputStream(memoryBytes.toByteArray()));
            dist = (T) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        
        return dist;
    }
    
    /**
     * 批量copy
     * @param clazz
     * @param list
     * @return
     */
    public static <S, T> List<T> copyProps(List<S> sourceList, Class<T> targetClazz) {
        if(null == sourceList) {
            return null;
        }
        
        try {
            List<T> result = new ArrayList<T>();
            for(S s : sourceList) {
                result.add(copyProps(s, targetClazz.newInstance()));
            }
            
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 将s中属性值copy到t对应的属性值中<br>
     * 按照两者属性名称是否相同决定是否copy<br>
     * 若目标t对象属性值不为空，则跳过copy<br>
     * @param s 
     * @param t 
     * @return t
     */
    public static <S, T> T copyProps(S s, T t) {
        if(null == s) {
            return t;
        }
        
        List<Field> sourceFields = ClassUtil.getGenericFields(s.getClass());
        List<Field> targetFields = ClassUtil.getGenericFields(t.getClass());
        if(sourceFields.size() <= 0 || targetFields.size() <= 0) {
            return t;
        }
        
        Map<String, Field> sourceFieldMap = new HashMap<String, Field>();
        for(Field sourceField : sourceFields) {
            sourceFieldMap.put(sourceField.getName(), sourceField);
        }
        
        for(Field targetField : targetFields) {
            Field sourceField = sourceFieldMap.get(targetField.getName());
            if(null == sourceField) {
                continue;
            }
            
            cast(sourceField, targetField, s, t);
        }
        
        return t;
    }
    
    /**
     * 一.属性copy<br>
     * 如果源field和目标field的type相同, 则直接copy,<br>
     * 否则进入下一步类型转换copy<br>
     * 
     * 二.类型转换原则:<br>
     * 1.源field是string类型,则根据目标field类型进行适合的反序列化之后进行copy.<br>
     * 2.目标field是string类型,则根据源field类型进行适合的序列化之后进行copy;<br>
     * @param source 源
     * @param target 目标
     * @param s 
     * @param t 
     */
    private static <S, T> void cast(Field source, Field target, S s, T t) {
        try {
            source.setAccessible(true);
            target.setAccessible(true);
            if(null == source.get(s) || null != target.get(t)) {
                return;
            }
            
            if(ClassUtil.isSameType(target, source)) {
                target.set(t, source.get(s));
                return;
            }
            
            Object value = ClassUtil.cast(target.getType(), source.getType(), source.get(s));
            if(null != value) {
                target.set(t, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
