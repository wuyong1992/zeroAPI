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

    /** 0 普通用户  1 管理员 */
    public interface UserRole {
        int ROLE_CUSTOMER = 0;
        int ROLE_ADMIN = 0;
    }

    /** 0 可用  1 隐藏  2 删除 */
    public interface ArticleStatus {
        int STATUS_USEABLE = 0;
        int STATUS_HIDE = 1;
        int STATUS_DELETE = 2;
    }

    //    todo 暂定60S,后期使用接口定义
    public interface TokenConfig {
        /** Token前缀 token_*/
        String TOKEN_PREFIX = "token_";
        /** Token有效期 单位：秒 */
        Integer TOKEN_EXPIRATION = 100;
        /** Token加密秘钥key */
        String TOKEN_KEY = "huyunyun";
        /** Token 签发人 */
        String TOKEN_ISSUER = "jianguo";
    }

    /** 校验规则 */
    public interface Validate {
        String VALIDATE_EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
    }

}
