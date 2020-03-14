package thread;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class BlockingQueueMain {


    public static void main(String[] args) {
        HashMap map = new HashMap();

        BlockingQueue queu1e = new ArrayBlockingQueue(10);

        DelayQueue<Data> queue = new DelayQueue<>();

        Thread c1 = new Thread(new Consumer(queue), "consumer-1");
        Thread p1 = new Thread(new Producer(queue), "producer-1");
        c1.start();
        p1.start();


    }





}
