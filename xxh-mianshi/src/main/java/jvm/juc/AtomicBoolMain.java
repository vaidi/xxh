package jvm.juc;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBoolMain {

    public static void main(String[] args) {

        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.lazySet(Boolean.FALSE);
        System.out.println(atomicBoolean.get());

    }

}
