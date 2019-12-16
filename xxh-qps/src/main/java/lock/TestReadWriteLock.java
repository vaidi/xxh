package lock;

import static java.lang.Thread.sleep;

public class TestReadWriteLock {


    public static void main(String[] args) throws InterruptedException {

       final ReadWriteLock readWriteLock = new ReadWriteLock();

        new Thread(new Runnable() {
            public void run() {
                try {
                    readWriteLock.lockRead();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        sleep(20);
        System.out.println("readCount:"+readWriteLock.getReadCount());
        new Thread(new Runnable() {
            public void run() {
                try {
                    readWriteLock.lockWrite();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        sleep(20);
        System.out.println("writeCount:"+readWriteLock.getWriteCount());






    }


}
