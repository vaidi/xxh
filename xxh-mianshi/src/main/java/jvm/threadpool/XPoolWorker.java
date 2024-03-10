package jvm.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class XPoolWorker extends AbstractQueuedSynchronizer implements Runnable {

    /**
     * This class will never be serialized, but we provide a
     * serialVersionUID to suppress a javac warning.
     */
    private static final long serialVersionUID = 6138294804551838833L;

    /** Thread this worker is running in.  Null if factory fails. */
    final Thread thread;
    /** Initial task to run.  Possibly null. */
    Runnable firstTask;
    /** Per-thread task counter */
    volatile long completedTasks;

    XPoolWorker(Runnable firstTask){
        setState(-1);
        this.firstTask = firstTask;
        this.thread =  Executors.defaultThreadFactory().newThread(this);
    }

    @Override
    public void run() {
        runWorker(this);
    }
    protected boolean tryRelease(int unused) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock()        { acquire(1); }
    public boolean tryLock()  { return tryAcquire(1); }
    public void unlock()      { release(1); }
    public boolean isLocked() { return isHeldExclusively(); }

    private void runWorker(XPoolWorker w) {
//        Thread wt = Thread.currentThread();
//        Runnable task = w.firstTask;
//        w.firstTask = null;
//        w.unlock(); // allow interrupts
//        boolean completedAbruptly = true;
//        try {
//            while (task != null || (task = getTask()) != null) {
//                w.lock();
//                // If pool is stopping, ensure thread is interrupted;
//                // if not, ensure thread is not interrupted.  This
//                // requires a recheck in second case to deal with
//                // shutdownNow race while clearing interrupt
//                if ((runStateAtLeast(ctl.get(), STOP) ||
//                        (Thread.interrupted() &&
//                                runStateAtLeast(ctl.get(), STOP))) &&
//                        !wt.isInterrupted())
//                    wt.interrupt();
//                try {
//                    beforeExecute(wt, task);
//                    Throwable thrown = null;
//                    try {
//                        task.run();
//                    } catch (RuntimeException x) {
//                        thrown = x; throw x;
//                    } catch (Error x) {
//                        thrown = x; throw x;
//                    } catch (Throwable x) {
//                        thrown = x; throw new Error(x);
//                    } finally {
//                        afterExecute(task, thrown);
//                    }
//                } finally {
//                    task = null;
//                    w.completedTasks++;
//                    w.unlock();
//                }
//            }
//            completedAbruptly = false;
//        } finally {
//            processWorkerExit(w, completedAbruptly);
//        }
    }
    private Runnable getTask() {
        return null;
    }
}
