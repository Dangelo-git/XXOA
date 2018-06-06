package com.dangelo.xxoa.uitl;



import com.blankj.utilcode.utils.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * ʱ��ת��������
 *
 * @author max.chengdu 2015��11��29��
 */
public class TimeManagement {
 /*
    * <pre>
    *                          HH:mm    15:44
            *                         h:mm a    3:44 下午
    *                        HH:mm z    15:44 CST
    *                        HH:mm Z    15:44 +0800
            *                     HH:mm zzzz    15:44 中国标准时间
    *                       HH:mm:ss    15:44:40
            *                     yyyy-MM-dd    2016-08-12
            *               yyyy-MM-dd HH:mm    2016-08-12 15:44
            *            yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
            *       yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
    *  EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
    *       yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
            *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
            *   yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
    *                         K:mm a    3:44 下午
    *               EEE, MMM d, ''yy    星期五, 八月 12, '16
            *          hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
            *   yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
    *     EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
            *                  yyMMddHHmmssZ    160812154440+0800
            *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
            * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
    * </pre>
            * 注意：SimpleDateFormat不是线程安全的，线程安全需用{@code ThreadLocal<SimpleDateFormat>}
    */
    /**
     *
     * @param millis
     * @param pattern
     * @return
     */
    public static String longToStringDate(long millis, String pattern) {
        return TimeUtils.millis2String(millis, pattern);
    }

    /**
     *
     * @param time
     * @param pattern
     * @return
     */
    public static Long stringToLongDate(String time, String pattern) {
        return TimeUtils.string2Millis(time, pattern);

    }
    /**
     * 处理时区
     * @param time
     * @return
     */
    public static long stringToLongDateForBJ(String time, String pattern) {
        if (time == null||time.length() < 14) {
            return 0;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); // 设置北京时区
            Date d = sdf.parse(time);
            return d.getTime();
        } catch (ParseException var3) {
            var3.printStackTrace();
            return -1L;
        }
    }



    public static String exchangeStringDate(String date) throws ParseException {
        if (date != null && date.length() > 10) {
            String result = date.substring(0, 10);
            return result;
        } else {
            return null;
        }

    }

    /**
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String exchangeStringTime(String date) throws ParseException {
        if (date != null && date.length() > 10) {
            String result = date.substring(10, date.length());
            return result;
        } else {
            return null;
        }
    }
    public static Long stringToLongDate1(String s) {
        Long time = null;
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sfd.parse(s);
            time = date.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;

    }

}
