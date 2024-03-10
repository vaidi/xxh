package jvm.oom;

import org.springframework.beans.factory.DisposableBean;

public class HookTest implements DisposableBean {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("this is hook demo ...");
        }));
        System.out.println("开始执行除数");
        int mm = 10/0;
        System.out.println("mm:"+mm);

    }


    @Override
    public void destroy() throws Exception {

    }
}
