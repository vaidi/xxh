package com.erlong;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy(exposeProxy=true)//这个用来解决aopProxy代理模式  AopContext来获取代理对象来执行方法
@MapperScan("com.erlong.mybatis.dao")
@EnableCaching
public class SpringBootLearn {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearn.class, args);
    }
}
