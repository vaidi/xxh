package lock;

public class ThreadHold {


    public static void main(String[] args) {
        Mutex mutex = new Mutex();
        boolean lockOk = Thread.holdsLock(mutex);
        System.out.println("lockOk:" + lockOk);
        synchronized (mutex) {
            lockOk = Thread.holdsLock(mutex);
            System.out.println("lockOk:" + lockOk);
        }
        System.out.println("lockOk:" + Thread.holdsLock(mutex));

    }
}
