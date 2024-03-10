package lock.syn;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConditionAwait {
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        AtomicInteger integer = new AtomicInteger(0);
        new Thread(() -> {
            lock.lock();
            try {
                while (integer.get()%3 !=2) {
                    String name = Thread.currentThread().getName();
                    System.out.println("当前线程："+name);
                    conditionC.await();
                }
                integer.incrementAndGet();
                IntStream.range(0, 10).forEach((int i) -> System.out.println("当前打印:" + i + ";" + Thread.currentThread().getName()));
                conditionA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "c").start();

        Thread thread = new Thread(() -> {
            lock.lock();
            try {
                while (integer.get()%3 !=0) {
                    conditionA.await();
                }
                integer.incrementAndGet();
                IntStream.range(0, 10).forEach((int i) -> System.out.println("当前打印:" + i + ";" + Thread.currentThread().getName()));
                conditionB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "a");
        thread.start();
       new Thread(() -> {
            lock.lock();
            try {
                while (integer.get()%3 !=1) {
                    conditionB.await();
                }
                integer.incrementAndGet();
                IntStream.range(0, 10).forEach((int i) -> System.out.println("当前打印:" + i + ";" + Thread.currentThread().getName()));
                conditionC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "b").start();

        System.out.println("唤醒成功");


    }


}
