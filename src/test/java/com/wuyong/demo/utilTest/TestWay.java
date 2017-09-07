package com.wuyong.demo.utilTest;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 坚果
 * on 2017/9/7
 */

public class TestWay {

    @Test
    public void testZZ() {
        String email = "627522616@qq.com";
        Pattern pattern = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
        Matcher matcher = pattern.matcher(email);
        System.out.print(matcher.matches());
    }
}
