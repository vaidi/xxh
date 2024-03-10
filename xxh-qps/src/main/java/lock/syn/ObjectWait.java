package lock.syn;

public class ObjectWait {


    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            synchronized (obj) {
                System.out.println("线程a进入了锁 wait前");
                try {
                    obj.wait();
                    System.out.println("线程a被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a");
        thread.start();

        new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("睡了100ms等待唤醒");
                obj.notify();
                System.out.println("线程b唤醒成功");
            }
        }, "b").start();

        System.out.println("唤醒成功");
    }

}
