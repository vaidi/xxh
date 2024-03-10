package jvm.threadpool;

import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
@Data
public class PriorityTask implements Runnable{

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

    private final Callable<String> inner;

    private final int priority;

    private final int taskSequence;

    private final ThreadPoolExecutor threadPoolExecutor;

    public PriorityTask(Callable<String> inner, Integer priority, ThreadPoolExecutor threadPoolExecutor){
        this.inner =  inner;
        this.priority = priority;
        this.taskSequence = ATOMIC_INTEGER.getAndIncrement();
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public void run() {
        try {
            String call = inner.call();
            System.out.println("active count:"+threadPoolExecutor.getActiveCount()+" queue size:"+threadPoolExecutor.getQueue().size()+   "priority:"+priority+" is called ,result is:"+call);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
