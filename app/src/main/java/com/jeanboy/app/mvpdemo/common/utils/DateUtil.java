package com.jeanboy.app.mvpdemo.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Next on 2016/7/8.
 */
public class DateUtil {

    public DateUtil() {
        throw new AssertionError();
    }

    /**
     * 英文简写如：2010
     */
    public static String FORMAT_Y = "yyyy";

    /**
     * 英文简写如：12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * 英文简写如：1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 英文全称  如：2010-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL_SN = "yyyyMMddHHmmssS";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";

    /**
     * 中文简写  如：2010年12月01日  12时
     */
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";

    /**
     * 中文简写  如：2010年12月01日  12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    public static Calendar calendar = null;
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * @param str 格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date str2Date(String str) {
        return str2Date(str, null);
    }


    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param calendar
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Calendar calendar) {
        return date2Str(calendar, null);
    }


    public static String date2Str(Calendar calendar, String format) {
        if (calendar == null) {
            return null;
        }
        return date2Str(calendar.getTime(), format);
    }

    /**
     * @param date
     * @return 格式：yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date) {
        return date2Str(date, null);
    }


    public static String date2Str(Date date, String format) {// yyyy-MM-dd HH:mm:ss
        if (date == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(date);
        return s;
    }

    /**
     * @return 格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateString() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" +
                (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + " " +
                c.get(Calendar.HOUR_OF_DAY) + ":" +
                c.get(Calendar.MINUTE) + ":" +
                c.get(Calendar.SECOND);
    }

    public static String getCurrentDateString(String format) {
        Calendar calendar = Calendar.getInstance();
        return date2Str(calendar, format);
    }

    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return 增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return 增加之后的天数
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();

        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }


    public static final int SECOND = 1000;
    public static final int MINUTE = SECOND * 60;
    public static final int HOUR = MINUTE * 60;
    public static final int DAY = HOUR * 24;
    public static final int WEEK = DAY * 7;

    /**
     * 返回时间差
     *
     * @param startTime
     * @return
     */
    public String getDateDistance(long startTime) {
        long timeLong = System.currentTimeMillis() - startTime;
        if (timeLong <= 0) {
            return "刚刚";
        } else if (timeLong < MINUTE) {
            return timeLong / SECOND + "秒前";
        } else if (timeLong < HOUR) {
            timeLong = timeLong / MINUTE;
            return timeLong + "分钟前";
        } else if (timeLong < DAY) {
            timeLong = timeLong / HOUR;
            return timeLong + "小时前";
        } else if (timeLong < WEEK) {
            timeLong = timeLong / DAY;
            return timeLong + "天前";
        } else if (timeLong < WEEK * 4) {
            timeLong = timeLong / WEEK;
            return timeLong + "周前";
        } else {
            timeLong = timeLong / DAY;
            return timeLong + "天前";
        }
    }

    public static String dateToString(long time) {
        if (time >= DAY) {
            return (time / DAY) + "天" + (time % DAY / HOUR) + "小时" + (time % DAY % HOUR / MINUTE) + "分钟";
        } else if (time >= HOUR && time < DAY) {
            return (time % DAY / HOUR) + "小时" + (time % DAY % HOUR / MINUTE) + "分钟";
        } else {
            return (time % DAY % HOUR / MINUTE) + "分钟";
        }
    }

    /**
     * @param date1
     * @param date2
     * @return 0 两日期相等，-1 date1在date2之前，1 date1在date2之后
     */
    public static int compare(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(date1);
            c2.setTime(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = c1.compareTo(c2);
        return i;
    }

    /**
     * 根据出生日期计算年龄 过去的日期返回0
     *
     * @param birthDay
     * @return
     */
    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            return 0;
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }

}
