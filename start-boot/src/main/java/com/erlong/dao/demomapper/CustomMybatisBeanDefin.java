//package com.erlong.dao.demomapper;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * 动态注册bean
// */
//@Component
//public class CustomMybatisBeanDefin implements BeanDefinitionRegistryPostProcessor {
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//        CustomMybatisClassPathBeanScanner scanner = new CustomMybatisClassPathBeanScanner(registry);
//        scanner.addIncludeFilter((a,b)->true);
//        scanner.scan("com.erlong.custom.mapper");
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//
//    }
//}
