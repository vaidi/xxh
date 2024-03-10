package jvm.threadlocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadLocalHash {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Integer> threadLocal = new ThreadLocal();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        IntStream.rangeClosed(1, 1000).forEach(e -> {
            executorService.execute(() -> {
                if (e % 2 == 0) {
                    Integer o = threadLocal.get();
                    System.out.println("threadLocal中的值:" + o);
                }
                threadLocal.set(e);
                Integer integer = threadLocal.get();
                System.out.println("设置的值:" + integer);
            });
        });

        Thread.sleep(1000);
        System.out.println("核心线程数的值:" + executorService.getCorePoolSize() + "活跃线程数:" + executorService.getCorePoolSize());
    }

    private static final int HASH_INCREMENT = 0x61c88647;
    public final int threadLocalHashCode = nextHashCode();
    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }
}
