package com.lagou.edu.model.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-14 23:21
 **/
@Data
@ToString
public class Resume {

    private Integer id;

    private String name;

    private String address;

    private String phone;
}
