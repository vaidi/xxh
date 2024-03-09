package thread;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueMain {


    public static void main(String[] args) throws InterruptedException {
        HashMap map = new HashMap();
        new Thread(()->{
            System.out.println("开始执行线程");
            BlockingQueue queu1e = new ArrayBlockingQueue(10);
            Object take = null;
            try {
                take = queu1e.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("take:"+take);

        }).start();

        Thread.sleep(10000);
    }





}
