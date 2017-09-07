package com.wuyong.demo.controller;

import com.wuyong.demo.common.ServerResponse;
import com.wuyong.demo.pojo.User;
import com.wuyong.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 坚果
 * on 2017/9/6
 * user
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public ServerResponse login(@RequestBody User user) {
        log.info("【用户登录信息】user:{}", user);

        return userService.login(user);
    }

    @PostMapping(value = "/register")
    public ServerResponse register(@RequestBody User user) {
        log.info("【用户注册信息】user:{}", user);
        return userService.register(user);
    }
}
