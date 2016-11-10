package com.allenwtl.util;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


/**
 * 日期工具类
 *
 */
public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final int WEEK_DAYS = 7;

    public static final long SECOND = 1000;

    public static final long MINUTE = 60 * SECOND;

    public static final long HOUR = 60 * MINUTE;

    public static final long DAY = 24 * HOUR;

    public static final long WEEK = WEEK_DAYS * DAY;

	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Date addMinutes(Calendar date, int i) {
		Calendar copy = ((Calendar) date.clone());
		copy.add(Calendar.MINUTE, i);
		return copy.getTime();
	}

	public static Date nowAddMinutes(int i) {
		Calendar copy = Calendar.getInstance();
		copy.add(Calendar.MINUTE, i);
		return copy.getTime();
	}

	public static Calendar calendarAddMinutes(int i) {
		Calendar copy = Calendar.getInstance();
		copy.add(Calendar.MINUTE, i);
		return copy;
	}

	public static Calendar calendarAddDays(int i) {
		Calendar copy = Calendar.getInstance();
		copy.add(Calendar.DATE, i);
		return copy;
	}


    /**
     * 获取多少秒以前的数据
     * @param date
     * @param millis
     * @return
     */
    public static Date getTimeBeforMillis(Date date, int millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long timeLong = calendar.getTimeInMillis();
        timeLong = timeLong - millis;
        calendar.setTimeInMillis(timeLong);
        return calendar.getTime();
    }

    /***
     * 格式化时间
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return (String) sdf.format(date).toString();
    }

    /**
     * 获取当前时间返回:yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }




    /**
     * 获取当前时间yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getTime2() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * 日期转化为字符串:(格式:yyyy-MM-dd)
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String getDateString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 日期转化为字符串:(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     *            传入日期
     * @return
     */
    public static String getDateString2(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 日期转化为字符串(根据指定格式化格式)
     *
     * @param date
     *            传入日期
     * @return
     */
    public static String getDateStringByZdGs(Date date, String gs) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(gs);
        return sdf.format(date);
    }

    /***
     * 格式化时间
     *
     * @return
     */
    public static String formatDate(Calendar calendar) {
        return (String) sdf.format(calendar.getTime()).toString();
    }

    /**
     * 获取日期字符串:(根据日期字符串获取该日期的凌晨日期字符串(传入:yyyy-MM-dd))
     *
     * @param str
     * @return
     */
    public static String getDateStringByDayStart(String str) {
        return str + " " + "00:00:00";
    }

    /**
     * 获取当天的开始时间 yyyy-MM-dd 00:00:00
     *
     * @return
     */
    public static Date getTodayStartTime() {
        return getDateByString(getCurrentDateStringByDayStart());
    }

    /***
     * 获取当天的结束时间 yyyy-MM-dd 23:59:59
     *
     * @return
     */
    public static Date getTodayEndTime() {
        return getDateByString(getCurrentDateStringByDayEnd());
    }

    /**
     * 获取日期字符串:(根据日期字符串获取该日期的午夜日期字符串(传入:yyyy-MM-dd))
     *
     * @param str
     * @return
     */
    public static String getDateStringByDayEnd(String str) {
        return str + " " + "23:59:59";
    }

    /**
     * 获取当前时间yyyy-MM-dd
     *
     * @return
     */
    public static String getTime6() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 获取今天凌晨日期字符串
     *
     * @return
     */
    public static String getCurrentDateStringByDayStart() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date) + " " + "00:00:00";
    }

    /**
     * 获取某个时间的凌晨日期字符串
     *
     * @return
     */
    public static String getCurrentDateStringByDayStart(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date) + " " + "00:00:00";
    }

    /****
     * 获取当前时间小时的0分开始
     * @return
     */
    public static Calendar getCurrentHourFirstTime() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.set(year, month, day, hour, 0, 1);
        return calendar;
    }

    public static Calendar getCurrentHourLastTime() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.set(year, month, day, hour, 59, 59);
        return calendar;
    }

    /****
     * 获取当前时间凌晨零点
     * @return
     */
    public static Calendar getCurrentDayFirstTime() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 1);
        return calendar;
    }

    /****
     * 获取几天前/后凌晨零点,count表示指定间隔天数,可以为负值
     * @return
     */
    public static Calendar getSomeDayFirstTime(int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, count);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);
        return calendar;
    }

    public static Calendar getCurrentDayLastTime() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        return calendar;
    }

    /**
     * 获取今天午夜日期字符串
     *
     * @return
     */
    public static String getCurrentDateStringByDayEnd() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date) + " " + "23:59:59";
    }

    /**
     * 获取某个时间的午夜日期字符串
     *
     * @return
     */
    public static String getCurrentDateStringByDayEnd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date) + " " + "23:59:59";
    }

    /**
     * 根据Date获取Calender
     *
     * @param date
     * @return
     */
    public static Calendar getCalenderByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 日期转化为字符串(yyyyMMdd)
     *
     * @param date
     *            传入日期
     * @return
     */
    public static String getDateString3(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    /**
     * 日期转化为字符串(yyyy-MM-dd hh:mm)
     *
     * @param date
     *            传入日期
     * @return
     */
    public static String getDateString4(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    /**
     * 字符串转化为日期:(yyyyMMdd)
     *
     * @param str
     *            格式:yyyyMMdd
     * @return
     */
    public static Date getDate3(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            logger.warn("日期转换错误：" + e.getMessage());
        }
        return date;
    }

    /**
     * 根据Calender获取Date
     *
     * @return
     */
    public static Date getDateByCalender(Calendar calendar) {
        if (calendar != null) {
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 获取当前日(根据日期yyyy-MM-dd HH:mm:ss)
     *
     * @return 当前日
     */
    public static Integer getDayByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取当前日期(Calendar.getTime())
     *
     * @return 当前日期
     */
    public static Date getDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    /**
     * 获取日期(根据日期字符串)
     *
     * @param str
     * @return
     */
    public static Date getDateByString(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            logger.warn("日期转换错误：" + e.getMessage());
        }
        return date;
    }

    /**
     *
     * 获取指定日期之后的指定天数的日期
     *
     * @return
     */
    public static Date getDateAfterByZdDateAndDays(Date date, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +(day));
        return calendar.getTime();
    }

    /**
     * 字符串转化为日期:(yyyy-MM-dd HH:mm:ss)
     *
     * @param str
     *            格式:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date getDate2(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!str.contains(":")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            logger.warn("日期转换错误：" + e.getMessage());
        }
        return date;
    }

    public static String dateToString(Date date, String format) {
        if (format == null) {
            return "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    public static String dateToYYYYMMDD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getByTimeMillis(long timeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        return dateToString(c.getTime());
    }

    /**
     * 获取凌晨日期yyyy-MM-dd
     *
     * @param str
     * @return
     */
    public static Date getDateByDayStart(String str) {
        return getDate2(getDateStringByDayStart(str));
    }

    /**
     * 获取午夜日期yyyy-MM-dd
     *
     * @param str
     * @return
     */
    public static Date getDateByDayEnd(String str) {
        return getDate2(getDateStringByDayEnd(str));
    }

    /**
     * 获取当前日
     *
     * @return 当前日
     */
    public static Integer getDay() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        return day;
    }

    /**
     * 计算:时间差,返回(time2-time1)的天数(24小时)
     *
     * @param time1
     *            格式为yyyy-MM-dd HH:mm:ss的日期
     * @param time2
     *            格式为yyyy-MM-dd HH:mm:ss的日期
     * @return
     */
    public static Long calcSubDay(String time1, String time2) {
        long days = 0;
        try {
            if (time1 == null || time1.equals("")) {
                time1 = getTime();
            }
            long cha = 0;
            days = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date d1 = sdf.parse(time1);
                Date d2 = sdf.parse(time2);
                cha = d2.getTime() - d1.getTime();
                days = cha / (1000 * 60 * 60 * 24);
            } catch (Exception e) {
                logger.warn("日期转换错误：" + e.getMessage());
            }
        } catch (Exception e) {
            logger.warn("日期转换错误：" + e.getMessage());
        }
        return days;
    }

    // 检查当前日期是否为指定日期的后一天
    public static Boolean isAnotherDay(Calendar zdCalendar) {
        if (zdCalendar != null) {
            Date date = zdCalendar.getTime();
            Date currentDateStartTime = getTodayStartTime();

            date = getDateByDayStart(getDateString(date));
            if (currentDateStartTime.after(date)) {
                Long cha = DateUtil.calcSubDay(DateUtil.getDateString2(date),
                        DateUtil.getDateString2(currentDateStartTime));
                if (cha == 1) {
                    return true;
                }
            }
            return false;
        }

        return false;
    }

    // 检查当前日期是否为指定日期的后一天,或后一天之后
    public static Boolean isAfterDayByZdCa(Calendar zdCalendar) {
        if (zdCalendar != null) {
            Date zdDate = zdCalendar.getTime();
            Date currentDate = DateUtil.getDate();
            // 若当前日期在指定日期之后
            if (currentDate.after(zdDate)) {
                // 判断是否为同一天
                boolean is = org.apache.commons.lang3.time.DateUtils.isSameDay(currentDate, zdDate);
                if (!is) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *
     * 比较:比较时间,前者比后者小返回-1,前者比后者大返回1,相等返回0(yyyy-MM-dd)
     *
     * @param strDate1
     * @param strDate2
     * @return
     */
    public static Integer calcCompareDate(String strDate1, String strDate2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = df.parse(strDate1);
            Date date2 = df.parse(strDate2);
            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            logger.warn("日期转换错误：" + e.getMessage());
        }
        return 0;
    }

    /**
     *
     * 比较:比较时间,前者比后者小返回-1,前者比后者大返回1,相等返回0(yyyy-MM-dd HH:mm:ss)
     *
     * @param strDate1
     * @param strDate2
     * @return
     */
    public static Integer calcCompareDate2(String strDate1, String strDate2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = df.parse(strDate1);
            Date date2 = df.parse(strDate2);
            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            logger.warn("日期转换错误：" + e.getMessage());
        }
        return 0;
    }

    /**
     * 计算:两个日期的时间差(获取相差的秒数)
     *
     * @param time1
     * @param time2
     * @return time2-time1
     */
    public static Long calcSubTime003(String time1, String time2) {
        long hoursToSecond = 0L;
        long hoursToMinutes = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long t1 = 0L;
        long t2 = 0L;
        try {
            t1 = sdf.parse(time1).getTime();
        } catch (Exception e) {
            logger.warn("日期转换错误：" + e.getMessage());
        }
        try {
            t2 = sdf.parse(time2).getTime();
        } catch (Exception e) {
            logger.warn("日期转换错误：" + e.getMessage());
        }
        // 因为t1-t2得到的是毫秒级,所以要除3600000得出小时.算天数或秒同理
        int hours = (int) ((t2 - t1) / 3600000);
        int minutes = (int) (((t2 - t1) / 1000 - hours * 3600) / 60);
        int second = (int) ((t2 - t1) / 1000 - hours * 3600 - minutes * 60);

        // 若小时不为0,将小时化为秒
        if (hours != 0) {
            hoursToSecond = hours * 3600;
        }
        // 若分不为0,将分化为秒
        if (minutes != 0) {
            hoursToMinutes = minutes * 60;
        }
        return (hoursToSecond + hoursToMinutes + second);
    }

    /**
     *
     * 比较:比较时间是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        }

        if (date1 != null && date2 != null && date1.getTime() == date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static Calendar getCurrentCalendar() {
        return Calendar.getInstance();
    }

    /**根据日期取得周几*/
    public static int getWeekDayByDateStr(String dateStr) {
        Calendar d = parseYYYYMMDD(dateStr);
        Date tempDate = d.getTime();
        return tempDate.getDay() == 0 ? 7 : tempDate.getDay();
    }


    /**
     * 比较两个日期之间相差多少秒
     *
     */
    public static Long compareSecond(Calendar c1, Calendar c2) {
        assert (c1 != null);
        assert (c2 != null);
        return (Long) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / 1000);
    }

    /**
     * 比较两个日期之间相差多少毫秒
     *
     */
    public static Long compareTimeInMillis(Calendar c1, Calendar c2) {
        assert (c1 != null);
        assert (c2 != null);
        return (Long) (c1.getTimeInMillis() - c2.getTimeInMillis());
    }

    /**
     * 比较两个日期之间相差多少分钟
     *
     */
    public static int compareMinutes(Calendar c1, Calendar c2) {
        assert (c1 != null);
        assert (c2 != null);
        return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / 1000 / 60);
    }

    /**
     * 根据所给年份，返回此年的1月1日零时零分零秒日历对象
     *
     * @param year
     */
    public static Calendar getYearCalendar(int year) {
        Calendar result = DateUtil.now();
        result.clear();
        result.set(Calendar.YEAR, year);
        return result;
    }

    public static Calendar getYearCalendar() {
        Calendar c = DateUtil.now();
        return getYearCalendar(c.get(Calendar.YEAR));
    }

    public static Calendar getNextYearCalendar(int years) {
        Calendar c = DateUtil.now();
        return getYearCalendar(c.get(Calendar.YEAR) + years);
    }

    /**
     * 比较c1,c2相差多少天 返回0 则代表同一天<br>
     *
     */
    public static int interval(final Calendar date1, final Calendar date2) {
        if (isSameDay(date1, date2))
            return 0;
        Calendar bigCopy = (Calendar) date1.clone();
        Calendar smallCopy = (Calendar) date2.clone();
        setTimeToMidnight(bigCopy);
        setTimeToMidnight(smallCopy);
        long day = (bigCopy.getTimeInMillis() - smallCopy.getTimeInMillis()) / DAY;
        return (int) day;
    }

    public static int daysRented(final Calendar end, final Calendar start) {
        int days = interval(end, start);
        if (days == 0)
            return 1;
        return days;

    }

    private static void setTimeToMidnight(Calendar calendar) {
        Calendar clone = (Calendar) calendar.clone();
        calendar.clear();
        calendar.set(Calendar.YEAR, clone.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, clone.get(Calendar.MONTH));
        calendar.set(Calendar.DATE, clone.get(Calendar.DATE));
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        return DateUtils.isSameDay(cal1, cal2);
    }

    /**
     *
     * 与现在比较相差多少小时
     */
    public static int comparehours(Calendar small) {
        return compareMinutes(DateUtil.now(), small) / 60;
    }

    /**
     * 与今天相差多少天 返回0 则代表今天
     */
    public static int compareTody(Calendar small) {
        return interval(DateUtil.now(), small);
    }

    /**
     * 比较c1,c2相差多少月
     */
    public static int compareMonth(Calendar c1, Calendar c2) {
        return compareYear(c1, c2) * 12 + (c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH));
    }

    /**
     * 比较c1,c2相差多少年
     */
    public static int compareYear(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
    }

    public static int getCurrentYear() {
        Calendar c1 = DateUtil.now();
        return c1.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        return DateUtil.now().get(Calendar.MONTH);
    }

    /**
     * 判断此时是否在两时间之间
     */
    public static boolean isNowBetween(Calendar start, Calendar end) {
        Calendar now = now();
        return now.after(start) && now.before(end);
    }

    /**
     * 是否是今天
     */
    public static boolean isToday(Calendar date) {
        return DateUtil.interval(date, DateUtil.now()) == 0;
    }

    /**
     * 使用实例:
     *
     * <pre>
     * parseYYYYMMDD(&quot;20071011&quot;, null);//正确返回Calendar
     * parseYYYYMMDD(&quot;2007911&quot;, defaultDate);//返回defaultDate
     * </pre>
     *
     * @param parameterValue
     * @param defaultValue
     */
    public static Calendar parseYYYYMMDD(String parameterValue, Calendar defaultValue) {
        try {
            return parseYYYYMMDD(parameterValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将一个 年年年年月月日日 的字符串转换为日历类
     *
     * @param parameterValue
     */
    public static Calendar parseYYYYMMDD(String parameterValue) {
        String format = "yyyyMMdd";
        return parse(parameterValue, format);
    }

    /**
     * 字符串转为长
     *
     * @param parameterValue
     */
    public static Calendar parseYYYY_MM_DD_HH_MM_SS(String parameterValue) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return parse(parameterValue, format);
    }

    public static Calendar parse(String string, String... format) {
        Calendar result = Calendar.getInstance();
        try {
            result.setTime(DateUtils.parseDate(string, format));
            return result;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 将一个 年年年年月月日日 的字符串转换为日历类
     *
     * @param parameterValue
     */
    public static Calendar parseYYYY_MM_DD(String parameterValue) {
        String format = "yyyy-MM-dd";
        return parse(parameterValue, format);
    }

    private static Calendar formerDate;

    public static void setSystemCurrentDate(Calendar date) {
        if (formerDate == null)
            formerDate = DateUtil.now();
        try {
            String command = String.format("cmd.exe /c date %s", toYYYY_MM_DD(date));
            Process cc = Runtime.getRuntime().exec(command);
            cc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void resetTime() {
        if (formerDate == null)
            return;
        setSystemCurrentDate(formerDate);
    }

    public static Calendar getOneYearBeforeNow() {
        Calendar result = now();
        result.add(Calendar.YEAR, -1);
        return result;
    }

    public static Calendar getOneWeekBeforeNow() {
        return addDate(-WEEK_DAYS);
    }

    public static Calendar getTwoWeekBeforeNow() {
        return addDate(-WEEK_DAYS * 2);
    }

    public static Calendar getThreeMonthBeforeNow() {
        return getTheDayZero(addDate(-30 * 3));
    }

    public static Calendar getOneMonthBeforeNow() {
        return getTheDayZero(addDate(-30 * 1));
    }

    public static Calendar getMidNightFutureAfter(Calendar date, int days) {
        Calendar future = (Calendar) date.clone();
        future.add(Calendar.DATE, days);
        return future;
    }

    private static Calendar addDate(int days) {
        Calendar result = now();
        result.add(Calendar.DATE, days);
        return result;
    }

    /**
     * 返回此日期的零点整，如2007-3-14 19:00:35 返回 2007-3-14 00:00:00
     *
     * @param date
     */
    public static Calendar getTheDayZero(Calendar date) {
        Calendar result = (Calendar) date.clone();
        result.set(Calendar.HOUR_OF_DAY, 0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);
        return result;
    }

    public static Calendar getTheDayZero() {
        return getTheDayZero(DateUtil.now());
    }

    public static Calendar getTheDayMidnight() {
        return getTheDayMidnight(DateUtil.now());
    }

    /**
     * 返回此日期的午夜，如2007-3-14 19:00:35 返回 2007-3-14 23:59:59
     *
     * @param date
     */
    public static Calendar getTheDayMidnight(Calendar date) {
        Calendar result = (Calendar) date.clone();
        result.set(Calendar.HOUR_OF_DAY, 23);
        result.set(Calendar.MINUTE, 59);
        result.set(Calendar.SECOND, 59);
        result.set(Calendar.MILLISECOND, 999);
        return result;
    }

    public static Calendar getTheMonthCalendar() {
        Calendar result = Calendar.getInstance();
        result.set(Calendar.DAY_OF_MONTH, 1);
        result.set(Calendar.HOUR_OF_DAY, 0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);
        return result;
    }

    public static String nowTimeStamp() {
        return toTimeStamp(now());
    }

    public static String toMMDD(Calendar date) {
        return new SimpleDateFormat("MMdd").format(date.getTime());
    }

    public static String toYYYYMMDD(Calendar date) {
        return new SimpleDateFormat("yyyyMMdd").format(date.getTime());
    }

    public static String toYYMMDD(Calendar date) {
        return new SimpleDateFormat("yyMMdd").format(date.getTime());
    }

    public static String toYYYY_MM_DD(Calendar date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
    }

    public static String toYYYYMMDDHHMM(Calendar date) {
        return new SimpleDateFormat("yyyyMMddHHmm").format(date.getTime());
    }

    public static String toYYMMDDHHMMSS(Calendar date) {
        return new SimpleDateFormat("yyMMddHHmmss").format(date.getTime());
    }

    public static String toMM_DD_HH_mm_ss(Calendar date) {
        return new SimpleDateFormat("MM-dd HH:mm:ss").format(date.getTime());
    }

    public static String toYYYY_MM_DDZH(Calendar date) {
        return new SimpleDateFormat("yyyy年MM月dd日").format(date.getTime());
    }

    public static String toTimeStamp(Calendar calendar) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
    }

    public static String toTimeStampFm(Calendar calendar) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    public static void toOracleDateStrForMap(Map<String, Object> values) {
        for (Entry<String, Object> entry : values.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (null != value) {
                System.out.println("the class of the value is"+value.getClass());
                if (Calendar.class.isAssignableFrom(value.getClass())) {
                    value = toOracleDateStr((Calendar) value);
                    values.put(key, value);
                }
            }
        }
    }

    public static String toOracleDateStr(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    public static String toyyyy_MM_dd_HH_mm(Calendar calendar) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(calendar.getTime());
    }

    public static String toYYYY_MM(Calendar date) {
        return new SimpleDateFormat("yyyy-MM").format(date.getTime());
    }
    public static String toYYYYMM(Calendar date) {
        return new SimpleDateFormat("yyyyMM").format(date.getTime());
    }

    public static String toCNyyyy_MM_dd_HH_mm(Calendar calendar) {
        return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分").format(calendar.getTime());
    }

    public static String toDateStamp(Calendar calendar) {
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    public static String toHHmm(Calendar date) {
        return new SimpleDateFormat("HH:mm").format(date.getTime());
    }

    public static String toHHmmss(Calendar date) {
        return new SimpleDateFormat("HH:mm:ss").format(date.getTime());
    }

    public static String toHHmmss_(Calendar date) {
        return new SimpleDateFormat("HHmmss").format(date.getTime());
    }

    public static String toHHmmssSSS(Calendar date) {
        return new SimpleDateFormat("HH:mm:ss SSS").format(date.getTime());
    }

    public static String toDd_HHmm(Calendar date) {
        return new SimpleDateFormat("dd/HH:mm").format(date.getTime());
    }

    private static String[] parsePatterns;

    static {
        parsePatterns = new String[] { "yyyy/MM/dd", "yyyy-MM-dd", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm" };
    }

    public static Calendar parseTimeStamp(String string) {
        Calendar result = Calendar.getInstance();
        try {
            result.setTime(DateUtils.parseDate(string, parsePatterns));
            return result;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Calendar add(Calendar date, int field, int i) {
        Calendar copy = ((Calendar) date.clone());
        copy.add(field, i);
        return copy;
    }

    /**
     * @param time
     *            如15:30或15:30:20
     * @param offset
     *            时区
     */
    public static Calendar setTodayTime(String time, int offset) {
        Calendar result = Calendar.getInstance();
        String[] info = time.split(":");
        int hour = Integer.valueOf(info[0]);
        int minute = Integer.valueOf(info[1]);

        hour -= offset;
        if (hour < 0)
            hour += 24;

        result.set(Calendar.HOUR_OF_DAY, hour);
        result.set(Calendar.MINUTE, minute);
        result.set(Calendar.SECOND, 0);

        return result;
    }

    public static Calendar getCalendarFromSqlDate(java.sql.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    public static Calendar now() {
        return Calendar.getInstance();
    }

    public static Calendar getMixDate() {
        Calendar date = now();
        date.clear();
        return date;
    }

    /**
     * 获取当前日期属于一年第几周
     * @return
     * @create_time 2011-3-29 上午11:53:19
     */
    public static int getWeekNumber() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 星期日返回1，星期一返回2，依次类推
     * @param cal
     * @return
     * @create_time 2011-5-6 下午08:12:23
     */
    public static int getWeekIndex(Calendar cal) {
        int week = cal.get(Calendar.DAY_OF_WEEK);//星期日返回1，星期一返回2，依次类推
        if (week == 1) {
            return 0;
        } else {
            return week - 2;
        }
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /** 竞彩足球周几的时间转换  key = 周日,value = 110812*/
    public static Map<String, String> getOneWeekDay() {
        // 获得一周的key-value key=周日,value=110812
        Map<String, String> weekDayMap = new HashMap<String, String>();
        String weekArr[] = new String[] { "", "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar now = Calendar.getInstance();
        weekDayMap.put(weekArr[now.get(Calendar.DAY_OF_WEEK)], DateUtil.toYYMMDD(now));
        for (int i = 1; i <= 6; i++) {
            now.add(Calendar.DAY_OF_MONTH, 1);
            weekDayMap.put(weekArr[now.get(Calendar.DAY_OF_WEEK)], DateUtil.toYYMMDD(now));
        }
        return weekDayMap;
    }

    /** 竞彩足球周几的时间转换  key = 周日,value = 110812*/
    public static Map<String, String> getNumOneWeekDay() {
        // 获得一周的key-value key=周日,value=110812
        Map<String, String> weekDayMap = new HashMap<String, String>();
        //1:周一
        String weekArr[] = new String[] { "", "7", "1", "2", "3", "4", "5", "6" };
        Calendar now = Calendar.getInstance();
        weekDayMap.put(weekArr[now.get(Calendar.DAY_OF_WEEK)], DateUtil.toYYMMDD(now));
        for (int i = 1; i <= 6; i++) {
            now.add(Calendar.DAY_OF_MONTH, 1);
            weekDayMap.put(weekArr[now.get(Calendar.DAY_OF_WEEK)], DateUtil.toYYMMDD(now));
        }
        return weekDayMap;
    }

    /** 竞彩足球周几的时间转换  key = 110812,value = 周日*/
    public static Map<String, String> getOtherOneWeekDay() {
        // 获得一周的key-value key=周日,value=110812
        Map<String, String> weekDayMap = new HashMap<String, String>();
        String weekArr[] = new String[] { "", "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar now = Calendar.getInstance();
        weekDayMap.put(DateUtil.toYYMMDD(now), weekArr[now.get(Calendar.DAY_OF_WEEK)]);
        for (int i = 1; i <= 6; i++) {
            now.add(Calendar.DAY_OF_MONTH, 1);
            weekDayMap.put(DateUtil.toYYMMDD(now), weekArr[now.get(Calendar.DAY_OF_WEEK)]);
        }
        //这里多加一个当前天跨度之前的一周
        now = Calendar.getInstance();
        for (int i = 1; i <= 6; i++) {
            now.add(Calendar.DAY_OF_MONTH, -1);
            weekDayMap.put(DateUtil.toYYMMDD(now), weekArr[now.get(Calendar.DAY_OF_WEEK)]);
        }
        return weekDayMap;
    }

    public static Calendar convertSqlDate(java.sql.Date date) {
        long mill = date.getTime();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(mill);
        return now;
    }




    public static List<String> getDateBetweenStr(Calendar startTime, Calendar endTime) {
        Calendar start = (Calendar) startTime.clone();
        Calendar end = (Calendar) endTime.clone();
        List<String> list = new ArrayList<String>();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start.getTime()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end.getTime()));
        if (start.get(Calendar.YEAR) == end.get(Calendar.YEAR)) {//同年
            if (start.get(Calendar.DAY_OF_YEAR) == end.get(Calendar.DAY_OF_YEAR)) {
                list.add(new SimpleDateFormat("yyyy-MM-dd").format(start.getTime()));
            } else {
                for (int day = start.get(Calendar.DAY_OF_YEAR); day < end.get(Calendar.DAY_OF_YEAR); start.add(
                        Calendar.DAY_OF_YEAR, 1)) {
                    list.add(new SimpleDateFormat("yyyy-MM-dd").format(start.getTime()));
                    day = start.get(Calendar.DAY_OF_YEAR);
                }
            }

        } else {//跨年
                //开始时间当前年最后一天
            Calendar last = (Calendar) start.clone();
            last.set(Calendar.MONTH, 11);
            last.set(Calendar.DAY_OF_MONTH, last.getActualMaximum(Calendar.DAY_OF_MONTH));

            for (int day = start.get(Calendar.DAY_OF_YEAR); day < last.get(Calendar.DAY_OF_YEAR); start.add(
                    Calendar.DAY_OF_YEAR, 1)) {
                list.add(new SimpleDateFormat("yyyy-MM-dd").format(start.getTime()));
                day = start.get(Calendar.DAY_OF_YEAR);
            }

            //结束时间同年第一天
            Calendar first = (Calendar) end.clone();
            first.set(Calendar.MONTH, 0);
            first.set(Calendar.DAY_OF_MONTH, first.getActualMinimum(Calendar.DAY_OF_MONTH));

            for (int day = first.get(Calendar.DAY_OF_YEAR); day < end.get(Calendar.DAY_OF_YEAR); first.add(
                    Calendar.DAY_OF_YEAR, 1)) {
                list.add(new SimpleDateFormat("yyyy-MM-dd").format(first.getTime()));
                day = first.get(Calendar.DAY_OF_YEAR);
            }

        }

        return list;

    }
    /**
     * 通过字符串格式的时间参数返回中文的周几
     * 20140101
     * 140101
     * @param dateStr
     *  字符格式的时间参数
     * @param format
     * 时间参数格式  yyyMMdd
     * @return
     */
    public static String getCnWeekByDateStr(String dateStr,String format){
    	return getCNWeekByCalendar(parse(dateStr, format));
    }
    /**
     * 获取中文 周几
     * @param c calendar
     * @return
     */
    public static String getCNWeekByCalendar(Calendar c){
    	String weekArr[] = new String[] { "", "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
    	if (c != null) {
    		return  weekArr[c.get(Calendar.DAY_OF_WEEK)];
		}
    	return "";
    }

    /**
     * 将date转为calendar
     * @param date
     * @return
     */
    public static Calendar dateToCalendar(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	return cal;
    }

    public static int getIntervalSecondMidnight() {
        Calendar nowCal = Calendar.getInstance();
        Calendar midCal = getTheDayMidnight();
        Date beginTime = nowCal.getTime();
        Date endTime = midCal.getTime();
        long t1 = beginTime.getTime();
        long t2 = endTime.getTime();
        return (int) ((t2 - t1) / (1000));
    }

	public static Calendar  getTime(Date date, int day, int hour, int minute, int second){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) - day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		return c;
	}


	public static Date parseBounsTimesAccordingToSumary(String summary,String incomTime) throws Exception{
		String monthDay =  summary.split("-")[0];
		String year=incomTime.substring(0, 4);
		String incomeMonthDay=incomTime.substring(4, 8);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		if(!monthDay.equals(incomeMonthDay) && monthDay.equals("1231")){
			year  = Integer.parseInt(year)-1+"";
		}
		return simpleDateFormat.parse(year+monthDay);
	}



    /**
     * 获取当前时间yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrFullHour() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        return sdf.format(date);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getDateString2(getSomeDayFirstTime(-2).getTime()));

    }

    /**
     * 得到几天前的时间
      *
      * @param d
      * @param day
      * @return
      */
     public static Date getDateBefore(Date d, int day) {
         Calendar now = Calendar.getInstance();
         now.setTime(d);
         now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
         return now.getTime();
     }

    /**
     * 评论中 发现的时候 显示规则
     * @return
     */
    public static String getCommentShowTime(Calendar c1){
        Calendar nowTime = Calendar.getInstance();
        long second = compareSecond(nowTime, c1);
        int minute = compareMinutes(nowTime, c1);
        int hour = comparehours(c1);
        int day = compareTody(c1);
        int month = compareMonth(nowTime, c1);
        int year = compareYear(nowTime, c1);
        if(year != 0){
            return (year+"年前");
        }
        if(month!=0){
            return month+"月前";
        }
        if(day!=0){
            return day+"天前";
        }
        if(hour!=0){
            return hour+"小时前";
        }
        if(minute!=0){
            return minute+"分钟前";
        }
        if(second!=0){
            return second+"秒前";
        }
        return "";
    }

}
