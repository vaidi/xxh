package lock.syn;


public class SynMain {

    public static synchronized void getStaick() {
        System.out.println("获取getStaick");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取getStaick 等待结束");
    }

    public static synchronized void getStaickNoSleep() {
        System.out.println("获取getStaickNoSleep");
    }

    public void getSynClass(){
        synchronized (SynMain.class){
            System.out.println("获取getSynClass");
        }
    }

    public synchronized void getOne() {
        System.out.println("获取one");
    }


    public synchronized void getTwo() {
        System.out.println("获取getTwo");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取two 等待结束");
    }

    public void getTwoThis(){
        System.out.println(" init two this" );
        synchronized (this){
            System.out.println(" 进入了 two this" );
        }
        System.out.println("出去了 two this" );
    }


    public static void main(String[] args) {
        SynMain synMain = new SynMain();
        new Thread(() -> {
            synMain.getTwo();
        }).start();
        new Thread(() -> {
            synMain.getTwoThis();
        }).start();
//        new Thread(() -> {
//            SynMain.getStaick();
//        }).start();
//        new Thread(() -> {
//            SynMain.getStaickNoSleep();
//        }).start();
//        new Thread(() -> {
//            synMain.getSynClass();
//        }).start();
//        new Thread(() -> {
//            synMain.getTwo();
//        }).start();
//
//        new Thread(() -> {
//            synMain.getOne();
//        }).start();
//        int i = Thread.activeCount();
//        System.out.println("活跃线程数：" + i);

    }


}
