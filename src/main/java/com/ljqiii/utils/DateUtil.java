package com.ljqiii.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    static long OneDay = 86400000;//one day milles

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-mm");

    public static SimpleDateFormat time = new SimpleDateFormat("yyyy-dd-mm");


    public static String TimeOrDateUtil(Date date) {
        if (System.currentTimeMillis() / 86400000 == date.getTime() / 86400000) {
            return DateUtil.simpleDateFormat.format(date);
        }
        return DateUtil.time.format(date);

    }
}
