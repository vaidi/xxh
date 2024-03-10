package thread;

public class StopThread4 {


    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                // 数值尽可能的大
                for (int i = 0; i < 10000; i++) {
                    System.out.println( "i="  +(i + 1) );
                }
                System.out.println( "开始阻塞" );
                Thread.sleep( 200 );
                System.out.println( "阻塞完毕" );
            } catch (InterruptedException e) {
                System.out.println( "中断报错·······" );
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();


    }
}
