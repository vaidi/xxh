package com.erlong.spring.ioc;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Import(CurstomBeanDefinitionRegist.class)
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        ExecutorService bean = applicationContext.getBean(ThreadPoolExecutor.class);
        bean.execute(()-> System.out.println("hello"));
        Thread.yield();
        System.out.println(JSONObject.toJSON(bean));
    }


    private static  void test1(){

    }

}
