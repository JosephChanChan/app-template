package com.byb.framework.utils.stomp;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: chenzeting
 * Date:     2018/9/17
 * Description:
 */
public class DateUtils {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat ym = new SimpleDateFormat("yyyyMM");

    /**
     * 格式化日期 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatDate (Date date) {
        if (date == null) {
            return "";
        }
        return sdf.format(date);
    }

    /**
     * 每个季度结算
     * @return
     */
    public static String quarter () {
        int []mons = {3, 6, 9 ,12};
        DateTime today = new DateTime();
        for (int m : mons) {
            if (today.getMonthOfYear() <= m) {
                return today.withMonthOfYear(m).dayOfMonth().withMaximumValue().toString("yyyyMMdd");
            }
        }
        return null;
    }

    /**
     * yyyy-MM-dd
     * @return
     */
    public static String formatDate () {
        return ymd.format(new Date());
    }

    /**
     * 判断与今天是否是同一天
     * @return
     */
    public static boolean isSameDate (Date timeIdentify) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (timeIdentify != null) {
            String time = sdf.format(timeIdentify);
            String curDate = sdf.format(new Date());
            if (curDate.equals(time)) {
                return true;
            }
        }
        return false;
    }
}
