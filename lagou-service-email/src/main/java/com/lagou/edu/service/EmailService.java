package com.lagou.edu.service;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 15:57
 **/
public interface EmailService {

    Boolean sendEmail(String email, String code);
}
