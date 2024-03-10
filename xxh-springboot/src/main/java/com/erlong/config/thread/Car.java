package com.erlong.config.thread;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Car implements DisposableBean {

    @Autowired
    Person person;

    public Car(){
        System.out.println("car instance... person:"+person);
    }

    /**
     * 自定义的初始化方法
     */
    @PostConstruct
    public void init(){
        System.out.println("car ... init...person:"+person);
    }

    /**
     * 自定义的销毁方法
     */
    @PreDestroy
    public void detory(){
        System.out.println("car ... detory...person:"+person);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("car ... detory1...person:"+person);
    }

    @Override
    public String toString() {
        return "Car{" +
                "person=" + person +
                '}';
    }
}
