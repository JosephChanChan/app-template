package com.joseph.framework.utils.stomp;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * @author liuanxin
 * @author Joseph
 */
public class DateKit {

    /** 当前时间 */
    public static Date now() {
        return new Date();
    }

    /** 当前日期 */
    public static java.sql.Date today() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    /** 返回 yyyy-MM-dd HH:mm:ss 格式的当前时间 */
    public static String nowTime() {
        return now(DateFormatType.YYYY_MM_DD_HH_MM_SS);
    }

    /** 返回 yyyy-MM-dd HH:mm:ss SSS 格式的当前时间 */
    public static String detailNowTime() {
        return now(DateFormatType.YYYY_MM_DD_HH_MM_SS_SSS);
    }

    /** 获取当前时间日期的字符串 */
    public static String now(DateFormatType dateFormatType) {
        return format(now(), dateFormatType);
    }
    /** 格式化日期 yyyy-MM-dd */
    public static String formatDate(Date date) {
        return format(date, DateFormatType.YYYY_MM_DD);
    }
    /** 格式化时间 HH:mm:ss */
    public static String formatTime(Date date) {
        return format(date, DateFormatType.HH_MM_SS);
    }
    /** 格式化日期和时间 yyyy-MM-dd HH:mm:ss */
    public static String formatFull(Date date) {
        return format(date, DateFormatType.YYYY_MM_DD_HH_MM_SS);
    }

    /** 格式化日期对象成字符串 */
    public static String format(Date date, DateFormatType type) {
        if (CommonUtil.isBlank(date) || CommonUtil.isBlank(type)) {
            return CommonUtil.EMPTY;
        }

        return DateTimeFormat.forPattern(type.getValue()).print(date.getTime());
    }

    /**
     * 将字符串转换成 Date 对象. 类型基于 DateFormatType 一个一个试. cst 格式麻烦一点
     *
     * @see DateFormatType
     */
    public static Date parse(String source) {
        if (CommonUtil.isBlank(source)) {
            return null;
        }

        source = source.trim();
        for (DateFormatType type : DateFormatType.values()) {
            try {
                if (type.isCst()) {
                    // cst 单独处理
                    return new SimpleDateFormat(type.getValue(), Locale.ENGLISH).parse(source);
                } else {
                    Date date = DateTimeFormat.forPattern(type.getValue()).parseDateTime(source).toDate();
                    if (date != null) {
                        return date;
                    }
                }
            } catch (ParseException | IllegalArgumentException e) {
                // ignore
            }
        }
        return null;
    }

    /** 获取一个日期所在天的最开始的时间(00:00:00 000), 对日期查询尤其有用 */
    public static Date getDayStart(Date date) {
        if (CommonUtil.isBlank(date)) {
            return null;
        }
        return getDateTimeStart(date).toDate();
    }
    private static DateTime getDateTimeStart(Date date) {
        return new DateTime(date)
                .hourOfDay().withMinimumValue()
                .minuteOfHour().withMinimumValue()
                .secondOfMinute().withMinimumValue()
                .millisOfSecond().withMinimumValue();
    }
    /** 获取一个日期所在天的最晚的时间(23:59:59 999), 对日期查询尤其有用 */
    public static Date getDayEnd(Date date) {
        if (CommonUtil.isBlank(date)) {
            return null;
        }
        return getDateTimeEnd(date).toDate();
    }
    private static DateTime getDateTimeEnd(Date date) {
        return new DateTime(date)
                .hourOfDay().withMaximumValue()
                .minuteOfHour().withMaximumValue()
                .secondOfMinute().withMaximumValue()
                .millisOfSecond().withMaximumValue();
    }

    /**
     * 取得指定日期指定天后的日期
     *
     * @param day 正数表示多少天后, 负数表示多少天前
     */
    public static Date addDays(Date date, int day) {
        return new DateTime(date).plusDays(day).toDate();
    }
    /**
     * 取得指定日期指定个月后的日期
     *
     * @param month 正数表示多少月后, 负数表示多少月前
     */
    public static Date addMonths(Date date, int month) {
        return new DateTime(date).plusMonths(month).toDate();
    }
    /**
     * 取得指定日期指定天后的日期
     *
     * @param year 正数表示多少年后, 负数表示多少年前
     */
    public static Date addYears(Date date, int year) {
        return new DateTime(date).plusYears(year).toDate();
    }
    /**
     * 取得指定日期指定分钟后的日期
     *
     * @param minute 正数表示多少分钟后, 负数表示多少分钟前
     */
    public static Date addMinute(Date date, int minute) {
        return new DateTime(date).plusMinutes(minute).toDate();
    }
    /**
     * 取得指定日期指定小时后的日期
     *
     * @param hour 正数表示多少小时后, 负数表示多少小时前
     */
    public static Date addHours(Date date, int hour) {
        return new DateTime(date).plusHours(hour).toDate();
    }
    /**
     * 取得指定日期指定秒后的日期
     *
     * @param second 正数表示多少秒后, 负数表示多少秒前
     */
    public static Date addSeconds(Date date, int second) {
        return new DateTime(date).plusSeconds(second).toDate();
    }
    /**
     * 取得指定日期指定周后的日期
     *
     * @param week 正数表示多少周后, 负数表示多少周前
     */
    public static Date addWeeks(Date date, int week) {
        return new DateTime(date).plusWeeks(week).toDate();
    }

    /** 传入的时间晚于当前时间就返回传入的时间, 否则就返回当前时间 */
    public static Date before(Date date) {
        Date now = now();
        return now.after(date) ? now : date;
    }

    /** 传入的时间是不是当月当日. 用来验证生日 */
    public static boolean wasBirthday(Date date) {
        DateTime dt = DateTime.now();
        DateTime dateTime = new DateTime(date);
        return dt.getMonthOfYear() == dateTime.getMonthOfYear() && dt.getDayOfMonth() == dateTime.getDayOfMonth();
    }

    /** 计算两个日期之间相差的天数. 如果 start 比 end 大将会返回负数 */
    public static int betweenDay(Date start, Date end) {
        if (CommonUtil.isBlank(start) || CommonUtil.isBlank(end)) {
            return 0;
        }

        return Days.daysBetween(getDateTimeStart(start), getDateTimeStart(end)).getDays();
    }
    public static int betweenDay(java.sql.Date start, java.sql.Date end) {
        if (CommonUtil.isBlank(start) || CommonUtil.isBlank(end)) {
            return 0;
        }

        return Days.daysBetween(getDateTimeStart(start), getDateTimeStart(end)).getDays();
    }

    /** 返回当前月份 */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /** 返回给定的 YYYY-MM 字符串中的月份 */
    public static int getMonthFromYYYY_MM(String yyyy_MM) {
        String monthString = yyyy_MM.substring(yyyy_MM.indexOf("-") + 1);
        return Integer.valueOf(monthString);
    }

    /** 返回给定的 java.sql.Date 中的月份 */
    public static int getMonthFromSqlDate(java.sql.Date date) {
        return date.getMonth() + 1;
    }

    /** 返回给定的 YYYY-MM-dd 字符串所代表的的 java.sql.Date，如果 date 不符合格式，会抛出 NullPointerException */
    public static java.sql.Date getSqlDateFrom(String date) {
        return new java.sql.Date(Objects.requireNonNull(DateKit.parse(date)).getTime());
    }

    public static java.sql.Date getSqlDateFrom(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 根据指定的日期，获取这个日期当月的第一天的日期。
     * 需要注意：时间和分钟、秒数没有被设置是不变的，例如返回像这样的 2019-06-01 14:33:05
     *
     * @param date date
     * @return date 代表的那个月的第一天
     */
    public static Date getInitialDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 根据指定的日期，获取这个日期当月的最后一天日期。
     * 需要注意：时间和分钟、秒数没有被设置是不变的，例如返回像这样的 2019-06-01 14:33:05
     *
     * @param date date
     * @return date 代表的那个月的最后一天日期。
     */
    public static Date getTailDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

}
