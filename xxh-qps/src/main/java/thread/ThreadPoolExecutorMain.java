package thread;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.threadpool.support.AbortPolicyWithReport;

import java.util.concurrent.*;

public class ThreadPoolExecutorMain {

    public static void main(String[] args) {
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
