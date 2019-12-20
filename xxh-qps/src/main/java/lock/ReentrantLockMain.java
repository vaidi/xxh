package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/18 10:59
 * @Description: 打断点用来验证aqs 锁与 释放 锁不同的方法所走的结果
 */
public class ReentrantLockMain {

    /*

     */
    private static final Lock LOCK = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LOCK.lock();
                    try {
                        System.out.println("我是被锁住的内容" + j);
                        sleep(30);
                        System.out.println("用来验证可重入锁！！！" + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        LOCK.unlock();
                    }
                }
            }).start();
            // System.out.println("当前轮训完毕："+i);
        }


    }


}
