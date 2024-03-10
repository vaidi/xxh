package jvm.threadpool;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.threadpool.support.AbortPolicyWithReport;

import java.util.concurrent.*;

public class ThreadPoolExecutorMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,101,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1));
        pool.shutdown();
        pool.shutdownNow();
        pool.isTerminated();
        int a = 1;
        for (int i = 1; i <= a; i++) {
            int j = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    if(j == 10){
                        throw new RuntimeException();
                    }
                    //获取线程名称
                    Thread thread = Thread.currentThread();
                    String name = thread.getName();
                    try {
                        //输出
                        int activeCount = pool.getActiveCount();
                        System.out.println("当前执行的线程名字："+Thread.currentThread().getName()+"任务："+j+"-----,线程名称:"+name+"-----活跃线程数:"+activeCount+"-----线程数"+pool.getPoolSize());
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                       throw new RuntimeException();
                    }

                }
            });
        }
        Thread.sleep(300);
        System.out.println("线程数"+pool.getPoolSize()+"-----,活跃线程数"+pool.getActiveCount());
        pool.execute(()->{
            System.out.println("当前执行的线程名字："+Thread.currentThread().getName()+"-----活跃线程数:"+pool.getActiveCount());
        });
        System.out.println("执行完毕");
    }



    public void test1(){
        RejectedExecutionHandler rejected1= new ThreadPoolExecutor.AbortPolicy();
        RejectedExecutionHandler rejected2= new ThreadPoolExecutor.CallerRunsPolicy();
        RejectedExecutionHandler rejected3= new ThreadPoolExecutor.DiscardPolicy ();
        RejectedExecutionHandler rejected4= new ThreadPoolExecutor.DiscardOldestPolicy();
        URL url = new URL("hession","aaa",63979);
        RejectedExecutionHandler rejected5= new AbortPolicyWithReport("11",url);
        BlockingQueue queue = new ArrayBlockingQueue<Thread>(1);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1,2,1, TimeUnit.SECONDS,queue,rejected5);
        for(int i=0;i<10;i++){
            threadPool.execute(()-> System.out.println("当前线程名字："+Thread.currentThread().getName()));
        }

    }
}
