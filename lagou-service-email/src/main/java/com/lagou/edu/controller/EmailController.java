package com.lagou.edu.controller;

import com.lagou.edu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 15:56
 **/
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send/{email}/{code}")
    public Boolean sendEmail(@PathVariable("email") String email, @PathVariable("code") String code) {
        return emailService.sendEmail(email, code);
    }
}
