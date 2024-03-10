package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreMain {

    public static void main(String[] args) {
        System.out.println(Math.round(-1.5));
        Semaphore semaphore = new Semaphore(1);
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("获得了线程量 在等待11");
                Thread.sleep(5000);
                semaphore.release();
                System.out.println("释放了 在等待11");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(()->{
            try {
                System.out.println("获得了线程量 在等待222");
                semaphore.tryAcquire(11, TimeUnit.SECONDS);
                System.out.println("获得了线程量 在等待3333");
                Thread.sleep(10000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


    }

}
