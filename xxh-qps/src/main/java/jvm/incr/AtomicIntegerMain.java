package jvm.incr;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerMain {

    public static void main(String[] args) {
        AtomicInteger mm = new AtomicInteger();
        mm.incrementAndGet();
        mm.getAndIncrement();
    }

}
