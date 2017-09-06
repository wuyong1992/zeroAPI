package com.wuyong.demo.common;

/**
 * Created by 坚果
 * on 2017/9/6
 * 常量类
 */
public class Const {

    /** 当前用户 */
    public static final String CURRENT_USER = "currentUser";
    /** MD5盐值 */
    public static final String MD5_SALT = "geelysdafaqj23ou89ZXcj@#$@#$#@KJdjklj;D../dSF.,";
    /** Token有效期 单位：秒*/
//    todo 暂定60S,后期使用接口定义
    public static final Integer TOKEN_EXPIRATION = 60;
    /** Token key */
    public static final String Token_Key = "huyunyun";


    /** 0 普通用户  1 管理员 */
    public interface Role {
        int ROLE_CUSTOMER = 0;
        int ROLE_ADMIN = 0;
    }

    /** 0 可用  1 隐藏  2 删除 */
    public interface ArticleStatus {
        int STATUS_USEABLE = 0;
        int STATUS_HIDE = 1;
        int STATUS_DELETE = 2;
    }

}
