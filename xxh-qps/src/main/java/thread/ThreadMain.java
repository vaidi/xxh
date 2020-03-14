package thread;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class ThreadMain {


    public static void main(String[] args) throws InterruptedException {
        User a,b;
        a=new User();
        b=a;
        System.out.println(a==b);
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        new Thread(() -> {
            lock.lock();
            try {
                while (atomicInteger.get() != 1) {
                    c2.await();
                }
                atomicInteger.getAndIncrement();
                System.out.println("currentName:" + Thread.currentThread().getName());
                c3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "B").start();
        new Thread(() -> {
            lock.lock();
            try {
                while (atomicInteger.get() != 2) {
                    c3.await();
                }
                atomicInteger.getAndIncrement();
                System.out.println("currentName:" + Thread.currentThread().getName());
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "C").start();

        new Thread(() -> {
            lock.lock();
            try {
                while (atomicInteger.get() != 0) {
                    c1.await();
                }
                atomicInteger.getAndIncrement();
                System.out.println("currentName:" + Thread.currentThread().getName());
                c2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();
    }


    public static void print(int[] array) {
        List<Integer> list = new ArrayList<>(10);
        for (int o : array) {
            int size = list.size();
            if (size < 10) {
                list.add(o);
                list.sort((a,b)->a-b);
            }
            if (size > 9 && o < list.get(9)) {
                list.remove(9);
                list.add(o);
                list.sort((a,b)->a-b);
            }
        }
        list.stream().forEach(System.out::println);
    }

    public void list(List<User> list){
          list.stream().filter(e->e.getAge()<=20).collect(Collectors.toList());
    }

    static class User{

        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


}
