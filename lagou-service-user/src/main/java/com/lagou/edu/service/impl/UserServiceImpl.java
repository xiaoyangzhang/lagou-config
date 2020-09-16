package com.lagou.edu.service.impl;

import com.lagou.edu.mapper.ResumeMapper;
import com.lagou.edu.mapper.UserMapper;
import com.lagou.edu.model.entity.AuthToken;
import com.lagou.edu.model.entity.Resume;
import com.lagou.edu.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-14 23:10
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResumeMapper resumeMapper;

//    @Autowired
//    private CodeFeignClient codeFeignClient;

    private static Map<String, String> USERNAME_PASSWORD = new HashMap<>();

    @Override
    public Resume getById(Integer id) {
        return resumeMapper.getById(id);
    }

    @Override
    public Boolean register(String email, String code, String password, HttpServletResponse response) {
        USERNAME_PASSWORD.put(email, password);
//        int result = codeFeignClient.verifyCode(email, code);
//        if (result != 0) {
//            return false;
//        }
        String token = this.createToken(email, password);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setValue(token);
        response.addCookie(cookie);
        return true;
    }

    @Override
    public Boolean checkRegister(String email) {
        AuthToken authToken = userMapper.selectByEmail(email);
        if (authToken == null) {
            return false;
        }
        return true;
    }

    @Override
    public String login(String email, String password, HttpServletResponse response) {
        if (StringUtils.equals(password, USERNAME_PASSWORD.get(email))) {
            AuthToken authToken = userMapper.selectByEmail(email);
            if (authToken == null) {
                String token = this.createToken(email, password);
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                cookie.setMaxAge(Integer.MAX_VALUE);
                cookie.setValue(token);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("token", authToken.getToken());
                cookie.setPath("/");
                cookie.setMaxAge(Integer.MAX_VALUE);
                cookie.setHttpOnly(false);
                cookie.setValue(authToken.getToken());
                response.addCookie(cookie);
            }
            return email;
        }
        return null;
//        AuthToken authToken = userMapper.select(email, token);
//        if (authToken == null) {
//            return null;
//        }
    }

    private String createToken(String email, String password) {
        AuthToken authToken = new AuthToken();
        authToken.setEmail(email);
        authToken.setToken(UUID.randomUUID()
                               .toString());
        userMapper.insert(authToken);
        return authToken.getToken();
    }

    @Override
    public String getByToken(String token) {
        AuthToken authToken = userMapper.selectByToken(token);
        if (authToken == null) {
            return null;
        }
        return authToken.getEmail();
    }

}
