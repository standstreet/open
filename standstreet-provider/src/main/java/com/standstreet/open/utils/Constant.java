package com.standstreet.open.utils;

/**
 * 全局常量
 */
public class Constant {



    public final static int AUTHCODE_LENGTH = 6;// 验证码长度

    // 是否输出信息
    public final static String BLOWFISH_KEY = "L0GGq5SJVmV0CBz";// BlowFish数据库加密的密钥

    public final static String BLOWFISH_PASSWORD_KEY = "d";// BlowFish报文中密码的密钥

    public final static int USERNAME_MAX_LENGTH = 40;// 用户名的最大长度

    public final static int USERNAME_MIN_LENGTH = 3;// 用户名的最小长度

    public final static int PWD_MAX_LENGTH = 20;// 密码的最大长度

    public final static int PWD_MIN_LENGTH = 6;// 密码的最小长度

    public final static int NICKNAME_MAX_LENGTH = 20;// 昵称的最大长度

    public final static int NICKNAME_MIN_LENGTH = 2;// 昵称的最小长度

    public final static int REALNAME_MAX_LENGTH = 10;// 真实姓名最大长度

    public final static int REALNAME_MIN_LENGTH = 2;// 真实姓名最小长度

    public final static int BIRTHDAY_LENGTH = 10;// 生日的长度

    public final static int LOCATION_MAX_LENGTH = 255;// 地区最大长度(2015-3-30从20修改为255)

    public final static int LOCATION_MIN_LENGTH = 4;// 地区最小长度

    public final static String SEX_MALE = "1";// 性别男

    public final static String SEX_FEMAL = "0";// 性别女

    public final static int EMAIL_MAX_LENGTH = 40;// 电子邮件的最大长度

    public final static int EMAIL_MIN_LENGTH = 8;// 电子邮件的最小长度

    public final static int QQ_MAX_LENGTH = 12;// QQ号的最大长度

    public final static int QQ_MIN_LENGTH = 5;// QQ号的最小长度

    public final static int PHONENUMBER_MAX_LENGTH = 12;// 电话号码最大长度

    public final static int PHONENUMBER_MIN_LENGTH = 7;// 电话号码最小长度

    public final static int PERSONNALINFO_MAX_LENGTH = 30;// 个人信息的最大长度


    public static final long TOKEN_VALIDITY_TIME = 1000 * 60 * 60 * 24;// Token有效期
    // 毫秒*秒*分*天


    public final static String SIGN = "sign";
    public final static String APP = "app";
    public final static String APP_ID = "app_id";
    public final static String API_KEY = "apiKey";
    public final static String APP_KEY = "appKey";
    public final static String SECRET_KEY = "secretKey";
}