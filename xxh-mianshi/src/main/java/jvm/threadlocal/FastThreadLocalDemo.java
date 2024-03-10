package jvm.threadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * https://baijiahao.baidu.com/s?id=1762414973640986962&wfr=spider&for=pc
 * 1,高效查找 数组下标
 * 2，安全性能更高
 *
 */
public class FastThreadLocalDemo {

    private static final FastThreadLocal<String> FAST_THREAD_LOCAL = new FastThreadLocal();
    FastThreadLocalThread fastThreadLocalThread = new FastThreadLocalThread();
    static  ExecutorService executorService = Executors.newFixedThreadPool(1);
    static ThreadLocal<String> threadLocal = new ThreadLocal();
    public static void main(String[] args) {
        IntStream.rangeClosed(0,10).forEach(e->{
            executorService.submit(()->{
                String fastLocalValue = FAST_THREAD_LOCAL.get();
                String threadLocalValue = threadLocal.get();
                System.out.println("fastValue:"+fastLocalValue+",threadLocalValue:"+threadLocalValue);
                FAST_THREAD_LOCAL.set("e:"+e);
                threadLocal.set("e:"+e);
            });
        });


    }
}
