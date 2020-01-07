package com.letsfly.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.letsfly.common.constant.GlobalConstant;
import com.letsfly.common.pattern.RegularExpressionPattern;

/**
 * String工具类
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class StringUtil extends StringUtils {
    
    /**
     * 私有化构造方法，防止被外部实例化
     */
    private StringUtil() {}
    
    /**
     * 获取uuid
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll(GlobalConstant.SYMBOL_HYPHEN, GlobalConstant.EMPTY_STR);
    }
    
    /**
     * 判断目标字符串是否为null or ""
     * @param str 目标字符串
     * @return true/false
     */
    public static boolean isEmpty(String str) {
        return (null == str || "".equals(str));
    }
    
    /**
     * 判断目标字符串是否不为null and ""
     * @param str 目标字符串
     * @return true/false
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * 去除首尾空格[若字符串为null,则异常]
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str.trim();
    }
    
    /**
     * 追加strings到str后面
     * @param str
     * @param strings
     * @return
     */
    public static String append(String str, String... strings) {
        if(null == strings || strings.length <= 0) {
            return str;
        }
        
        StringBuffer buffer = new StringBuffer(str);
        for(String item : strings) {
            buffer.append(item);
        }
        
        return buffer.toString();
    }
    
    /**
     * 拼接strings
     * @param strings
     * @return
     */
    public static String join(String... strings) {
        if(null == strings || strings.length <= 0) {
            return null;
        }
        
        StringBuffer buffer = new StringBuffer();
        for(String item : strings) {
            buffer.append(item);
        }
        
        return buffer.toString();
    }
    
    /**
     * 是否有值
     * @param str
     * @return
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }
    
    /**
     * 是否有值
     * @param str
     * @return
     */
    public static boolean hasLength(String str) {
        return (str != null && !str.isEmpty());
    }
    
    /**
     * 是否含有空格
     * @param str
     * @return
     */
    public static boolean containsWhitespace(CharSequence str) {
        if(!hasLength(str)) {
            return false;
        }
        
        int strLen = str.length();
        for(int i = 0; i < strLen; i++) {
            if(Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 是否含有空格
     * @param str
     * @return
     */
    public static boolean containsWhitespace(String str) {
        return containsWhitespace((CharSequence) str);
    }
    
    /**
     * 去除首尾多个空格
     * @param str
     * @return
     */
    public static String trimWhitespace(String str) {
        if(!hasLength(str)) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder(str);
        while(sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
            sb.deleteCharAt(0);
        }
        
        while(sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }
    
    /**
     * 去除所有空格
     * @param str
     * @return
     */
    public static String trimAllWhitespace(String str) {
        if(!hasLength(str)) {
            return str;
        }
        
        int len = str.length();
        StringBuilder sb = new StringBuilder(str.length());
        for(int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if(!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 去除首部多个空格
     * @param str
     * @return
     */
    public static String trimLeadingWhitespace(String str) {
        if(!hasLength(str)) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder(str);
        while(sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
            sb.deleteCharAt(0);
        }
        
        return sb.toString();
    }
    
    /**
     * 去除尾部多个空格
     * @param str
     * @return
     */
    public static String trimTrailingWhitespace(String str) {
        if(!hasLength(str)) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder(str);
        while(sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }
    
    /**
     * 去除首部多个Character
     * @param str
     * @param leadingCharacter
     * @return
     */
    public static String trimLeadingCharacter(String str, char leadingCharacter) {
        if(!hasLength(str)) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder(str);
        while(sb.length() > 0 && sb.charAt(0) == leadingCharacter) {
            sb.deleteCharAt(0);
        }
        
        return sb.toString();
    }
    
    /**
     * 去除尾部多个Character
     * @param str
     * @param trailingCharacter
     * @return
     */
    public static String trimTrailingCharacter(String str, char trailingCharacter) {
        if(!hasLength(str)) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder(str);
        while(sb.length() > 0 && sb.charAt(sb.length() - 1) == trailingCharacter) {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }
    
    /**
     * 是否以prefix开头[不计大小写]
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return (str != null && prefix != null && str.length() >= prefix.length() && 
                str.regionMatches(true, 0, prefix, 0, prefix.length()));
    }
    
    /**
     * 是否以suffix结尾[不计大小写]
     * @param str
     * @param suffix
     * @return
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        return (str != null && suffix != null && str.length() >= suffix.length() && 
                str.regionMatches(true, str.length() - suffix.length(), suffix, 0, suffix.length()));
    }
    
    /**
     * str从index开始是否包含substring
     * @param str
     * @param index
     * @param substring
     * @return
     */
    public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
        if(index + substring.length() > str.length()) {
            return false;
        }
        
        for(int i = 0; i < substring.length(); i++) {
            if(str.charAt(index + i) != substring.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * str中出现sub的次数
     * @param str
     * @param sub
     * @return
     */
    public static int countOccurrencesOf(String str, String sub) {
        if(!hasLength(str) || !hasLength(sub)) {
            return 0;
        }
        
        int count = 0, pos = 0, idx;
        while((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        
        return count;
    }
    
    /**
     * 将inString中的oldPattern全部替换成newPattern
     * @param inString
     * @param oldPattern
     * @param newPattern
     * @return
     */
    public static String replaceAll(String inString, String oldPattern, String newPattern) {
        if(!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        
        int index = inString.indexOf(oldPattern);
        if(index == -1) {
            return inString;
        }
        
        int capacity = inString.length();
        if(newPattern.length() > oldPattern.length()) {
            capacity += 16;
        }
        
        StringBuilder sb = new StringBuilder(capacity);
        
        int pos = 0;
        int patLen = oldPattern.length();
        while(index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        
        sb.append(inString.substring(pos));
        return sb.toString();
    }
    
    /**
     * 将inString中的pattern全部去除
     * @param inString
     * @param pattern
     * @return
     */
    public static String delete(String inString, String pattern) {
        return replaceAll(inString, pattern, "");
    }
    
    /**
     * 将inString中所有出现在charsToDelete中的[字符]全部剔除
     * @param inString
     * @param charsToDelete
     * @return
     */
    public static String deleteAny(String inString, String charsToDelete) {
        if(!hasLength(inString) || !hasLength(charsToDelete)) {
            return inString;
        }
        
        StringBuilder sb = new StringBuilder(inString.length());
        for(int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            if(charsToDelete.indexOf(c) == -1) {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 给str首尾加单引号
     * @param str
     * @return
     */
    public static String quoteSingle(String str) {
        return (str != null ? GlobalConstant.SYMBOL_SINGLE_QUOTE + str + GlobalConstant.SYMBOL_SINGLE_QUOTE : null);
    }
    
    /**
     * 给str首尾加双引号
     * @param str
     * @return
     */
    public static String quoteDouble(String str) {
        return (str != null ? GlobalConstant.SYMBOL_DOUBLE_QUOTE + str + GlobalConstant.SYMBOL_DOUBLE_QUOTE : null);
    }
    
    /**
     * 如果是string类型，则首尾加单引号
     * @param obj
     * @return
     */
    public static Object quoteSingleIfString(Object obj) {
        return (obj instanceof String ? quoteSingle((String) obj) : obj);
    }
    
    /**
     * 如果是string类型，则首尾加双引号
     * @param obj
     * @return
     */
    public static Object quoteDoubleIfString(Object obj) {
        return (obj instanceof String ? quoteDouble((String) obj) : obj);
    }
    
    /**
     * 截取qualifiedName最后一个separator字符后面的字符串
     * @param qualifiedName
     * @param separator
     * @return
     */
    public static String unqualify(String qualifiedName, char separator) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
    }
    
    /**
     * 截取qualifiedName中最后一个'.'字符后面的字符串[获取文件扩展名]
     * @param qualifiedName
     * @return
     */
    public static String unqualify(String qualifiedName) {
        return unqualify(qualifiedName, '.');
    }
    
    /**
     * 解析字符串为TimeZone对象
     * @param timeZoneString
     * @return
     */
    public static TimeZone parseTimeZoneString(String timeZoneString) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
        if ("GMT".equals(timeZone.getID()) && !timeZoneString.startsWith("GMT")) {
            throw new IllegalArgumentException(String.format("Invalid time zone specification '%s'", timeZoneString));
        }
        
        return timeZone;
    }
    
    /**
     * 将字符串追加到数组尾[用于数组已满载情况下]
     * @param array
     * @param str
     * @return
     */
    public static String[] addStringToArray(String[] array, String str) {
        if(array == null || array.length == 0) {
            return new String[] {str};
        }
        
        String[] newArr = new String[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = str;
        
        return newArr;
    }
    
    /**
     * 连接两个数组[两数组中的公共元素不会去重]
     * @param array1
     * @param array2
     * @return
     */
    public static String[] concatenateStringArrays(String[] array1, String[] array2) {
        if(array1 == null || array1.length == 0) {
            return array2;
        }
        
        if(array2 == null || array2.length == 0) {
            return array1;
        }
        
        String[] newArr = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, newArr, 0, array1.length);
        System.arraycopy(array2, 0, newArr, array1.length, array2.length);
        return newArr;
    }
    
    /**
     * 合并两个数组[去除重复元素]
     * @param array1
     * @param array2
     * @return
     */
    public static String[] mergeStringArrays(String[] array1, String[] array2) {
        if(array1 == null || array1.length == 0) {
            return array2;
        }
        
        if(array2 == null || array2.length == 0) {
            return array1;
        }
        
        List<String> result = new ArrayList<String>();
        result.addAll(Arrays.asList(array1));
        
        for(String str : array2) {
            if(!result.contains(str)) {
                result.add(str);
            }
        }
        
        return toStringArray(result);
    }
    
    /**
     * 集合转数组
     * @param collection
     * @return
     */
    public static String[] toStringArray(Collection<String> collection) {
        return collection.toArray(new String[collection.size()]);
    }
    
    /**
     * 集合转数组
     * @param enumeration
     * @return
     */
    public static String[] toStringArray(Enumeration<String> enumeration) {
        List<String> list = Collections.list(enumeration);
        return list.toArray(new String[list.size()]);
    }
    
    /**
     * 占位符替换,使用{key}表示占位符,例:<br>
     * String src = "我喜欢{name}";<br><br>
     * 
     * Map<String, String> params = new HashMap<String, String>();<br>
     * params.put("name", "周杰伦");<br><br>
     * 
     * String result = StringUtil.replaceHolder(src, params);<br>
     * result值为: 我喜欢周杰伦
     * @param src
     * @param params
     * @return
     */
    public static String replaceHolder(String src, Map<String, String> params) {
        if(null == params || params.size() <= 0) {
            return src;
        }
        
        Matcher matcher = Pattern.compile(RegularExpressionPattern.REG_EXP_PLACEHOLDER).matcher(src);
        while (matcher.find()) {
            String keyHolder = matcher.group();
            String key = keyHolder.substring(1, keyHolder.length() - 1).trim();
            String value = params.get(key);
            
            if(StringUtil.isNotEmpty(value)) {
                src = src.replace(keyHolder, value);
            }
        }
        
        return src;
    }
    
    /**
     * 使用指定字符替换到字符串中指定位置
     * String phone = "13611112222";<br><br>
     * String ch = "*";<br>
     * index = 3, offset = 4;<br><br>
     * 
     * String result = StringUtil.replace(phone, ch, index, offset);<br>
     * @param src 原字符串
     * @param ch 目标字符
     * @param index 开始下标
     * @param offset 替换长度
     * @return
     */
    public static String replaceMsg(String src, String ch, int index, int offset) {
        if(StringUtil.isNotEmpty(src) || src.length() < index || src.length() < (index + offset)) {
            return null;
        }
        
        String[] arr = src.split(GlobalConstant.EMPTY_STR);
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<arr.length; i++) {
            if(i>= index && i<= (index + offset - 1)) {
                buffer.append(ch);
                continue;
            }
            
            buffer.append(arr[i]);
        }
        
        return buffer.toString();
    }
}
