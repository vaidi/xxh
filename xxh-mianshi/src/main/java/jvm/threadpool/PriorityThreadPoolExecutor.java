package jvm.threadpool;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 优先级线程池
 */
public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {


    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, buildWorkQueue());
    }

    private static BlockingQueue buildWorkQueue() {
        return new PriorityQueueDemo(30,new PriorityTaskComparator());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("最大线程数"+Runtime.getRuntime().availableProcessors()*2);

        PriorityThreadPoolExecutor threadPoolExecutor = new PriorityThreadPoolExecutor(2,Runtime.getRuntime().availableProcessors()*200,60,TimeUnit.SECONDS);
        IntStream.range(0,10).forEach(e->{
            threadPoolExecutor.execute(new PriorityTask(new CallableWrapper(e),10000,threadPoolExecutor));
        });
        IntStream.range(20,30).forEach(e->{
            threadPoolExecutor.execute(new PriorityTask(new CallableWrapper(e),100,threadPoolExecutor));
        });
        IntStream.range(40,50).forEach(e->{
            threadPoolExecutor.execute(new PriorityTask(new CallableWrapper(e),1,threadPoolExecutor));
        });
    }
}
