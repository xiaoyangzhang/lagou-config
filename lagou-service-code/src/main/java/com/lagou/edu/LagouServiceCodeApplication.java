package com.lagou.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lagou.edu.mapper")
public class LagouServiceCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LagouServiceCodeApplication.class, args);
	}

}
