package com.erlong.config;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeTaskMain {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService =new ThreadPoolExecutor(2, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }, 100, 1000);
        int activeCount = executorService.getActiveCount();
        System.out.println("active count:"+activeCount);


    }


    public void test(){
//        commandBatchService.getConnectionManager().newTimeout(new TimerTask() {
//            @Override
//            public void run(Timeout timeout) throws Exception {
//                ExpirationEntry ent = EXPIRATION_RENEWAL_MAP.get(getEntryName());
//                if (ent == null) {
//                    return;
//                }
//                Long threadId = ent.getFirstThreadId();
//                if (threadId == null) {
//                    return;
//                }
//
//                RFuture<Boolean> future = renewExpirationAsync(threadId);
//                future.onComplete((res, e) -> {
//                    if (e != null) {
//                        log.error("Can't update lock " + getRawName() + " expiration", e);
//                        EXPIRATION_RENEWAL_MAP.remove(getEntryName());
//                        return;
//                    }
//
//                    if (res) {
//                        // reschedule itself
//                        renewExpiration();
//                    } else {
//                        cancelExpirationRenewal(null);
//                    }
//                });
//            }
//        }, internalLockLeaseTime / 3, TimeUnit.MILLISECONDS);
    }
}
