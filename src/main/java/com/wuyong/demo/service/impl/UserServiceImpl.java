package com.wuyong.demo.service.impl;

import com.wuyong.demo.common.Const;
import com.wuyong.demo.common.ResponseCode;
import com.wuyong.demo.common.ServerResponse;
import com.wuyong.demo.pojo.User;
import com.wuyong.demo.repository.UserRepository;
import com.wuyong.demo.service.UserService;
import com.wuyong.demo.util.MD5Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 坚果
 * on 2017/9/6
 */
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public ServerResponse login(User user) {
        //1 提取变量
        String username = StringUtils.trimToNull(user.getUsername());
        String password = StringUtils.trimToNull(user.getPassword());
        //2 处理变量
        if (username == null || password == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc()
            );
        }
        //3 从数据库查找该用户并比对密码
        if (!isUsername(username)) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.USERNAME_MISMATCHING.getCode(),
                    ResponseCode.USERNAME_MISMATCHING.getDesc());
        }
        String MD5Pwd = userRepository.findByUsername(username).getPassword();
        if (!isPassword(password, MD5Pwd)) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.PASSWORD_MISMATCHING.getCode(),
                    ResponseCode.PASSWORD_MISMATCHING.getDesc());
        }

        //4 jjwt创建token,设置有效期2小时
        Map claims = new HashMap();
        claims.put(Const.CURRENT_USER, username);
        String token = Jwts.builder()
                .setSubject("LOGIN_SUCCESS")
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + Const.TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.ES256, Const.Token_Key)
                .compact();

        //5 token保存到redis,设置有效期2小时
        //6 返回登录成功信息和token
        return ServerResponse.createBySuccess(ResponseCode.SUCCESS.getDesc(), token);
    }


    //该用户名是否存在
    private boolean isUsername(String username) {
        return userRepository.findByUsername(username) == null;
    }

    /**
     * 密码是否正确
     * @param password 用户输入密码
     * @param MD5Pwd   数据库MD5加密过后的密码
     * @return
     */
    private boolean isPassword(String password, String MD5Pwd) {
        password = MD5Util.MD5EncodeUtf8(password);
        return StringUtils.endsWith(password, MD5Pwd);
    }

}
