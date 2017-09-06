package com.wuyong.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by 坚果
 * on 2017/9/6
 */
@Slf4j
public class MD5UtilTest {


    @Test
    public void MD5EncodeUtf8() throws Exception {
        log.info(MD5Util.MD5EncodeUtf8("like"));
    }

}