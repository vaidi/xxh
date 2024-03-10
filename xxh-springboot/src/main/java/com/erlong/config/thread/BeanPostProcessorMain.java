package com.erlong.config.thread;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPostProcessorMain implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean == null || !"car".equals(beanName)){
            return null;
        }
        System.out.println("postProcessBeforeInitialization beanName:"+beanName+",bean:"+ JSONObject.toJSONString(bean));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean == null || !"car".equals(beanName)){
            return null;
        }
        System.out.println("postProcessAfterInitialization beanName:"+beanName+",bean:"+ JSONObject.toJSONString(bean));
        return bean;
    }
}
