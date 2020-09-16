package com.lagou.edu.service.impl;

import com.lagou.edu.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 15:58
 **/
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Boolean sendEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xiaoyangzhang1990@163.com");
        message.setTo(email);
        message.setSubject("注册验证码");
        message.setText("欢迎注册天下第一网。您的验证码为:\n" + code);
        try {
            mailSender.send(message);
            System.out.println("simple mail had send。");
            return true;
        } catch (Exception e) {
            System.err.println("send mail error" + e);
            return false;
        }
    }
}
