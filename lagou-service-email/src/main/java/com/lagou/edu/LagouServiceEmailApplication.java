package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LagouServiceEmailApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(LagouServiceEmailApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
