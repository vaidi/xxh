package com.erlong.downhook;

import java.util.concurrent.TimeUnit;

public class DownHookMain implements Cloneable {

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute Hook.....");
            }
        }));
    }
    public static void main(String[] args) {
        new DownHookMain().start();
        System.out.println("The Application is doing something");
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
