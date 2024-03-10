package com.erlong.jvm.metaspaceoom;

import groovy.lang.GroovyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class GroovyOom {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AtomicInteger atomicInteger = new AtomicInteger();
        while (true){
            // 创建GroovyClassLoader实例
            GroovyClassLoader classLoader = new GroovyClassLoader();
            // 定义Groovy脚本
            String script = "class MyScript {\n" +
                    "    static void printHello() {\n" +
                    "        println 'Hello from Groovy!'\n" +
                    "    }\n" +
                    "}\n";

            // 解析Groovy脚本并加载类
            Class<?> clazz = classLoader.parseClass(script);
            // 获取静态方法printHello()
            Method method = clazz.getDeclaredMethod("printHello");
            // 调用方法printHello()
            method.invoke(null);
            System.out.println("num:"+atomicInteger.getAndIncrement());

        }

    }

}

