package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/17 15:12
 * @Description:
 */
public class MutextDemo {


//    private static Mutex mutex = new Mutex();
//
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            final int j =i;
//            Thread thread = new Thread(() -> {
//                mutex.lock();
//                try {
//                    System.out.println("ThreadID:"+Thread.currentThread().getId()+"i:"+j);
//                    Thread.sleep(3000);
//                    System.out.println("ThreadID:"+Thread.currentThread().getId()+"睡觉结束"+"i:"+j);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    mutex.unlock();
//                }
//            });
//            thread.start();
//        }
//    }







    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }





}
