package com.lagou.edu.mapper;

import com.lagou.edu.model.entity.Resume;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-14 23:11
 **/
public interface ResumeMapper {


    @Select("select * from tb_resume where id=#{id}")
    Resume getById(@Param("id") Integer id);
}
