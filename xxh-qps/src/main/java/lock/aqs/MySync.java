package lock.aqs;

public abstract class MySync extends MyAbstractQueuedSynchronizer {

    abstract void lock();

    final boolean nonfairTryAcquire(int acquires){
        final  Thread current =Thread.currentThread();
        int c =getState();
        if(c == 0){
            if(compareAndSetState(0,acquires)){
                setExclusiveOwnerThread(current);
                return true;
            }
        }else if(current == getExeclusiveOwnerThread()){
            int nextc = c + acquires;
            setState(nextc);
            return true;
        }
        return false;
    }

}
