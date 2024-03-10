package thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StopThread {

    public static void main(String[] args) {

        ThreadPoolExecutor executorService =new ThreadPoolExecutor(2, 3,
                2L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        executorService.execute(()->{
            sleep();
            System.out.println("run threadName:"+Thread.currentThread().getName()); });
        executorService.execute(()->{
            sleep();
            System.out.println("run threadName:"+Thread.currentThread().getName()); });
        executorService.execute(()->{
            sleep();
            System.out.println("run threadName:"+Thread.currentThread().getName()); });
        executorService.execute(()->{
            sleep();
            System.out.println("run threadName:"+Thread.currentThread().getName()); });
        executorService.execute(()->{
            sleep();
            System.out.println("run threadName:"+Thread.currentThread().getName()); });

        AtomicInteger atomicInteger = new AtomicInteger();
        while (atomicInteger.getAndIncrement()>10000){

        }
        int activeCount = executorService.getActiveCount();
        System.out.println("active count:"+activeCount);
    }

    public static void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
