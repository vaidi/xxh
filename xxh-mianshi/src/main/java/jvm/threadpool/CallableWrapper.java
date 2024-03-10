package jvm.threadpool;

import java.util.concurrent.Callable;

public class CallableWrapper implements Callable {

    private int id;
    public CallableWrapper(Integer id){
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(100);
        return "task id:"+id+"is completed";
    }
}
