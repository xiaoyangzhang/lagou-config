package com.lagou.edu.service;

import com.lagou.edu.model.entity.Resume;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-14 23:10
 **/
public interface UserService {
    Resume getById(Integer id);

    Boolean register(String email, String code, String password, HttpServletResponse response);

    Boolean checkRegister(String email);

    String login(String email, String password, HttpServletResponse response);

//    String createToken(String email, String password);

    String getByToken(String token);
}
