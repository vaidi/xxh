package com.erlong.spring.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * proxyBeanMethods 单例
 */
@Configuration(proxyBeanMethods = true)
public class AppConfig {

    @Bean
    public MyService myService() {
        System.out.println("初始化对象 MyService" );
        myBean();
        return new MyServiceImpl();
    }

    @Bean
    public MyBean myBean(){
        System.out.println("初始化对象 MyBean" );
        return new MyBean();
    }

    public static class MyBean{
        private String beanName;

    }

    public static class MyServiceImpl implements MyService {
        private int counter = 0;

        @Override
        public int incrementAndGetCounter() {
            return ++counter;
        }
    }

    public interface MyService {
        int incrementAndGetCounter();
    }

    public static void main(String[] args) {
        // 创建 ApplicationContext 并加载 AppConfig
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 从 ApplicationContext 中获取 MyService bean 的两个实例
        MyService service1 = context.getBean(MyService.class);
        MyService service2 = context.getBean(MyService.class);

        // 调用 incrementAndGetCounter 方法并打印结果
        System.out.println(service1.incrementAndGetCounter()); // 输出: 1
        System.out.println(service2.incrementAndGetCounter()); // 输出: 1

        // 如果 proxyBeanMethods = true，则 service1 和 service2 是同一个实例的代理，因此输出将会是 1 和 2
    }
}
