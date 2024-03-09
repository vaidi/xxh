package thread;

import java.util.UUID;

public class ThreadLocalMain {

    public static void main(String[] args) {
        System.out.println(LocalMain.local.get()+"----");
        for(int i=0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread thread = Thread.currentThread();
                    System.out.println("来到这里 ，线程名称："+thread.getName() );
                    System.out.println(LocalMain.local.get()+"----"+thread.getName() );
                }
            },"线程:"+i).start();;
        }
    }


}
class LocalMain{

    public static ThreadLocal<String> local = new ThreadLocal<>();

    static {
        System.out.println("要创建对象threadlocal,线程名称："+ Thread.currentThread().getName());
        local.set(UUID.randomUUID().toString());
        System.out.println("创建对象完毕");
    }

}