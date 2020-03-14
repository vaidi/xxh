package singal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MainTest {


    public static void main(String[] args) throws InterruptedException {
        Integer num = new Integer(100);
        Integer integer = 100;
        System.out.println(num == integer);








//        final String mm =DoubleCheckLock.getInstance().thisThread;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            for(int i = 0;i<10;i++){
                new Thread(()->{
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DoubleCheckLock doubleCheckLock = DoubleCheckLock.getInstance();
                    System.out.println(doubleCheckLock.getThisThread() +"    "+Thread.currentThread().getName());

                }).start();
            }
            TimeUnit.MILLISECONDS.sleep(500);

            countDownLatch.countDown();

 //        System.out.println(
//                DoubleCheckLock.getInstance() ==  DoubleCheckLock.getInstance()
//        );
//        Map<String,Integer> map = new HashMap<>();
//        map.put("swap", DoubleCheckLock.getInstance().hashCode());
//        while (true){
//            new Thread(()->{
//                if(map.get("swap").intValue()!=DoubleCheckLock.getInstance().hashCode()){
//                    System.out.println("swap："+map.get("swap").intValue()
//                            +",hashCode:"+ DoubleCheckLock.getInstance().hashCode());
//                }
//            }).start();
//        }
    }
}
