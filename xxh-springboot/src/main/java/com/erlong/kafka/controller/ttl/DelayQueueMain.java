package com.erlong.kafka.controller.ttl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueMain {
    // 可以用来执行定时任务
    static BlockingQueue<MyTask> task = new DelayQueue<>();
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1",now + 1001);
        MyTask t2 = new MyTask("t2",now + 2002);
        MyTask t3 = new MyTask("t3",now + 1503);
        MyTask t4 = new MyTask("t4",now + 3004);
        MyTask t5 = new MyTask("t5",now + 505);
        MyTask t6 = new MyTask("t6",now + 2506);

        task.put(t1);
        task.put(t2);
        task.put(t3);
        task.put(t4);
        task.put(t5);
        task.put(t6);

        for (int i = 0; i < 6; i++) {
            System.out.println(task.take());
        }

    }

    static class MyTask implements Delayed{

        String name;
        long runningTime;

        MyTask(String name,long runningTime){
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long currentDel = this.getDelay(TimeUnit.MILLISECONDS);
            long otherDel = o.getDelay(TimeUnit.MILLISECONDS);
            return Long.compare(currentDel,otherDel);
        }
        @Override
        public String toString() {
            return name + "-" + runningTime;
        }
    }
}
