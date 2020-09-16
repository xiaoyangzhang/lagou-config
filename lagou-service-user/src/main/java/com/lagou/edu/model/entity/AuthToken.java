package com.lagou.edu.model.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 17:54
 **/
@Data
@ToString
public class AuthToken {

    private Integer id;

    private String email;

    private String token;
}
