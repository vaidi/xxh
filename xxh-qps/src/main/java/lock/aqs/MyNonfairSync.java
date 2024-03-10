package lock.aqs;

public class MyNonfairSync extends MySync {
    @Override
   final void lock() {
        if (compareAndSetState(0, 1))
            setExclusiveOwnerThread(Thread.currentThread());
        else
            acquire(1);
    }

    protected boolean tryAcquire(int arg) {
        return nonfairTryAcquire(arg);
    }
}
