//package com.erlong.dao.demomapper;
//
//import org.springframework.beans.factory.config.BeanDefinitionHolder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
//import org.springframework.context.annotation.ScannedGenericBeanDefinition;
//import org.springframework.core.type.classreading.MetadataReader;
//
//import java.io.IOException;
//import java.util.Set;
//
//
//public class CustomMybatisClassPathBeanScanner extends ClassPathBeanDefinitionScanner {
//
//    public CustomMybatisClassPathBeanScanner(BeanDefinitionRegistry registry) {
//        super(registry);
//    }
//
//    @Override
//    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
//        return metadataReader.getClassMetadata().isInterface();
//    }
//
//
//    @Override
//    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
//        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
//        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
//         ScannedGenericBeanDefinition beanDefinition = (ScannedGenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
//            String beanClassName = beanDefinition.getBeanClassName();
//            beanDefinition.setBeanClass();
//            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue();
//
//        }
//
//
//        return super.doScan(basePackages);
//    }
//}
