package com.lagou.edu.service;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 15:42
 **/
public interface CodeService {

    int verifyCode(String email, String code);

    Boolean sendCodeEmail(String email);
}
