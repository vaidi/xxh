package thread;

import java.util.concurrent.TimeUnit;

public class StopThread2 {

    public static void main(String[] args) throws InterruptedException {
        InterruptThread1 taskCase3 = new InterruptThread1();
        taskCase3.start();
        Thread.sleep(3000);
        taskCase3.stop();

    }

}
class InterruptThread1 {
    private Thread thread;

    public void start() {
        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("doSomething");
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    // 重置中断标志位为true
                    // thread.interrupt();
                    e.printStackTrace();
                    System.out.println("抛出中断异常，中断程序");
                }
            }
        });
        thread.start();
    }

    public void stop() {
        thread.interrupt();
        System.out.println("执行interrupt方法");
    }

}