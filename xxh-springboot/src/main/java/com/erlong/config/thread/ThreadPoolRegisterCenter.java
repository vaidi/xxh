package com.erlong.config.thread;

import com.google.common.collect.Sets;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.ObjectUtils;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池优雅的关闭
 */
public class ThreadPoolRegisterCenter implements ApplicationRunner, DisposableBean {
    /**
     * @desc 线程池容器
     */
    private static final Set<ExecutorService> THREAD_POOL_CONTEXT  = Sets.newConcurrentHashSet();
    /**
     * @desc 添加线程池到容器中
     * @param executorService
     */
    public static void register(ExecutorService executorService) {
        THREAD_POOL_CONTEXT.add(executorService);
    }

    @Override
    public void destroy() throws Exception {
        //防止调用tomcat reload方法,导致的无法感知到关闭事件
        THREAD_POOL_CONTEXT.forEach(executorService -> {
            if (shutdownAndAwaitTermination(executorService)){
                THREAD_POOL_CONTEXT.remove(executorService);
            }
        });
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //设置钩子方法,并使用shutdownAndAwaitTermination方法进行关闭处理
        Runtime.getRuntime().addShutdownHook(new Thread(() -> THREAD_POOL_CONTEXT.forEach(executorService -> {
            if (!ObjectUtils.isEmpty(executorService)){
                shutdownAndAwaitTermination(executorService);
            }
        })));
    }
    /**
     * @desc 关闭方法
     * @param executorService
     */
    private boolean shutdownAndAwaitTermination(ExecutorService executorService) {
        return MoreExecutors.shutdownAndAwaitTermination(executorService, 10L, TimeUnit.SECONDS);
    }
}
