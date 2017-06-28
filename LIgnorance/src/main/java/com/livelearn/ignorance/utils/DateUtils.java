package com.livelearn.ignorance.utils;

import com.apkfuns.logutils.LogUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期相关操作
 */
public class DateUtils {

    private static final long MINUTE_OF_MILLISECONDS = 1000 * 60;
    private static final long HOUR_OF_MILLISECONDS = MINUTE_OF_MILLISECONDS * 60;
    private static final long DAY_OF_MILLISECONDS = HOUR_OF_MILLISECONDS * 24;
    private static final long MOUTH_OF_MILLISECONDS = DAY_OF_MILLISECONDS * 30;
    private static final long YEAR_OF_MILLISECONDS = DAY_OF_MILLISECONDS * 365;

    /**
     * String -->> Calendar
     *
     * @param dateStr 时间字符串
     * @param pattern 时间格式
     */
    public static Calendar dateStrToCalendar(String dateStr, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
            format.parse(dateStr);
            return format.getCalendar();
        } catch (Exception e) {
            LogUtils.e(e);
            return Calendar.getInstance();
        }
    }

    /**
     * String -->> Date
     *
     * @param dateStr 时间字符串 2015-11-02 12:11:11
     * @param pattern 时间格式  yyyy-MM-dd HH:mm:ss
     */
    public static Date dateStrToDate(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.getDefault()).parse(dateStr);
        } catch (Exception e) {
            LogUtils.e(e);
            return new Date();
        }
    }

    /**
     * Calendar -->> String
     *
     * @param calendar 日历
     * @param pattern  时间格式
     */
    public static String calendarToStr(Calendar calendar, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        format.setCalendar(calendar);
        return format.format(calendar.getTime());
    }

    /**
     * Date -> String
     *
     * @param date    日期
     * @param pattern 时间格式
     */
    public static String dateToStr(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendarToStr(calendar, pattern);
    }


    /**
     * oldPattern -> newPattern
     *
     * @param dateStr    时间字符串 20140202
     * @param oldPattern 旧时间格式 yyyyMMdd
     * @param newPattern 新时间格式 yyyy年MM月dd日
     */
    public static String oldDateStrToNewDateStr(String dateStr, String oldPattern, String newPattern) throws ParseException {
        try {
            Calendar calendar = dateStrToCalendar(dateStr, oldPattern);
            return calendarToStr(calendar, newPattern);
        } catch (Exception e) {
            LogUtils.e(e);
            return dateStr;
        }
    }

    /**
     * @param dateStr 传入"yyyy-MM-dd"
     * @return 年龄
     */
    public static long getAge(String dateStr, String pattern) {
        try {
            Date currentDate = new Date();
            Date birthDate = new SimpleDateFormat(pattern, Locale.getDefault()).parse(dateStr);
            long day = (currentDate.getTime() - birthDate.getTime()) / (24 * 60 * 60 * 1000) + 1;
            return day / 365;
        } catch (Exception e) {
            LogUtils.e(e);
            return 0;
        }
    }

    /**
     * 同一时间格式的两个时间相比较
     *
     * @param date1   时间1
     * @param date2   时间2
     * @param pattern 时间格式
     */
    public static int compareToDate(String date1, String date2, String pattern) {
        try {
            DateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            LogUtils.e(e);
            return 0;
        }
    }

    /**
     * 时间处理
     *
     * @param srcTimeStr 原始时间
     * @param srcPattern 原始时间格式
     */
    public static String timeDisposal(String srcTimeStr, String srcPattern) {
        try {
            String processedTime;
            SimpleDateFormat format = new SimpleDateFormat(srcPattern, Locale.getDefault());
            Date srcDate = format.parse(srcTimeStr);
            Date currentTime = format.parse(format.format(new Date(System.currentTimeMillis())));
            Calendar currentCalendar = Calendar.getInstance();
            Calendar srcCalendar = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            today.set(Calendar.YEAR, currentCalendar.get(Calendar.YEAR));
            today.set(Calendar.MONTH, currentCalendar.get(Calendar.MONTH));
            today.set(Calendar.DAY_OF_MONTH, currentCalendar.get(Calendar.DAY_OF_MONTH));
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            currentCalendar.setTime(currentTime);
            srcCalendar.setTime(srcDate);
            if (srcCalendar.after(today)) {
                long differMin = (currentTime.getTime() - srcDate.getTime()) / MINUTE_OF_MILLISECONDS;
                if (differMin >= 5 && differMin <= 60) {
                    processedTime = differMin + "分钟前";
                } else if (differMin < 5) {
                    processedTime = "刚刚";
                } else {
                    processedTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(srcDate);
                }
            } else {
                Calendar thisYear = Calendar.getInstance();
                thisYear.set(Calendar.YEAR, currentCalendar.get(Calendar.YEAR));
                thisYear.set(Calendar.MONTH, Calendar.JANUARY);
                thisYear.set(Calendar.DAY_OF_MONTH, 1);
                thisYear.set(Calendar.HOUR_OF_DAY, 0);
                thisYear.set(Calendar.MINUTE, 0);
                thisYear.set(Calendar.SECOND, 0);
                if (srcCalendar.after(thisYear)) {
                    processedTime = new SimpleDateFormat("MM月dd日 HH:mm:ss", Locale.getDefault()).format(srcDate);
                } else {
                    processedTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.getDefault()).format(srcDate);
                }
            }
            return processedTime;
        } catch (Exception e) {
            LogUtils.e(e);
            return srcTimeStr;
        }
    }

    /**
     * 把时间戳转换成最近的时间
     *
     * @param milliseconds 时间戳
     */
    public static String getRecentlyDate(long milliseconds) {
        long currentMilliseconds = System.currentTimeMillis();
        long timeDistance = currentMilliseconds - milliseconds;
        SimpleDateFormat dateFormat;
        if (timeDistance > 0 && timeDistance <= 1000) { //一秒内
            return "刚刚";
            //过去不超过3天的时间
        } else if (timeDistance > 1000 && timeDistance <= DAY_OF_MILLISECONDS * 3) {
            if (timeDistance / 1000 < 60) {
                return timeDistance / 1000 + "秒前";
            } else if (timeDistance / MINUTE_OF_MILLISECONDS < 60) {
                return timeDistance / MINUTE_OF_MILLISECONDS + "分钟前";
            } else if (timeDistance / HOUR_OF_MILLISECONDS < 24) {
                return timeDistance / HOUR_OF_MILLISECONDS + "小时前";
            } else if (timeDistance / DAY_OF_MILLISECONDS <= 3) {
                return timeDistance / DAY_OF_MILLISECONDS + "天前";
            }
            //将来不超过三天时间
        } else if (timeDistance < 0 && timeDistance >= DAY_OF_MILLISECONDS * -3) {
            timeDistance = timeDistance * -1;
            if (timeDistance / 1000 < 60) {
                return timeDistance / 1000 + "秒后";
            } else if (timeDistance / MINUTE_OF_MILLISECONDS < 60) {
                return timeDistance / MINUTE_OF_MILLISECONDS + "分钟后";
            } else if (timeDistance / HOUR_OF_MILLISECONDS < 24) {
                return timeDistance / HOUR_OF_MILLISECONDS + "小时后";
            } else if (timeDistance / DAY_OF_MILLISECONDS <= 3) {
                return timeDistance / DAY_OF_MILLISECONDS + "天后";
            }
            //过去或将来超过3天，但小于一年
        } else if (Math.abs(timeDistance) < YEAR_OF_MILLISECONDS) {
            dateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
            return dateFormat.format(new Date(milliseconds));
        }
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date(milliseconds));
    }
}
