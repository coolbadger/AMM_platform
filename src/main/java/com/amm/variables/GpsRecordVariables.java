package com.amm.variables;

/**
 * Created by ThinkPad on 2017-03-16.
 */
public class GpsRecordVariables {

    private static int counts = 1;//队列中数量

    public static int getCounts() {
        return counts;
    }

    public static void setCounts(int counts) {
        GpsRecordVariables.counts = counts;
    }
}
