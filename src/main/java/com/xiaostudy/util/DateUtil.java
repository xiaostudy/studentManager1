package com.xiaostudy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 */
public class DateUtil {

    /**
     * 判断两个日期是否相同，精确到天。只有参数不为null且日期不同返回true
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareToByDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return compareToByDay(sdf.format(date1), sdf.format(date2));
    }

    /**
     * 判断两个日期是否相同，精确到天。只有参数不为null且日期不同返回true
     * @param strDate1
     * @param strDate2
     * @return
     */
    public static boolean compareToByDay(String strDate1, String strDate2) {
        if (strDate1 == null || strDate1.trim().length() <= 0 || strDate2 == null || strDate2.trim().length() <= 0) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date fomatDate1 = null;
        Date fomatDate2 = null;
        try {
            fomatDate1 = sdf.parse(strDate1);
            fomatDate2 = sdf.parse(strDate2);
            if(fomatDate1 == null || fomatDate2 == null) {
                return false;
            }
            if (fomatDate2.compareTo(fomatDate1) != 0) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
