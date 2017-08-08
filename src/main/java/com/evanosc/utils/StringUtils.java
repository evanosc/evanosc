package com.evanosc.utils;

/**
 * 字符串工具类
 * Created by evang on 2017/4/10.
 */
public class StringUtils {

    /**
     * 字符串是否为空，空的定义如下 1、为null <br>
     * 2、为""<br>
     *
     * @param str 被检测的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 字符串是否为非空白 空白的定义如下： <br>
     * 1、不为null <br>
     * 2、不为""<br>
     *
     * @param str 被检测的字符串
     * @return 是否为非空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 检测是否为空
     * @param string
     * @return
     */
    public static boolean isNull(String string) {
        return string == null || "".equals(string.trim());
    }

    /**
     * 字符串是否为空白 空白的定义如下： <br>
     * 1、为null <br>
     * 2、为不可见字符（如空格）<br>
     * 3、""<br>
     * @param str 被检测的字符串
     * @return 是否为空
     */
    public static boolean isBlank(String str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 格式化Object对象为字符串，如果为null则返回空白.
     *
     * @param str
     * @return str
     */
    public static String trim(Object str) {
        return (str == null) ? "" : str.toString().trim();
    }
}
