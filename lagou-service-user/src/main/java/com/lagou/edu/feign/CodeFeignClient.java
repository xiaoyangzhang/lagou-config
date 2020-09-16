package com.lagou.edu.feign;

import com.lagou.edu.config.CodeFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 15:36
 **/

@FeignClient(value = "lagou-service-code", fallback = CodeFallback.class)
public interface CodeFeignClient {

    @GetMapping("/code/validate/{email}/{code}")
    int verifyCode(@PathVariable("email") String email, @PathVariable("code") String code);

}
