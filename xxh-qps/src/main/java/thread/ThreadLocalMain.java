package thread;

import java.util.UUID;

public class ThreadLocalMain {

    public static void main(String[] args) {

        for(int i=0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(LocalMain.local.get());
                }
            }).start();;
        }
    }


}
class LocalMain{

    public static ThreadLocal<String> local;

    static {
        local = new ThreadLocal<>();
        local.set(UUID.randomUUID().toString());
    }

}