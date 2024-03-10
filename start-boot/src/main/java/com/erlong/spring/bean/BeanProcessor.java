package com.erlong.spring.bean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.concurrent.atomic.AtomicInteger;

//@Component
public class BeanProcessor implements BeanPostProcessor, InitializingBean {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("前置处理方法,int:"+atomicInteger.incrementAndGet()+",bean name:"+toJson(bean));
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("后置处理方法,int:"+atomicInteger.incrementAndGet()+",bean name:"+ toJson(bean));
        return null;
    }

    private  String toJson(Object bean){
        try {
         return   JSONObject.toJSONString(bean);
        }catch (Exception e){

        }
        return "";

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("afterPropertiesSet处理方法,afterPropertiesSet,afterPropertiesSet");
    }
}
