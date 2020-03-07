package lock.syn;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {

    private AtomicReference<Thread> cas = new AtomicReference<Thread>();

    public void lock() {

        Thread current = Thread.currentThread();

        // 利用CAS，获取值不对则无限循环

        while (!cas.compareAndSet(null, current)) {

            // DO nothing

        }

    }

    public void unlock() {

        Thread current = Thread.currentThread();

        cas.compareAndSet(current, null);

    }
}
