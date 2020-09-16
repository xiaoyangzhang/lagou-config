package com.lagou.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 15:44
 **/
@FeignClient("lagou-service-email")
public interface EmailFeignClient {

    @GetMapping("/email/send/{email}/{code}")
    Boolean sendEmail(@PathVariable("email") String email, @PathVariable("code") String code);
}
