package thread;

import java.util.concurrent.DelayQueue;

public class Consumer implements Runnable {
    private final DelayQueue<Data> queue;
    public Consumer(DelayQueue<Data> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Data data = queue.take();
                System.out.println(Thread.currentThread().getName() + ": take " + data);

                Thread.yield();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
