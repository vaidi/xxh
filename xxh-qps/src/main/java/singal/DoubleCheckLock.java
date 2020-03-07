package singal;

public class DoubleCheckLock {
    public String getThisThread() {
        return thisThread;
    }
    private String thisThread;
    public static DoubleCheckLock instance;
    private DoubleCheckLock(String thisThread){
        this.thisThread = thisThread;
    }
    public static DoubleCheckLock getInstance(){
        //第一次检测
        if (instance==null){
            //同步
            synchronized (DoubleCheckLock.class){
                if (instance == null){
                    //多线程环境下可能会出现问题的地方
                    instance = new DoubleCheckLock(Thread.currentThread().getName());
                }
           }
        }
        return instance;
    }



}