package com.erlong.jvm.metaspaceoom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 创建动态代理造成
 */
import java.lang.reflect.Method;

public class CglibOomMain {


    public static void main(String[] args) {
        int count = 0;
        while (true) {
            getOpenCoder();
            System.out.println("目前创建了" + (++count) + "个OpenCoder类的代理类");
        }
    }

    /**
     * 动态生成一个OpenCoder的子类并调用study方法
     */
    private static void getOpenCoder() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OpenCoder.class);
        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("study")) {
                    System.out.println("关注OpenCoder公众号");
                    return methodProxy.invokeSuper(o, objects);
                } else {
                    return methodProxy.invokeSuper(o, objects);
                }
            }
        });
        OpenCoder openCoder = (OpenCoder) enhancer.create();
        openCoder.study();
    }

    static class OpenCoder {
        OpenCoder() {
        }

        public void study() {
            System.out.println("study......");
        }
    }
}


