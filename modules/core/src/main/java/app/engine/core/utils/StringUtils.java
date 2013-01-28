package app.engine.core.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * <p>
 * 该类封装了字符串类型数据的常用方法，该类中的方法均为静态方法。
 * </p>
 *
 * @author shifenghu
 */
public class StringUtils {
    /**
     * 字符串连接时的分隔符
     * <p>
     * 该分隔符用于{@link #toString(java.util.Collection)} 和 {@link #toString(java.util.Collection, String)}方法。
     * </p>
     */
    public static final String DEFAULT_SEPARATOR = ",";

    /**
     * 检查当前字符串是否为空
     * <p>
     * 如果字符串为null，或者长度为0，都被归为空。
     * </p>
     *
     * @param id 要检查的字符串
     * @return 返回结果，true表示不为空，false表示为空
     */
    public static boolean isEmpty(Object id) {
        if (id == null || id.toString().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 检查当前字符串是否为空
     * <p>
     * 如果字符串为null，或者调用{@link #java.lang.String.trim()}后长度为0，都被归为空。
     * </p>
     *
     * @param str 要检查的字符串
     * @return 检查结果，true 为空，false不为空
     */
    public static boolean isTrimEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 字符串数组转化为 字符串
     *
     * @param array
     * @return
     */
    public static String arrayToString(Object[] array) {
        if (array == null) return "";
        StringBuffer result = new StringBuffer();
        for (Object item : array) {
            result.append(item).append(",");
        }
        if (result.length() > 0) {
            return result.substring(0, result.length() - 1);
        }
        return "";
    }

    /**
     * 将对象解析成字符串
     *
     * @param value 要解析的对象
     * @return 解析的字符串
     */
    public static String toJSON(Object value) {
        //检查value是否为空
        if (value == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat format = new SimpleDateFormat(DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS);
        //设置日期格式
        mapper.writer(format);
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 判断当前字符串是否是由数字组成
     *
     * @param str 要检查的字符串
     * @return 结果
     */
    public static boolean isDigit(String str) {
        //检查是否为空
        if (StringUtils.isTrimEmpty(str)) {
            return false;
        }
        return Pattern.matches("^\\d+$", str);
    }

    /**
     * 判断当前字符串是否表示数字区间
     *
     * @param str 要检查的字符串
     * @return 结果
     */
    public static boolean isDigitRange(String str) {
        //检查是否为空
        if (StringUtils.isTrimEmpty(str)) {
            return false;
        }
        return Pattern.matches("^\\d+-\\d+$", str);
    }

    /**
     * 替换字符串中的字符,该方法用于velocity层，只替换第一次匹配
     *
     * @param str   被替换的原始字符串
     * @param regex 替换的字符
     * @param value 替换的值
     * @return 替换结果
     */
    public static String replace(String str, String regex, String value) {
        //检查是否为空
        if (StringUtils.isTrimEmpty(str)) {
            return str;
        }
        return str.replace(regex, value);
    }

    /**
     * 替换字符串中的字符,该方法用于velocity层，替换所有匹配
     *
     * @param str   被替换的原始字符串
     * @param regex 替换的字符
     * @param value 替换的值
     * @return 替换结果
     */
    public static String replaceAll(String str, String regex, String value) {
        //检查是否为空
        if (StringUtils.isTrimEmpty(str)) {
            return str;
        }
        return str.replaceAll(regex, value);
    }

    /**
     * 提换html的部分特殊字符
     * <p>
     * 只替换了&、<和>符号
     * </p>
     *
     * @param str 要替换的字符串
     * @return 替换结果
     */
    public static String formatHtml(String str) {
        //检查是否为空
        if (StringUtils.isTrimEmpty(str)) {
            return str;
        }
        //替换特殊字符串
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        return str;
    }

    /**
     * 替换HTML的全部特殊字符
     * <p>
     * 替换了&、<、>、"和空格
     * </p>
     *
     * @param str 要替换的字符串
     * @return 替换的结果
     */
    public static String formatAllHtml(String str) {
        //检查是否为空
        if (StringUtils.isTrimEmpty(str)) {
            return str;
        }
        //替换特殊字符串
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll(" ", "&nbsp;");
        return str;
    }

    /**
     * 将过长字符串进行截取，并在末尾追加描述符，如...
     *
     * @param str       要截取的字符串
     * @param maxLength 最大长度
     * @param replace   追加的字符串，如果是null，则默认为...
     * @return 截取结果
     */
    public static String cut(String str, int maxLength, String replace) {
        //检查是否为空
        if (StringUtils.isTrimEmpty(str)) {
            return str;
        }
        //检查replace是否存在
        if (replace == null) {
            replace = "...";
        }
        //检查长度
        if (str.length() + replace.length() <= maxLength || maxLength < 1 || replace.length() > maxLength) {
            return str;
        }
        //开始截取
        return str.substring(0, maxLength - replace.length()) + replace;
    }

    /**
     * 将string 集合拼接成字符串，使用{@value #DEFAULT_SEPARATOR}分隔
     *
     * @param list 要处理的集合
     * @return 处理结果
     */
    public static String toString(Collection<?> list) {
        return toString(list, null);
    }

    /**
     * 将string 集合拼接成字符串，使用特定字符分隔
     *
     * @param list      要处理的集合
     * @param separator 分隔符，如果为null，则默认使用{@value #DEFAULT_SEPARATOR}
     * @return 处理结果
     */
    public static String toString(Collection<?> list, String separator) {
        if (separator == null) {
            separator = DEFAULT_SEPARATOR;
        }
        //检查list是否存在
        if (list == null) {
            return null;
        }
        StringBuffer rs = new StringBuffer();
        Iterator<?> it = list.iterator();
        Object next = null;
        while (it.hasNext()) {
            next = it.next();
            if (next == null) {
                continue;
            }
            rs.append(next.toString());
            //如果有下一个值，则添加分隔符
            if (it.hasNext()) {
                rs.append(separator);
            }
        }
        return rs.toString();
    }

    /**
     * 检查输入的字符串是否为查询条件 有[ 标识
     *
     * @param str
     * @return
     */
    public static boolean isQueryCondition(String str) {
        //检查是否为空
        if (StringUtils.isTrimEmpty(str)) {
            return false;
        }
        //检查是否为查询条件
        if (str.indexOf("[") != -1) {
            return true;
        }

        return false;
    }

    /**
     * @param ojb
     * @return Integer
     * @throws
     * @Title strToInt
     * @Description 将字符串数字转换成数字
     * @author liuqin
     * @date 2012-12-6 下午1:14:50
     */
    public static Integer strToInt(Object ojb) {
        if (isEmpty(ojb)) return 0;
        try {
            return Integer.valueOf(ojb.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * @param content
     * @return String
     * @Title filterImgTag
     * @Description 过滤字符串中的图片标签
     * @author chenjingxue
     * @date 2012-12-17
     */
    public static String filterImgTag(String content) {
        return content.replaceAll("<img.*?>", "");
    }


    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    /**
     * 删除html 标签
     *
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        return htmlStr.trim(); // 返回文本字符串
    }

}
