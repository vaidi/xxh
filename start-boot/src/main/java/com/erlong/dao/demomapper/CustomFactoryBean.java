//package com.erlong.dao.demomapper;
//
//import com.erlong.custom.mapper.UserMapper;
//import org.apache.ibatis.binding.MapperProxy;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.context.ApplicationContext;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Proxy;
//
//public class CustomFactoryBean implements FactoryBean {
//
//    private Class mapperClass;
//
//    private ApplicationContext context;
//
//    public CustomFactoryBean(Class mapperClass){
//        this.mapperClass = mapperClass;
//    }
//
//
//    @Override
//    public Object getObject() throws Exception {
//        System.out.println(mapperClass);
//        return (UserMapper)Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass},
//                new MapperProxy<UserMapper>(mapperClass,null));
//                new InvocationHandler() {
//
//
//        return null;
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return null;
//    }
//}
