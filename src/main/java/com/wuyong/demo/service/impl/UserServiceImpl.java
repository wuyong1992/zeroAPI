package com.wuyong.demo.service.impl;

import com.wuyong.demo.common.Const;
import com.wuyong.demo.common.ResponseCode;
import com.wuyong.demo.common.ServerResponse;
import com.wuyong.demo.pojo.User;
import com.wuyong.demo.repository.UserRepository;
import com.wuyong.demo.service.UserService;
import com.wuyong.demo.util.MD5Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 坚果
 * on 2017/9/6
 */
@Service(value = "userService")
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StringRedisTemplate strRedisTemplate;

    /** 登录 */
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
        User userFind = userRepository.findByUsername(username);
        String MD5Pwd = userFind.getPassword();
        if (!isPassword(password, MD5Pwd)) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.PASSWORD_MISMATCHING.getCode(),
                    ResponseCode.PASSWORD_MISMATCHING.getDesc());
        }

        //4 jjwt创建token,设置有效期2小时
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(Const.CURRENT_USER, username);
        String token = Jwts.builder()
                .setAudience(username)                          //接受者
                .setIssuer(Const.TokenConfig.TOKEN_ISSUER)      //签发者
                .setSubject("LOGIN_SUCCESS")                    //主题
                .setIssuedAt(new Date())                        //签发时间
                .setClaims(claims)                              //存放的内容
                .setExpiration(new Date(System.currentTimeMillis()/1000 + Const.TokenConfig.TOKEN_EXPIRATION))   //有效期
                .signWith(SignatureAlgorithm.HS256, Const.TokenConfig.TOKEN_KEY)                            //签名方式  秘钥
                .compact();
        //5 token保存到redis,设置有效期2小时
        strRedisTemplate.opsForValue().set(
                Const.TokenConfig.TOKEN_PREFIX+userFind.getId(),
                token,
                Const.TokenConfig.TOKEN_EXPIRATION,
                TimeUnit.SECONDS);
        //6 返回登录成功信息和token
        log.info("【登录】成功，返回token:{}",token);
        return ServerResponse.createBySuccess(ResponseCode.SUCCESS.getDesc(), token);
    }


    /**
     * 注册服务
     * @param user
     * @return
     */
    public ServerResponse register(User user) {
        //1 提取数据并判断数据有效性
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        String username = StringUtils.trimToNull(user.getUsername());
        String password = StringUtils.trimToNull(user.getPassword());
        if (username == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if (isUsername(username)) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.USERNAME_EXIST.getCode(),
                    ResponseCode.USERNAME_EXIST.getDesc());
        }
        if (password == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        //TODO 暂时不校验Email
        /*if (!isEmail(user.getEmail())) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.EMAIL_MISMATCHING.getCode(),
                    ResponseCode.EMAIL_MISMATCHING.getDesc());
        }*/
        //2 密码MD5加密
        String MD5Pwd = MD5Util.MD5EncodeUtf8(password);
        user.setPassword(MD5Pwd);
        //3 保存到数据库
        userRepository.save(user);
        return ServerResponse.createBySuccess();
    }


    /**
     * 校验用户名是否存在
     * @param username  用户名
     * @return true 存在该用户  false 不存在该用户
     */
    private boolean isUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    /**
     * 密码是否正确
     * @param password 用户输入密码
     * @param MD5Pwd   数据库MD5加密过后的密码
     * @return true 正确  false 错误
     */
    private boolean isPassword(String password, String MD5Pwd) {
        password = MD5Util.MD5EncodeUtf8(password);
        return StringUtils.endsWith(password, MD5Pwd);
    }

    /**
     * 校验email是否有效
     * @param email email
     * @return true 有效  false 无效
     */
    private boolean isEmail(String email) {
        Boolean flag = true;
        Pattern pattern = Pattern.compile(Const.Validate.VALIDATE_EMAIL);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            flag = false;
        }
        if (userRepository.findByEmail(email) != null) {
            flag = false;
        }
        return flag;
    }

}
