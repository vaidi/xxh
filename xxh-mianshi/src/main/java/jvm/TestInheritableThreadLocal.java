package jvm;

public class TestInheritableThreadLocal implements Runnable{
    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("----主线程设置值为\"主线程\"");
        threadLocal.set("主线程");
        System.out.println("----主线程设置后获取值：" + threadLocal.get());
        Thread tt = new Thread(new TestInheritableThreadLocal());
        tt.start();
        tt.join();

        System.out.println("----主线程结束 主线程获取到的:"+threadLocal.get()+",活跃线程:"+Thread.activeCount());

    }


    @Override
    public void run() {
        System.out.println("----子线程设置值前获取：" + threadLocal.get());
        System.out.println("----新开线程设置值为\"子线程\"");
        threadLocal.set("子线程");
        System.out.println("----新开的线程设置值后获取：" + threadLocal.get());
    }
}
