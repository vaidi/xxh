package com.erlong.spring.ioc;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.lang.Thread.sleep;

public class CurstomBeanDefinitionRegist implements ImportBeanDefinitionRegistrar {


    public static void main(String[] args) throws InterruptedException {

        while (true){
            sleep(20000);
        }

    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ExecutorService executorService =Executors.newCachedThreadPool();

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(ThreadPoolExecutor.class).getBeanDefinition();
        registry.registerBeanDefinition("person",beanDefinition);
    }


//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(PersonIoc.class).getBeanDefinition();
//        registry.registerBeanDefinition("person",beanDefinition);
//    }
}
