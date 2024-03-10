package jvm.threadpool.custom;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CustomerThreadPoolMain {

    public static void main(String[] args) throws InterruptedException {
        CustomerThreadPool pool = new CustomerThreadPool(1,101,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>(10));
        IntStream.range(1,4).forEach(e->{
            testThread(pool);
        });

        Thread.sleep(10000);
        System.out.println("执行完毕");
        System.out.println("main 线程数"+pool.getPoolSize()+"-----,活跃线程数"+pool.getActiveCount()+"icoreSize");
    }

    private static void testThread( CustomerThreadPool pool) {
        IntStream.range(1,2).forEach(e->{
            pool.execute(()->{
                //System.out.println("当前执行的线程名字："+Thread.currentThread().getName()+"-----活跃线程数:"+pool.getActiveCount());
            });
        });
        System.out.println("--------------------------------------");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main 线程数"+pool.getPoolSize()+"-----,活跃线程数"+pool.getActiveCount());
        IntStream.range(1,2).forEach(e->{
            pool.execute(()->{
              //  sleep(1000);
                //System.out.println("当前执行的线程名字："+Thread.currentThread().getName()+"-----活跃线程数:"+pool.getActiveCount());
            });
        });
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
