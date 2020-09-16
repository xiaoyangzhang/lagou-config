package com.lagou.edu.controller;

import com.lagou.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-14 23:09
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/resume")
//    public Resume getById(@RequestParam("id") Integer id) {
//        return userService.getById(id);
//    }

    @PostMapping("/register/{email}/{code}/{password}")
    public Boolean register(@PathVariable("email") String email, @PathVariable("code") String code, @PathVariable("password") String password, HttpServletResponse response) {
        return userService.register(email, code, password, response);
    }

    @GetMapping("/isRegistered/{email}")
    public Boolean checkRegister(@PathVariable("email") String email) {
        return userService.checkRegister(email);
    }

    @GetMapping("/login/{email}/{password}")
    public String login(@PathVariable("email") String email, @PathVariable("password") String password, HttpServletResponse response) {
        return userService.login(email, password, response);
    }
//
//    @GetMapping("/create-token/{email}/{password}")
//    public String createToken(@PathVariable("email") String email, @PathVariable("password") String password) {
//        return userService.createToken(email, password);
//    }

    @GetMapping("/info/{token}")
    public String createToken(@PathVariable("token") String token) {
        return userService.getByToken(token);
    }
}
