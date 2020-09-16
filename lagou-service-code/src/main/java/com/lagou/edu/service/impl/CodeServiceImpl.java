package com.lagou.edu.service.impl;

import com.lagou.edu.feign.EmailFeignClient;
import com.lagou.edu.mapper.AuthCodeMapper;
import com.lagou.edu.model.entity.AuthCode;
import com.lagou.edu.service.CodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 15:43
 **/
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private AuthCodeMapper authCodeMapper;

    @Autowired
    private EmailFeignClient emailFeignClient;

    @Override
    public int verifyCode(String email, String code) {
        AuthCode authCode = authCodeMapper.select(email);
        if (authCode == null || (authCode != null && !StringUtils.equals(authCode.getCode(), code))) {
            return 1;
        } else if (authCode.getExpiretime()
                           .getTime() < System.currentTimeMillis()) {
            return 2;
        }
        return 0;
    }

    @Override
    public Boolean sendCodeEmail(String email) {
        AuthCode authCode = new AuthCode();
        authCode.setEmail(email);
        int code = (int) (Math.random() * 1000000);
        authCode.setCode(code + "");
        authCode.setExpiretime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        authCodeMapper.insert(authCode);
        Boolean result = emailFeignClient.sendEmail(email, code + "");
        return result;
    }
}
