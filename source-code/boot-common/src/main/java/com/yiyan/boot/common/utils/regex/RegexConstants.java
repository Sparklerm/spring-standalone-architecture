package com.yiyan.boot.common.utils.regex;

/**
 * 正则表达式常量
 *
 * @author Alex Meng
 * @createDate 2023-04-25 22:03
 */
public class RegexConstants {
    private RegexConstants() {
    }

    /**
     * 数字
     */
    public static final String NUMBER = "^\\d+$";
    /**
     * 浮点数
     */
    public static final String DOUBLE = "^(-?\\d+)(\\.\\d+)?$";
    /**
     * 手机号码
     */
    public static final String MOBILE = "^1[3-9]\\d{9}$";
    /**
     * 邮箱
     */
    public static final String EMAIL = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";
    /**
     * 中国大陆身份证号码
     */
    public static final String ID_CARD_CN = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
    /**
     * URL
     */
    public static final String URL = "(https://|http://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";
    /**
     * IPv4地址
     */
    public static final String IPV4_ADDRESS = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    /**
     * IPv6地址
     */
    public static final String IPV6_ADDRESS = "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$";
    /**
     * 中文
     */
    public static final String CHINESE = "^[\u4e00-\u9fa5]+$";
    /**
     * 英文
     */
    public static final String ENGLISH = "^[A-Za-z]+$";

    /**
     * 日期格式
     */
    public static final String DATE = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    /**
     * 时间格式
     */
    public static final String TIME = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
    /**
     * 日期时间格式
     */
    public static final String DATE_TIME = "^\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}$";
    /**
     * 中国邮政编码
     */
    public static final String POST_CODE = "[1-9]\\d{5}(?!\\d)";
    /**
     * 中国车牌号码
     */
    public static final String PLATE_NUMBER = "[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}";
    /**
     * QQ
     */
    public static final String QQ = "[1-9][0-9]{4,}";

    /**
     * 18位身份证校验
     */
    public static final String ID_CARD = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";

    /**
     * 用户名
     */
    public static final String REGEXP_USERNAME = "^[a-zA-Z][a-zA-Z0-9_]{3,19}$";

    /**
     * 真实用户名
     */
    public static final String REGEXP_REAL_NAME = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";

    /**
     * 密码强度校验
     */
    public static final String SECURITY_CHECK = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~!@#$%^&*()_+=])[\\da-zA-Z~!@#$%^&*()_+=]{8,16}$";

    /**
     * 手机号
     */
    public static final String MOBILE_PHONE = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    /**
     * ip校验
     */
    public static final String REGEXP_IP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    /**
     * ipv6校验，同时支持ipv4及ipv4兼容地址和映像地址
     */
    public static final String REGEXP_IP_V6 = "^\\s*$|^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}||:))|(([0-9A-Fa-f]{1,4}:){5}((:[0-9A-Fa-f]{1,4}){2}|:))|(([0-9A-Fa-f]{1,4}:){4}((:[0-9A-Fa-f]{1,4}){3}|:))|(([0-9A-Fa-f]{1,4}:){3}((:[0-9A-Fa-f]{1,4}){4}|:))|(([0-9A-Fa-f]{1,4}:){2}((:[0-9A-Fa-f]{1,4}){5}|:))|(([0-9A-Fa-f]{1,4}:){1}((:[0-9A-Fa-f]{1,4}){6}|:))|(:(:[0-9A-Fa-f]{1,4}){1,7})|((::)?((25[0-5]|2[0-4]\\d|[01]?\\d\\d?).){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)))\\s*$";

    /**
     * 非必填ip校验
     */
    public static final String REGEXP_IP_NULL = "^$|^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    /**
     * ip段校验
     */
    public static final String REGEXP_IP_SEGMENT = "^(([0-9a-f]{1,4}:){7}[0-9a-f]{1,4})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(::([0-9a-f]{1,4}:){0,6}[0-9a-f]{1,4})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){1,7}:)(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^([0-9a-f]{1,4}:(:[0-9a-f]{1,4}){1,6})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){2}(:[0-9a-f]{1,4}){1,5})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){3}(:[0-9a-f]{1,4}){1,4})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){4}(:[0-9a-f]{1,4}){1,3})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){5}(:[0-9a-f]{1,4}){1,2})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){6}:[0-9a-f]{1,4})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^::FFFF:(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{2}|\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{2}|\\d)){3}(\\/(\\d|[12]\\d|3[0-2]))?$|^(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{2}|\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{2}|\\d)){3}(\\/(\\d|[12]\\d|3[0-2]))?$";

    /**
     * url校验(必须带http或https)
     */
    public static final String REGEXP_URL = "^((https?|ftp):\\/\\/)(((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|((([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+(\\/(([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|\\/|\\?)*)?$";

    /**
     * url校验(http和https可有可无）
     */
    public static final String REGEXP_URL_TWO = "^((https?|ftp):\\/\\/)?(((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|((([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+(\\/(([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|\\/|\\?)*)?$";

    /**
     * 域名校验
     */
    public static final String REGEXP_DOMAIN = "(?i)^([a-zA-Z0-9_-]+\\.)+[a-zA-Z0-9_-]+$";

    /**
     * 特殊字符校验
     */
    public static final String REGEXP_SPECIAL_CHAR = "^[^(\\&)(\\<)(\\>)(\\\\)]*$";

    /**
     * 手机
     */
    public static final String REGEXP_MOBILE = "^$|^1(3|4|5|7|8)[0-9]{9}$";

    /**
     * 固定电话
     */
    public static final String REGEXP_PHONE = "^((0\\d{2,3})-?)(\\d{7,8})(-?(\\d{0,4}))?$";

    /**
     * 非必填固定电话
     */
    public static final String REGEXP_TEL = "^$|^((0\\d{2,3})-?)(\\d{7,8})(-?(\\d{0,4}))?$";

    /**
     * 联系电话(手机or座机)
     */
    public static final String REGEXP_MOBILE_OR_PHONE = "^$|^(1(3|4|5|7|8)[0-9]{9})$|^((0\\d{2,3})-?)(\\d{7,8})(-?(\\d{0,4}))?$";

    /**
     * 端口
     */
    public static final String REGEXP_PORT = "^6(?:(553[0-5])|(55[0-2]\\d)|(5[0-4]\\d{2})|([0-4]\\d{3}))$|^([1-5]?\\d{1,4})$";

    /**
     * 传真
     */
    public static final String REGEXP_FAX = "^(\\d{3,4})?[-]?\\d{7,8}$";

    /**
     * 邮箱
     */
    public static final String REGEXP_EMAIL = "^$|(?i)^[a-z0-9]+[-._a-z0-9]*@([a-z0-9]+[-a-z0-9]*\\.){1,63}[a-z0-9]+$";

    /**
     * 经度
     */
    public static final String REGEXP_LONGITUDE = "^$|^(\\-|\\+)?((\\d|[1-9]\\d|1[0-7]\\d|0{1,3})\\.\\d{1,6}|(\\d|[1-9]\\d|1[0-7]\\d|0{1,3}|180)\\.0{1,6})$";

    /**
     * 纬度
     */
    public static final String REGEXP_LATITUDE = "^$|^[\\-|\\+]?([0-8]?\\d{1}\\.\\d{1,6}|90\\.0{1,6})$";

    /**
     * 正整数
     */
    public static final String REGEXP_POSITIVE_INTEGERS = "^\\d+$";

    /**
     * 中文
     */
    public static final String REGEXP_CHINESE = "^[\\u4e00-\\u9fa5]{0,}$";

    /**
     * 排除注入风险
     */
    public static final String REGEXP_SAFE = "^[^<>&\\\\]*$";

    /**
     * 时间格式正则
     */
    public static final String REGEXP_DATE_TIME = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";

}
