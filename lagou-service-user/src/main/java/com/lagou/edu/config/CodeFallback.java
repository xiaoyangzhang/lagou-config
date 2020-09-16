package com.lagou.edu.config;

import com.lagou.edu.feign.CodeFeignClient;
import org.springframework.stereotype.Component;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 18:10
 **/
@Component
public class CodeFallback implements CodeFeignClient {
    @Override
    public int verifyCode(String email, String code) {
        System.out.println("验证码校验超时");
        return 2;
    }
}
