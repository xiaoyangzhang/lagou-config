package com.lagou.edu.controller;

import com.lagou.edu.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 15:40
 **/
@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping("/create/{email}")
    public Boolean createCode(@PathVariable("email") @NotBlank String email) {
        return codeService.sendCodeEmail(email);
    }

    @GetMapping("/validate/{email}/{code}")
    public int verifyCode(@PathVariable("email") String email, @PathVariable("code") String code) {
        return codeService.verifyCode(email, code);
    }

}
