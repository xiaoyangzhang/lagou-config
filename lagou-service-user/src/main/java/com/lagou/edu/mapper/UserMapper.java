package com.lagou.edu.mapper;

import com.lagou.edu.model.entity.AuthToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-14 23:11
 **/
public interface UserMapper {


    @Insert("insert into lagou_token(email,token) values(#{email},#{token})")
    void insert(AuthToken authToken);

    @Select("select * from lagou_token where email=#{email} and token=#{token}")
    @ResultType(AuthToken.class)
    AuthToken select(@Param("email") String email, @Param("token") String token);

    @Select("select * from lagou_token where email=#{email}")
    @ResultType(AuthToken.class)
    AuthToken selectByEmail(@Param("email") String email);

    @Select("select * from lagou_token where token=#{token}")
    @ResultType(AuthToken.class)
    AuthToken selectByToken(@Param("token") String token);
}
