package com.beauty.config.utile;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ClassDesc
 */
public class DateUtil {

    /**
     * 获取当前月第一天
     *
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        calendar.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDays = sdf.format(calendar.getTime()) + " 00:00:00";
        return firstDays;
    }

    /**
     * 获取当前月最后一天
     *
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay = 0;
        //2月的平年瑞年天数
        if (month == 2) {
            // 这个api在计算2020年2月的过程中有问题
            lastDay = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
        } else {
            lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 设置日历中月份的最大天数
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDays = sdf.format(calendar.getTime()) + " 23:59:59";
        return lastDays;
    }


    public static void main(String[] args) {
        System.out.println(getLastDayOfMonth(2));
    }
}
