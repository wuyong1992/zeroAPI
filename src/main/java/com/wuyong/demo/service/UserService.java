package com.wuyong.demo.service;

import com.wuyong.demo.common.ServerResponse;
import com.wuyong.demo.pojo.User;

/**
 * Created by 坚果
 * on 2017/9/6
 */
public interface UserService {

    ServerResponse login(User user);

    ServerResponse register(User user);
}
