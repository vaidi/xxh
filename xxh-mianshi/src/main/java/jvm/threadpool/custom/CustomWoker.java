package jvm.threadpool.custom;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class CustomWoker extends AbstractQueuedSynchronizer implements Runnable {
    
    final Thread thread;
    Runnable firstTask;
    volatile long completedTasks;
    
    CustomWoker(Runnable runnable){
        setState(-1);
        this.firstTask = runnable;
        this.thread =  Executors.defaultThreadFactory().newThread(this::run);
        
    }


    @Override
    public void run() {
        runWorker(this);
    }

    private void runWorker(CustomWoker customWoker) {
    }
}
