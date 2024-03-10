package jvm.juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger();

        CompletableFuture.supplyAsync(()->{
            double v = ThreadLocalRandom.current().nextDouble();
            return v;
        }).whenComplete((v,e)->{
            System.out.println("随机数："+v);
        }).exceptionally(e->{
            e.getStackTrace();
            return null;
                }
        );


    }
    public void test2(){

        int commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism();
        System.out.println("commonPoolParallelism:"+commonPoolParallelism);
        ThreadLocalRandom.current().nextDouble();
        CompletableFuture.supplyAsync(()->"oo");


    }



    public void test1() throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new MyCallable());
        Thread thread = new Thread(task,"t1");
        thread.start();
        task.get();
    }


    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("come in");
            return "hello word";
        }
    }


}
