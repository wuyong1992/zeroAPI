package com.wuyong.demo.utilTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Created by 坚果
 * on 2017/9/6
 */

@Slf4j
public class lambdaTest {

    @Test
    public void lambdaLoop() {
        String[] str = {"a", "b", "c", "d"};
        for (String aStr : str) {
            log.info(aStr);
        }
        log.info("for循环结束");
        Arrays.stream(str).forEach(log::info);
    }

}
