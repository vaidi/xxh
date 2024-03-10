package jvm.threadpool;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueDemo extends PriorityBlockingQueue {

    private int capacity;
    public PriorityQueueDemo(int initialCapacity, Comparator comparator){
        super(initialCapacity, comparator);
        this.capacity = initialCapacity;
    }

    @Override
    public boolean offer(Object o) {
        return this.size()>capacity?false:super.offer(o);
    }
}
