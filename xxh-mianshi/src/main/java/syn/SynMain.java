package syn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynMain {


    public synchronized void testSyn(){
        System.out.println("进入这个syn方法");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        FIFOMutex fifoMutex = new FIFOMutex();




    }




}
