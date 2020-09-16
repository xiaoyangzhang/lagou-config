package com.lagou.edu.mapper;

import com.lagou.edu.model.entity.AuthCode;
import org.apache.ibatis.annotations.*;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 13:43
 **/
public interface AuthCodeMapper {

    @Insert("insert into lagou_auth_code(email,`code`,createtime,expiretime) values(#{email},#{code},now(),#{expiretime})")
    void insert(AuthCode authCode);

    @Select("select * from lagou_auth_code where email=#{email} order by createtime desc limit 1")
    @ResultType(AuthCode.class)
    AuthCode select(@Param("email") String email);
}
