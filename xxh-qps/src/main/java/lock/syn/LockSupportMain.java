package lock.syn;

import java.util.concurrent.locks.LockSupport;

public class LockSupportMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("init");
        Thread thread = new Thread(() -> {
            System.out.println("等待唤醒");
            LockSupport.park();
            System.out.println("唤醒成功");
        });
        thread.start();
        new Thread(()->{
            System.out.println("确认唤醒");
            LockSupport.unpark(thread);
        }).start();
        int i = Thread.activeCount();
        System.out.println(i);
        while (i != 2){
            System.out.println("还有在执行的线程，线程数:"+i);
            i = Thread.activeCount();
        }


        Thread thread1 = new Thread(() -> {
            System.out.println("before park");
            LockSupport.park();
            System.out.println("after park");
        });
        thread1.start();
        Thread.sleep(100);
        System.out.println("before interrupt");
        thread1.interrupt();
        System.out.println("after interrupt");
    }


}
