package com.lagou.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 20:06
 **/

@FeignClient("lagou-service-user")
public interface UserFeignClient {

    @GetMapping("/user/info/{token}")
    String getInfoByToken(@PathVariable("token") String token);
}
