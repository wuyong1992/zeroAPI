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
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),

    /** 3 账号错误 */
    USERNAME_MISMATCHING(2,"账号不存在"),

    /** 4 密码错误 */
    PASSWORD_MISMATCHING(2,"密码错误"),

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
