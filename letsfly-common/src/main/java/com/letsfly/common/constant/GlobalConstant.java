package com.letsfly.common.constant;

/**
 * 全局常量定义
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class GlobalConstant {
    
    /**
     * 私有化构造函数, 防止被外部实例化
     */
    private GlobalConstant() {}
    
    /** traceId key */
    public static final String ABEL_TRACE_ID = "abel_trace_id";
    
    /** logger traceId key */
    public static final String LOG_TRACE_ID = "traceid";
    
    /** 基础字符串 */
    public static String BASIC_STR = "0123456789abcdefghijklmnopqrstuvwxyz";
    
    /** 中国国家代码 */
    public static final int CHINA_CODE = 86;
    
    /** -9 */
    public static final int MINUS_NINE = -9;
    
    /** -8 */
    public static final int MINUS_EIGHT = -8;
    
    /** -7 */
    public static final int MINUS_SEVEN = -7;
    
    /** -6 */
    public static final int MINUS_SIX = -6;
    
    /** -5 */
    public static final int MINUS_FIVE = -5;
    
    /** -4 */
    public static final int MINUS_FOUR = -4;
    
    /** -3 */
    public static final int MINUS_THREE = -3;
    
    /** -2 */
    public static final int MINUS_TWO = -2;
    
    /** -1 */
    public static final int MINUS_ONE = -1;
    
    /** 0 */
    public static final int ZERO = 0;
    
    /** 1 */
    public static final int ONE = 1;
    
    /** 2 */
    public static final int TWO = 2;
    
    /** 3 */
    public static final int THREE = 3;
    
    /** 4 */
    public static final int FOUR = 4;
    
    /** 5 */
    public static final int FIVE = 5;
    
    /** 6 */
    public static final int SIX = 6;
    
    /** 7 */
    public static final int SEVEN = 7;
    
    /** 8 */
    public static final int EIGHT = 8;
    
    /** 9 */
    public static final int NINE = 9;
    
    /** null */
    public static final String NULL = "null";
    
    /** all */
    public static final String ALL = "all";
    
    /** success */
    public static final String SUCCESS = "success";
    
    /** failure */
    public static final String FAILURE = "failure";
    
    /** OK-字符串表示 */
    public static final String OK_STR = "OK";
    
    /** OK-数字表示 */
    public static final int OK_NUM = 1;
    
    /** YES-字符串表示 */
    public static final String YES_STR = "Y";
    
    /** YES-数字表示 */
    public static final int YES_NUM = 1;
    
    /** NO-字符串表示 */
    public static final String NO_STR = "N";
    
    /** NO-数字表示 */
    public static final int NO_NUM = 0;
    
    /** true-字符串表示 */
    public static final String TRUE_STR = "true";
    
    /** true-数字表示 */
    public static final int TRUE_NUM = 1;
    
    /** false-字符串表示 */
    public static final String FALSE_STR = "false";
    
    /** false-数字表示 */
    public static final int FALSE_NUM = 0;
    
    /** 2进制 */
    public static final int JZ_BINARY = 2;
    
    /** 8进制 */
    public static final int JZ_OCTAL = 8;
    
    /** 10进制 */
    public static final int JZ_DECIMAL = 10;
    
    /** 16进制 */
    public static final int JZ_HEX = 16;
    
    /** 60进制 */
    public static final int JZ_TIME = 60;
    
    /** 星号 */
    public static final String SYMBOL_ASTERISK = "*";
    
    /** 斜杠 */
    public static final String SYMBOL_SOLIDUS = "/";
    
    /** 反斜杠 */
    public static final String SYMBOL_REVERSE_SOLIDUS = "\\";
    
    /** 单引号 */
    public static final String SYMBOL_SINGLE_QUOTE = "'";
    
    /** 双引号 */
    public static final String SYMBOL_DOUBLE_QUOTE = "\"";
    
    /** 反引号 */
    public static final String SYMBOL_ESCAPE = "`";
    
    /** 逗号 */
    public static final String SYMBOL_COMMA = ",";
    
    /** 冒号 */
    public static final String SYMBOL_COLON = ":";
    
    /** 英文句号[点] */
    public static final String SYMBOL_DOT = ".";
    
    /** 转意后的[点] */
    public static final String SYMBOL_ESCAPED_DOT = "\\.";
    
    /** 问号 */
    public static final String SYMBOL_QUESTION = "?";
    
    /** 加号 */
    public static final String SYMBOL_PLUS = "+";
    
    /** 减号 */
    public static final String SYMBOL_MINUS = "-";
    
    /** 乘号 */
    public static final String SYMBOL_TIMES = "*";
    
    /** 除号 */
    public static final String SYMBOL_DIVISION = "/";
    
    /** 等号 */
    public static final String SYMBOL_EQUAL = "=";
    
    /** 短横线(连接符) */
    public static final String SYMBOL_HYPHEN = "-";
    
    /** 百分号 */
    public static final String SYMBOL_PERCENT = "%";
    
    /** 与 */
    public static final String SYMBOL_AND = "&";
    
    /** 或 */
    public static final String SYMBOL_OR = "|";
    
    /** 乘方、插入符号、插入符、脱字符号等 */
    public static final String SYMBOL_CARET = "^";
    
    /** 美元符号 */
    public static final String SYMBOL_DOLLAR = "$";
    
    /** 人民币符号 */
    public static final String SYMBOL_RMB = "￥";
    
    /** #号 */
    public static final String SYMBOL_NUMBER_SIGN = "#";
    
    /** @ 符号 */
    public static final String SYMBOL_AT = "@";
    
    /** 波浪号 */
    public static final String SYMBOL_TILDE = "~";
    
    /** 中文:圆括号-左 */
    public static final String SYMBOL_CH_PARENTHESES_LEFT = "（";
    
    /** 中文:圆括号-右 */
    public static final String SYMBOL_CH_PARENTHESES_RIGHT = "）";
    
    /** 中文:中括号-左 */
    public static final String SYMBOL_CH_BRACKET_LEFT = "【";
    
    /** 中文:中括号-右 */
    public static final String SYMBOL_CH_BRACKET_RIGHT = "】";
    
    /** 圆括号-左 */
    public static final String SYMBOL_PARENTHESES_LEFT = "(";
    
    /** 圆括号-右 */
    public static final String SYMBOL_PARENTHESES_RIGHT = ")";
    
    /** 中括号-左 */
    public static final String SYMBOL_BRACKET_LEFT = "[";
    
    /** 中括号-右 */
    public static final String SYMBOL_BRACKET_RIGHT = "]";
    
    /** 角括号-左 */
    public static final String SYMBOL_ANGLE_BRACKETS_LEFT = "<";
    
    /** 角括号-右 */
    public static final String SYMBOL_ANGLE_BRACKETS_RIGHT = ">";
    
    /** 花括号-左 */
    public static final String SYMBOL_BRACE_LEFT = "{";
    
    /** 花括号-右 */
    public static final String SYMBOL_BRACE_RIGHT = "}";
    
    /** 感叹号 */
    public static final String SYMBOL_EXCLAMATION_MARK = "!";
    
    /** 空字符串 */
    public static final String EMPTY_STR = "";
    
    /** 空格 */
    public static final String SPACE_STR = " ";
    
    /** UTF8 */
    public static final String CHARSET_UTF8 = "UTF-8";
    
    /** UTF16 */
    public static final String CHARSET_UTF16 = "UTF-16";
    
    /** GB2312 */
    public static final String CHARSET_GB2312 = "GB2312";
    
    /** GBK */
    public static final String CHARSET_GBK = "GBK";
    
    /** Big5 */
    public static final String CHARSET_BIG5 = "BIG5";
    
    /** ISO-8859-1 */
    public static final String CHARSET_ISO88591 = "ISO-8859-1";
    
    /** MD5 */
    public static final String ALGORITHM_MD5 = "MD5";
    
    /** AES加密 */
    public static final String ALGORITHM_AES = "AES";
    
    /** AES加密模式之ECB */
    public static final String ALGORITHM_AES_MODEL_ECB = "ECB";
    
    /** AES加密补码之PKCS5 */
    public static final String ALGORITHM_AES_PADDING_PKCS5 = "PKCS5Padding";
    
    /** RSA加密 */
    public static final String ALGORITHM_RSA = "RSA";
    
    /** RSA key size */
    public static final int ALGORITHM_RSA_KEY_SIZE1024 = 1024; 
    
    /** SHA-256 */
    public static final String ALGORITHM_SHA_256 = "SHA-256";
    
    /** SHA-384 */
    public static final String ALGORITHM_SHA_384 = "SHA-384";
    
    /** SHA-512 */
    public static final String ALGORITHM_SHA_512 = "SHA-512";
    
    /** RipeMD128 */
    public static final String ALGORITHM_RIPEMD_128 = "RipeMD128";
    
    /** RipeMD160 */
    public static final String ALGORITHM_RIPEMD_160 = "RipeMD160";
    
    /** RipeMD256 */
    public static final String ALGORITHM_RIPEMD_256 = "RipeMD256";
    
    /** RipeMD320 */
    public static final String ALGORITHM_RIPEMD_320 = "RipeMD320";
}
