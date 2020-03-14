package thread;

import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.sleep;

public class CyclicBarrierDemo {

    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");
                sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        int threadNum = 5;
//        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
//            }
//        });
//        for(int i = 0; i < threadNum; i++) {
//            new TaskThread(barrier).start();
//        }
        CyclicBarrier barrier1  = new CyclicBarrier(5);
        for(int i=0;i<=10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        barrier1.await();
                        System.out.println("current name:"+Thread.currentThread().getName());
                        barrier1.await();
                        System.out.println("123466666556");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


        sleep(10000);


    }

}
