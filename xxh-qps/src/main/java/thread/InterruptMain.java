package thread;

import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

public class InterruptMain {


    public static void main(String[] args) throws InterruptedException {
       Thread th = new Thread(()->{
           try {
               sleep(20000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           IntStream.rangeClosed(1,Integer.MAX_VALUE).forEach(System.out::print);
       });
       th.start();
       sleep(100);
       th.interrupt();



    }


}
