package com.lagou.edu.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-14 23:18
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DbConfig {

    private String driverClassName;

    private String username;

    private String password;

    private String url;

    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(password);
        druidDataSource.setUsername(username);
        druidDataSource.setDriverClassName(driverClassName);
        return druidDataSource;
    }
}
