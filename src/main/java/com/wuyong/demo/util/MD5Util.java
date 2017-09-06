package com.wuyong.demo.util;

import com.wuyong.demo.common.Const;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * Created by 坚果
 * on 2017/9/6
 */
@Slf4j
public class MD5Util {

    /**
     * 返回大写MD5
     *
     * @param origin      需要加密的原字符串
     * @param charsetname 加密的字符集 没有输入的话默认处理
     * @return
     */
    private static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception exception) {
            log.error("【MD5加密】加密异常：{}", exception);
            //exception.printStackTrace();
        }
        //返回大写
        return resultString.toUpperCase();
    }

    /**
     * MD5加盐值加密
     */
    public static String MD5EncodeUtf8(String origin) {
        //MD5加盐值
        origin = origin + Const.MD5_SALT;
        return MD5Encode(origin, "utf-8");
    }

    //十六进制
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    //单个字节转换成十六进制字符
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    //字节数组转换成十六进制字符串
    private static String byteArrayToHexString(byte b[]) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }
}
