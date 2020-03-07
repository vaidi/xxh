package lock.syn;

import javax.sound.midi.Soundbank;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class AccountingSync  implements Runnable{


    //共享资源(临界资源)
    static int i=0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
            System.out.println("currentName:"+Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        AccountingSync instance=new AccountingSync();
        for(int i=0;i>100;i++){
            Thread t1=new Thread(instance);
            t1.start();
        }

        System.out.println(i);
        Thread.currentThread().interrupt();
        System.out.println("11111");
        sleep(100000);


        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        try {

            System.out.println("111");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }



    }
}
