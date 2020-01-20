package com.erlong;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Hello world!
 */
@EnableAspectJAutoProxy(exposeProxy = true)//动态代理方式事务
@SpringBootApplication
@EnableAsync
public class SpringBootLearn {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearn.class, args);
    }
}

