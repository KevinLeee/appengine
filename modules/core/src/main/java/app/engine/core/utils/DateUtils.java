package app.engine.core.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * <p>
 * 日期类型的工具类，该类均为静态方法，直接调用
 * </p>
 *
 * @author shifenghu
 */
public class DateUtils {
    //
    // private static final SimpleDateFormat sdf = new SimpleDateFormat(
    // "yyyy-MM-dd HH:mm:ss");
    // private static final SimpleDateFormat sdfSimple = new SimpleDateFormat(
    // "yyyy-MM-dd");

    /**
     * 日期格式化模式（日期类型数据）
     * <p>
     * 日期格式化模式，使用此模式将日期格式化为“2012-10-08”，一般用于日期类型数据格式化
     * </p>
     */
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 日期格式化模式（时间类型数据）
     * <p>
     * 日期格式化模式，使用此模式将日期格式化为“2012-10-08 10:10:08”，一般用于时间类型数据格式化
     * </p>
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式化模式（时间类型数据），精确到分
     * <p>
     * 日期格式化模式，使用此模式将日期格式化为“2012-10-08 10:10”，一般用于时间类型数据格式化
     * </p>
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String PATTERN_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    /**
     * 其他时间值转换为毫秒单位：秒
     */
    public static final long UNIT_SECOND_TIME = 1000;
    /**
     * 其他时间值转换为毫秒单位：分钟
     */
    public static final long UNIT_MINUS_TIME = 60 * UNIT_SECOND_TIME;
    /**
     * 其他时间值转换为毫秒单位：小时
     */
    public static final long UNIT_HOUR_TIME = 60 * UNIT_MINUS_TIME;
    /**
     * 其他时间值转换为毫秒单位：天
     */
    public static final long UNIT_DAY_TIME = 24 * UNIT_HOUR_TIME;
    /**
     * 时间单位名称：秒
     */
    public static final String UNIT_SECOND_NAME = "秒";
    /**
     * 时间单位名称：分钟
     */
    public static final String UNIT_MINUS_NAME = "分钟";
    /**
     * 时间单位名称：小时
     */
    public static final String UNIT_HOUR_NAME = "小时";
    /**
     * 时间单位名称：天
     */
    public static final String UNIT_DAY_NAME = "天";

    /**
     * 根据特定模式，将字符串型日期对象解析成Date对象
     *
     * @param source  要解析的字符串
     * @param pattern 解析模式，默认为{@value #PATTERN_YYYY_MM_DD_HH_MM_SS}
     * @return 解析结果
     * @throws java.text.ParseException 如果要解析的字符串格式不匹配，则抛出此异常
     */
    public static Date parse(final String source, String pattern)
            throws ParseException {
        // 检查value是否为空
        if (source == null || StringUtils.isTrimEmpty(source)) {
            return null;
        }
        // 如果pattern为空
        if (pattern == null) {
            // 设置pattern为PATTERN_YYY_MM_DD_HH_MM_SS
            pattern = PATTERN_YYYY_MM_DD_HH_MM_SS;
        }
        // 初始化一个format类
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        // 开始解析
        return format.parse(source);
    }

    /**
     * 将日期对象根据特定格式格式化成字符串
     *
     * @param source  要格式化的日期对象
     * @param pattern 格式化模式，默认为{@value #PATTERN_YYYY_MM_DD_HH_MM_SS}
     * @return 格式化后的字符串
     */
    public static String format(final Date source, String pattern) {
        // 检查value是否为空
        if (source == null) {
            return null;
        }
        // 如果pattern为空
        if (pattern == null) {
            // 设置pattern为PATTERN_YYYY_MM_DD_HH_MM_SS
            pattern = PATTERN_YYYY_MM_DD_HH_MM_SS;
        }
        // 初始化一个format类
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(source);
    }

    /**
     * 格式化到天
     *
     * @param source
     * @return
     */
    public static String formatToDay(final Date source) {

        return format(source, PATTERN_YYYY_MM_DD);
    }

    /**
     * 格式化日期到秒
     *
     * @param source
     * @return
     */
    public static String formatToSecond(final Date source) {
        return format(source, PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化日期到分钟
     *
     * @param source
     * @return
     */
    public static String formatToMinute(final Date source) {
        return format(source, PATTERN_YYYY_MM_DD_HH_MM);
    }

    /**
     * 返回服务器时间
     *
     * @return
     */
    public static Date getServerDate() {
        return new Date();
    }

    /**
     * 判断特定时间距离当前服务器时间的毫秒数
     *
     * @param date 要比较的时间
     * @return 比较结果，如果当前日期迟于要比较的时间则返回值小于零，反之大于零
     */
    private static long millisecondsBeforeNow(final Date source) {
        // 检查日期对象是否存在
        if (source == null) {
            return 0;
        }
        return new Date().getTime() - source.getTime();
    }

    /**
     * 此方法用于前台项目显示当前相差的时间
     * <p>
     * 该方法用于前台项目中使用，一般用于提问时间等，规则如下（xx为时间，数字）：<br/>
     * （当前时间与发布时间）未超过一分钟：则显示秒，即xx秒前；<br/>
     * （当前时间与发布时间）未超过一小时：则显示到分钟，即xx分钟前；<br/>
     * （当前时间与发布时间）未超过一天（24小时）：则显示到小时，即xx小时前；<br/>
     * （当前时间与发布时间）超过一天（24小时）：则显示实际发布时间，年-月-日 时:分:秒；
     * </p>
     *
     * @param source 要格式化的日期
     * @return 格式化结果
     */
    public static String formatToFront(final Date source) {
        // 获取相差的毫秒数
        long d = millisecondsBeforeNow(source);
        // 之前描述名称
        final String BERFORE = "前";
        // 一分钟以内
        if (d < UNIT_MINUS_TIME) {
            return (d / UNIT_SECOND_TIME) + UNIT_SECOND_NAME + BERFORE;
        }
        // 一个小时以内
        else if (d < UNIT_HOUR_TIME) {
            return (d / UNIT_MINUS_TIME) + UNIT_MINUS_NAME + BERFORE;
        }
        // 未超过一天
        else if (d < UNIT_DAY_TIME) {
            return (d / UNIT_HOUR_TIME) + UNIT_HOUR_NAME + BERFORE;
        }
        // 超过一天
        else {
            return format(source, PATTERN_YYYY_MM_DD_HH_MM_SS);
        }
    }

    /**
     * 将秒转成分钟和秒字符串
     *
     * @param second 秒钟
     * @return xxm xxs
     */
    public static String formatToFront(Integer second) {
        long s = second % 60;
        int t = (int) (second / 60);
        return t + "m " + s + "s";
    }

    /**
     * 特定日期和服务器日期相差的天数
     *
     * @param source 要比较的日期
     * @return 天数，之后大于零，之前小于零
     */
    public static int daysBeforeNow(final Date source) {
        // 如果要比较的source不存在
        if (source == null) {
            return 0;
        }
        Calendar now = Calendar.getInstance();
        Calendar old = Calendar.getInstance();
        // 设置时分秒为0:0:0
        old.setTime(source);
        old.set(Calendar.HOUR_OF_DAY, 0);
        old.set(Calendar.MINUTE, 0);
        old.set(Calendar.SECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        // 获取相差描述
        long l = old.getTimeInMillis() - now.getTimeInMillis();
        // 转换程天数
        return BigDecimal
                .valueOf(l)
                .divide(BigDecimal.valueOf(UNIT_DAY_TIME), RoundingMode.HALF_UP)
                .intValue();
    }

    /**
     * 获取在某天的基础上增加或减少N天
     *
     * @param date 某天
     * @param num  加上或减少的天数，正数为加，负数为减。
     * @return
     * @author ZhangYunHe
     */
    public static Date getDateAfterDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);
        return c.getTime();
    }

    /**
     * 获取今天是今年中的第几天
     *
     * @return
     * @author ZhangYunHe
     * @date 2013-1-8
     */
    public static int getTodayOfYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取指定日期是今年中的第几天
     *
     * @param dateStr 日期字符串 格式：yyyy-MM-dd
     * @return 指定日期是今年中的第几天
     * @author ZhangYunHe
     * @note 请不要输入2012-02-31、2012-13-32之类的非法日期字符串
     */
    public static int getDayOfYear(String dateStr) {
        Calendar calendar = initCalendarByDateString(dateStr);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 使用yyyy-MM-dd格式的日期字符串实例化一个Calendar对象
     *
     * @param dateStr 日期字符串 格式：yyyy-MM-dd
     * @return
     * @author ZhangYunHe
     * @date 2013-1-8
     */
    public static Calendar initCalendarByDateString(String dateStr) {
        if (null != dateStr && !"".equals(dateStr.trim())
                && dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String[] date = dateStr.split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);// 月份需要减1，Calendar从0开始算1月
            calendar.set(Calendar.DATE, Integer.parseInt(date[2]));
            return calendar;
        } else {
            throw new IllegalArgumentException(
                    "传入的日期格式字符串非法！接受的日期格式字符串为：yyyy-MM-dd");
        }
    }

    /**
     * 获取今年总共有多少天
     *
     * @return
     * @author ZhangYunHe
     * @date 2013-1-8
     */
    public static int getDaysOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 在当前时间的基础上添加特定时间
     * <p>
     * 根据type类型来区分添加的时间单位，type等同于Calendar.XXXX 如：小时Calendar.HOUR_OF_DAY。<br/>
     * 暂只支持，DAY_OF_MONTH,HOUR_OF_DAY，MINUTE
     * </p>
     *
     * @param type  类型
     * @param value 要新增的值（如果是负数则是减少的值）
     * @return 处理后的时间
     */
    public static Date addByCurrent(int type, int value) {
        return addByDate(new Date(), type, value);
    }

    /**
     * 在特定时间的基础上添加特定时间
     * <p>
     * 根据type类型来区分添加的时间单位，type等同于Calendar.XXXX 如：小时Calendar.HOUR_OF_DAY。<br/>
     * 暂只支持，DAY_OF_MONTH,HOUR_OF_DAY，MINUTE
     * </p>
     *
     * @param date  特定时间
     * @param type  类型
     * @param value 要新增的值（如果是负数则是减少的值）
     * @return 处理后的时间
     */
    public static Date addByDate(Date date, int type, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        switch (type) {
            case Calendar.DAY_OF_MONTH:// 月份中的天数
                now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH)
                        + value);
                break;
            case Calendar.HOUR_OF_DAY:// 小时
                now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) + value);
                break;
            case Calendar.MINUTE:// 分钟
                now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + value);
                break;
        }
        return now.getTime();
    }

    public static String convertToChinese(BigDecimal object) {
        if (object != null) {
            BigInteger second = object.toBigInteger();
            final BigInteger SECOND_OF_HOUR = BigInteger.valueOf(3600);
            final BigInteger SECOND_OF_MINUTE = BigInteger.valueOf(60);
            BigInteger h, m, s;
            h = m = s = BigInteger.ZERO;
            BigInteger temp = second.mod(SECOND_OF_HOUR);
            if (second.max(SECOND_OF_HOUR).equals(second)) {
                h = second.divide(SECOND_OF_HOUR);
                if (!temp.equals(BigInteger.ZERO)) {
                    if (temp.max(SECOND_OF_MINUTE).equals(temp)) {
                        m = temp.divide(SECOND_OF_MINUTE);
                        if (!temp.mod(SECOND_OF_MINUTE).equals(BigInteger.ZERO)) {
                            s = temp.mod(SECOND_OF_MINUTE);
                        }
                    } else {
                        s = temp;
                    }
                }
            } else {
                m = second.divide(SECOND_OF_MINUTE);
                if (!second.mod(SECOND_OF_MINUTE).equals(BigInteger.ZERO)) {
                    s = second.mod(SECOND_OF_MINUTE);
                }
            }
            return h + "时 " + m + "分 " + s + "秒";
        }
        return "0时 0分 0秒";
    }

}
