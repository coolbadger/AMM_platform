package com.amm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by csw on 2016/8/7 12:02.
 * Explain:
 */
public class DateUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) {
        return sdf.format(date);
    }
}
