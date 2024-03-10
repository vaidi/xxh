package jvm.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadLocalDemo {


    private static final ThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();
    private static final ThreadLocal<String> THREAD_LOCAL1 = new InheritableThreadLocal();
    private static final ThreadLocal<String> THREAD_LOCAL2 = new InheritableThreadLocal();
    private static final int HASH_INCREMENT = 0x61c88647;
    private static AtomicInteger atom = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        IntStream.rangeClosed(0, 15).forEach(e -> {
            int andAdd = atom.getAndAdd(HASH_INCREMENT);
            int slot = andAdd & (16 - 1);
            System.out.println("andAdd:e"+e+"--- &后结果" +slot);
        });

//        THREAD_LOCAL.set("ll");
//
//        firstStack();
//        System.gc();
//        TimeUnit.SECONDS.sleep(1);
//        Thread thread = Thread.currentThread();
//        System.out.println(thread);
//        THREAD_LOCAL.set("erlong");
//        System.gc();
//        thread = Thread.currentThread();
//        System.out.println(thread);
//        THREAD_LOCAL1.set("erlong");
//        thread = Thread.currentThread();
//        System.out.println(thread);
//        THREAD_LOCAL2.set("xxh");
//        thread = Thread.currentThread();
//        System.out.println(thread);
//
//        ThreadLocalHash localHash = new ThreadLocalHash();
//
//        IntStream.range(0, 100000).forEach(e -> {
//            System.out.println("hash:" + localHash.threadLocalHashCode);
//            System.out.println(atom.getAndAdd(HASH_INCREMENT & (16 - 1)));
//        });
//
//
//        THREAD_LOCAL.set("hello");
//        THREAD_LOCAL.set("erlong");
//        THREAD_LOCAL.set("erlong1");
//        THREAD_LOCAL.set("erlong2");
//        THREAD_LOCAL.set("erlong3");
//        System.out.println(THREAD_LOCAL.get());
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        IntStream.range(0, 1000).forEach(e -> {
//            executorService.execute(() -> {
//                String name = Thread.currentThread().getName();
//                THREAD_LOCAL.set(name);
//                THREAD_LOCAL.set("erlong");
//                String s = THREAD_LOCAL.get();
//                System.out.println("线程名称：" + s);
//            });
//        });
//        executorService.shutdown();

    }


    private static Local firstStack() {
        Local local = new Local();
        System.out.println("info:" + local.get());
        return local;
    }


    private static class Local {
        private ThreadLocal<String> local = ThreadLocal.withInitial(() -> "local");

        public String get() {
            return local.get();
        }

        public void set(String str) {
            local.set(str);
        }
    }

}
