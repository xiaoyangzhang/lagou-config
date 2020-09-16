package com.lagou.edu.model.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 17:41
 **/
@Data
@ToString
public class AuthCode {

    private Integer id;

    private String email;

    private String code;

    private Date createtime;

    private Date expiretime;
}
