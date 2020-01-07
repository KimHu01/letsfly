package com.letsfly.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.letsfly.common.pattern.DatePattern;

/**
 * Date Util类
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class DateUtil {
    
    /**
     * 私有化构造方法，防止被外部实例化
     */
    private DateUtil() {}
    
    /**
     * timestamp转换为Date
     * @param timestamp
     * @return
     */
    public static Date convert2Date(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }
    
    /**
     * date转timestamp
     * @param date
     * @return
     */
    public static Timestamp convert2Timestamp(Date date) {
        return new Timestamp(date.getTime());
    }
    
    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }
    
    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static Date getCurrentDateTime() {
        return new Date();
    }
    
    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static String getCurrentTimeMillisString() {
        return String.valueOf(getCurrentTimeMillis());
    }
    
    /**
     * 格式化当前时间
     * @param pattern 字符串格式
     * @return 当前时间
     */
    public static String formatCurrentDateTime(DatePattern pattern) {
        return formatDateTime(getCurrentDateTime(), pattern);
    }
    
    /**
     * 格式化时间
     * @param date 日期
     * @param pattern 字符串格式
     * @return 当前时间
     */
    public static String formatDateTime(Date date, DatePattern pattern) {
        return new SimpleDateFormat(pattern.getPattern()).format(date);
    }
    
    /**
     * 将字符串类型解析成日期类型</br>
     * @param date 字符串日期
     * @param pattern 字符串格式
     * @return Date类型的日期
     */
    public static Date parseDateTime(String date, DatePattern pattern) {
        try {
            return new SimpleDateFormat(pattern.getPattern()).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("parseDateTime Exception", e);
        }
    }
    
    /**
     * 获取当前年
     * @return
     */
    public static int getCurrentYear() {
        return getYear(getCurrentDateTime());
    }
    
    /**
     * 获取date的年
     * @param date
     * @return 年
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
    
    /**
     * 获取当前月
     * @return
     */
    public static int getCurrentMouth() {
        return getMouth(getCurrentDateTime());
    }
    
    /**
     * 获取date的月
     * @param date
     * @return 月
     */
    public static int getMouth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }
    
    /**
     * 获取当前日
     * @return
     */
    public static int getCurrentDay() {
        return getDay(getCurrentDateTime());
    }
    
    /**
     * 获取date的日
     * @param date
     * @return 日
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 获取当前时间的小时
     * @return 小时
     */
    public static int getCurrentHour() {
        return getHour(getCurrentDateTime());
    }
    
    /**
     * 获取date的小时
     * @param date
     * @return 小时
     */
    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * 获取当前时间的分钟
     * @return 分钟
     */
    public static int getCurrentMinute() {
        return getMinute(getCurrentDateTime());
    }
    
    /**
     * 获取date的分钟
     * @param date
     * @return 分钟
     */
    public static int getMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }
    
    /**
     * 获取当前时间的秒
     * @return 秒
     */
    public static int getCurrentSecond() {
        return getSecond(getCurrentDateTime());
    }
    
    /**
     * 获取date的秒
     * @param date
     * @return 秒
     */
    public static int getSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }
    
    /**
     * 获取date的毫秒
     * @param date
     * @return
     */
    public static int getMilliSeconds(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MILLISECOND);
    }
    
    /**
     * 获取当前月天数
     * @return 天数
     */
    public static int getCurrentMouthDays() {
        Calendar cal = Calendar.getInstance();
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 获取某年某月的天数
     * @param year
     * @param mouth
     * @return 天数
     */
    public static int getAnyMouthDays(int year, int mouth) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mouth - 1);
        
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 获取某年某月的天数
     * @param date
     * @return 天数
     */
    public static int getAnyMouthDays(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 当前时间加milliSeconds之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param milliSeconds 毫秒
     * @return 新时间
     */
    public static Date getDateAfterMilliSeconds(int milliSeconds) {
        return getDateAfterMilliSeconds(getCurrentDateTime(), milliSeconds);
    }
    
    /**
     * date加milliSeconds之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param date
     * @param milliSeconds
     * @return 新时间
     */
    public static Date getDateAfterMilliSeconds(Date date, int milliSeconds) {
        return calculateDate(date, Calendar.MILLISECOND, milliSeconds);
    }
    
    /**
     * 当前时间加seconds之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param seconds 秒
     * @return 新时间
     */
    public static Date getDateAfterSeconds(int seconds) {
        return getDateAfterSeconds(getCurrentDateTime(), seconds);
    }
    
    /**
     * date加seconds之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param date
     * @param seconds
     * @return 新时间
     */
    public static Date getDateAfterSeconds(Date date, int seconds) {
        return calculateDate(date, Calendar.SECOND, seconds);
    }
    
    /**
     * 当前时间加minutes之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param minutes 分钟
     * @return 新时间
     */
    public static Date getDateAfterMinutes(int minutes) {
        return getDateAfterMinutes(getCurrentDateTime(), minutes);
    }
    
    /**
     * date加minutes之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param date
     * @param minutes
     * @return 新时间
     */
    public static Date getDateAfterMinutes(Date date, int minutes) {
        return calculateDate(date, Calendar.MINUTE, minutes);
    }
    
    /**
     * 当前时间加hours之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param hours 小时
     * @return 新时间
     */
    public static Date getDateAfterHours(int hours) {
        return getDateAfterHours(getCurrentDateTime(), hours);
    }
    
    /**
     * date加hours之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param date
     * @param hours
     * @return 新时间
     */
    public static Date getDateAfterHours(Date date, int hours) {
        return calculateDate(date, Calendar.HOUR_OF_DAY, hours);
    }
    
    /**
     * 当前时间加days之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param days 小时
     * @return 新时间
     */
    public static Date getDateAfterDays(int days) {
        return getDateAfterDays(getCurrentDateTime(), days);
    }
    
    /**
     * date加days之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param date
     * @param days
     * @return 新时间
     */
    public static Date getDateAfterDays(Date date, int days) {
        return calculateDate(date, Calendar.DAY_OF_MONTH, days);
    }
    
    /**
     * 当前时间加mouths之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param mouths 月数
     * @return 新时间
     */
    public static Date getDateAfterMouths(int mouths) {
        return getDateAfterMouths(getCurrentDateTime(), mouths);
    }
    
    /**
     * date加mouths之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param date
     * @param mouths
     * @return 新时间
     */
    public static Date getDateAfterMouths(Date date, int mouths) {
        return calculateDate(date, Calendar.MONTH, mouths);
    }
    
    /**
     * 当前时间加years之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param years 年数
     * @return 新时间
     */
    public static Date getDateAfterYears(int years) {
        return getDateAfterYears(getCurrentDateTime(), years);
    }
    
    /**
     * date加years之后的时间</br>
     * 负数代表时间向前，正数代表时间向后
     * @param date
     * @param years
     * @return 新时间
     */
    public static Date getDateAfterYears(Date date, int years) {
        return calculateDate(date, Calendar.YEAR, years);
    }
    
    /**
     * date01是否晚于date02
     * @param date01
     * @param date02
     * @return
     */
    public static boolean isAfter(Date date01, Date date02) {
        return date01.after(date02);
    }
    
    /**
     * date01是否早于date02
     * @param date01
     * @param date02
     * @return
     */
    public static boolean isBefore(Date date01, Date date02) {
        return date01.before(date02);
    }
    
    /**
     * 计算二者相差的天数[忽略时分秒，只计算日期差值]
     * @param date01
     * @param date02
     * @return
     */
    public static long getDaysDifference(Date date01, Date date02) {
        Calendar cal01 = Calendar.getInstance();
        cal01.setTime(date01);
        cal01.set(Calendar.HOUR_OF_DAY, 0);
        cal01.set(Calendar.MINUTE, 0);
        cal01.set(Calendar.SECOND, 0);
        
        Calendar cal02 = Calendar.getInstance();
        cal02.setTime(date02);
        cal02.set(Calendar.HOUR_OF_DAY, 0);
        cal02.set(Calendar.MINUTE, 0);
        cal02.set(Calendar.SECOND, 0);
        
        return (cal01.getTimeInMillis() - cal02.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }
    
    /**
     * 计算二者相差的小时数[忽略分秒]
     * @param date01
     * @param date02
     * @return
     */
    public static long getHoursDifference(Date date01, Date date02) {
        Calendar cal01 = Calendar.getInstance();
        cal01.setTime(date01);
        cal01.set(Calendar.MINUTE, 0);
        cal01.set(Calendar.SECOND, 0);
        
        Calendar cal02 = Calendar.getInstance();
        cal02.setTime(date02);
        cal02.set(Calendar.MINUTE, 0);
        cal02.set(Calendar.SECOND, 0);
        
        return (cal01.getTimeInMillis() - cal02.getTimeInMillis()) / (60 * 60 * 1000);
    }
    
    /**
     * 计算二者相差的分钟数[忽略秒]
     * @param date01
     * @param date02
     * @return
     */
    public static long getMinutesDifference(Date date01, Date date02) {
        Calendar cal01 = Calendar.getInstance();
        cal01.setTime(date01);
        cal01.set(Calendar.SECOND, 0);
        
        Calendar cal02 = Calendar.getInstance();
        cal02.setTime(date02);
        cal02.set(Calendar.SECOND, 0);
        
        return (cal01.getTimeInMillis() - cal02.getTimeInMillis()) / (60 * 1000);
    }
    
    /**
     * 计算二者相差的秒数
     * @param date01
     * @param date02
     * @return
     */
    public static long getSecondsDifference(Date date01, Date date02) {
        Calendar cal01 = Calendar.getInstance();
        cal01.setTime(date01);
        
        Calendar cal02 = Calendar.getInstance();
        cal02.setTime(date02);
        
        return (cal01.getTimeInMillis() - cal02.getTimeInMillis()) / 1000;
    }
    
    /**
     * 时间加减
     * @return
     */
    private static Date calculateDate(Date date, int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        cal.add(field, amount);
        return cal.getTime();
    }
}
