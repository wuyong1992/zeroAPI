package com.wuyong.demo.common;

/**
 * Created by 坚果
 * on 2017/9/6
 * 返回的状态码
 */
public enum ResponseCode {

    /** 0 成功 */
    SUCCESS(0, "SUCCESS"),

    /** 1 错误 */
    ERROR(1, "ERROR"),

    /** 2 参数错误 */
    ILLEGAL_ARGUMENT(2,"参数错误"),

    /** 3 账号不存在 */
    USERNAME_MISMATCHING(3,"账号不存在"),

    /** 4 账号不存在 */
    USERNAME_EXIST(4,"账号已存在"),

    /** 5 密码错误 */
    PASSWORD_MISMATCHING(5,"密码错误"),

    /** 6 email格式错误或者已存在 */
    E_MISMATCHING(6,"email格式错误或者已存在"),

    /** 10 需要登录 */
    NEED_LOGIN(10, "NEED_LOGIN");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
