package com.lagou.edu;

import com.lagou.edu.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LagouServiceEmailApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void contextLoads() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    }

    @Test
    public void testEmail() {
        emailService.sendEmail("411075810@qq.com", "123456");
    }

    @Test
    public void testRandom() {
        System.out.println((int) (Math.random() * 1000000));
    }

}
