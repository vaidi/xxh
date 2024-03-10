package com.erlong.config.thread;

import com.alibaba.fastjson.JSONObject;

public class ThreadMain {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true){
                if(Thread.currentThread().isInterrupted()){
                 //   System.out.println("Error");
                }else {
                   // System.out.println("Success");
                }
            }
        });
        thread.start();
        System.out.println("interrupt:"+thread.isInterrupted()+",isLive:"+thread.isAlive());
        //ps:此代码没有做响应中断处理。
        thread.interrupt();
        int i = Thread.activeCount();
        System.out.println("当前活跃的线程数:"+i);
        StackTraceElement[] stackTrace = thread.getStackTrace();
        System.out.println(JSONObject.toJSON(stackTrace));
        System.out.println("interrupt:"+thread.isInterrupted()+",isLive:"+thread.isAlive());
    }

}
