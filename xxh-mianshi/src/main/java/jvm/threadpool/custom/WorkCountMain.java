package jvm.threadpool.custom;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class WorkCountMain {


    public static void main(String[] args) {
        Runnable runnable = ()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        WorkCount workCount = new WorkCount(1,3);
        int threadNum = WorkCount.workerCountOf(workCount.ctl.get());
        workCount.execute(runnable);
        boolean b = WorkCount.runStateAtLeast(workCount.ctl.get(), WorkCount.STOP);
        System.out.println("b:"+b);

        workCount.getTask();
        System.out.println("threadNum:"+threadNum);

        IntStream.rangeClosed(0,1000).forEach(e->{
            workCount.execute(runnable);
        });

    }




    static class WorkCount{
        public void execute(Runnable command) {
            int c = ctl.get();
            int workCount = workerCountOf(c);
            if (workCount < corePoolSize) {
                if (addWorker(command, true))
                    return;
                c = ctl.get();
            }
            boolean running = isRunning(c);
            if (running && workQueue.offer(command)) {
                int recheck = ctl.get();
                boolean running1 = isRunning(recheck);
                int workerCountOf = workerCountOf(recheck);
                if (! running1 && remove(command)){
                    System.out.println("走到拒绝方法啦");
                    //  reject(command);
                }else  if (workerCountOf == 0){
                    addWorker(null, false);
                }
            } else if (!addWorker(command, false)){
                System.out.println("走到拒绝");
                // reject(command);
            }

        }

        private Runnable getTask() {
            boolean timedOut = false; // Did the last poll() time out?

            for (;;) {
                int c = ctl.get();
                int rs = runStateOf(c);

                // Check if queue empty only if necessary.
                if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
                    decrementWorkerCount();
                    return null;
                }

                int wc = workerCountOf(c);

                // Are workers subject to culling?
                boolean timed = false || wc > corePoolSize;
                if ((wc > maximumPoolSize || (timed && timedOut))
                        && (wc > 1 || workQueue.isEmpty())) {
                    if (compareAndDecrementWorkerCount(c))
                        return null;
                    continue;
                }

                try {
                    Runnable r = timed ? workQueue.poll(2000, TimeUnit.NANOSECONDS) :
                            workQueue.take();
                    if (r != null){
                        return r;
                    }
                    timedOut = true;
                } catch (InterruptedException retry) {
                    timedOut = false;
                }
            }
        }


        public boolean remove(Runnable task) {
            boolean removed = workQueue.remove(task);
            return removed;
        }
        private volatile int corePoolSize;
        private volatile int maximumPoolSize;
        WorkCount(int corePoolSize,int maximumPoolSize){
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
        }

        private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        private static final int COUNT_BITS = Integer.SIZE - 3;
        private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

        // runState is stored in the high-order bits
        private static final int RUNNING    = -1 << COUNT_BITS;
        private static final int SHUTDOWN   =  0 << COUNT_BITS;
        public static final int STOP       =  1 << COUNT_BITS;
        private static final int TIDYING    =  2 << COUNT_BITS;
        private static final int TERMINATED =  3 << COUNT_BITS;

        // Packing and unpacking ctl
        private static int runStateOf(int c)     { return c & ~CAPACITY; }
        private static int workerCountOf(int c)  { return c & CAPACITY; }
        private static int ctlOf(int rs, int wc) { return rs | wc; }

        /*
         * Bit field accessors that don't require unpacking ctl.
         * These depend on the bit layout and on workerCount being never negative.
         */

        private static boolean runStateLessThan(int c, int s) {
            return c < s;
        }

        private static boolean runStateAtLeast(int c, int s) {
            return c >= s;
        }

        private static boolean isRunning(int c) {
            return c < SHUTDOWN;
        }

        /**
         * Attempts to CAS-increment the workerCount field of ctl.
         */
        private boolean compareAndIncrementWorkerCount(int expect) {
            return ctl.compareAndSet(expect, expect + 1);
        }

        /**
         * Attempts to CAS-decrement the workerCount field of ctl.
         */
        private boolean compareAndDecrementWorkerCount(int expect) {
            return ctl.compareAndSet(expect, expect - 1);
        }

        /**
         * Decrements the workerCount field of ctl. This is called only on
         * abrupt termination of a thread (see processWorkerExit). Other
         * decrements are performed within getTask.
         */
        private void decrementWorkerCount() {
            do {} while (! compareAndDecrementWorkerCount(ctl.get()));
        }

        private final BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1);


        private final HashSet<CustomWoker> workers = new HashSet<CustomWoker>();

        private int largestPoolSize;

        private boolean addWorker(Runnable firstTask, boolean core) {
            retry:
            for (;;) {
                int c = ctl.get();
                int rs = runStateOf(c);

                // Check if queue empty only if necessary.
                if (rs >= SHUTDOWN &&
                        ! (rs == SHUTDOWN &&
                                firstTask == null &&
                                ! workQueue.isEmpty()))
                    return false;

                for (;;) {
                    int wc = workerCountOf(c);
                    if (wc >= CAPACITY || wc >= (core ? corePoolSize : maximumPoolSize)){
                        return false;
                    }
                    if (compareAndIncrementWorkerCount(c)){
                        int wcOf = workerCountOf(ctl.get());
                        System.out.println("wcOf:"+wcOf);
                        break retry;
                    }
                    c = ctl.get();  // Re-read ctl
                    if (runStateOf(c) != rs)
                        continue retry;
                    // else CAS failed due to workerCount change; retry inner loop
                }
            }

            boolean workerStarted = false;
            boolean workerAdded = false;
            CustomWoker w = null;
            try {
                w = new CustomWoker(firstTask);
                final Thread t = w.thread;
                if (t != null) {
                    final ReentrantLock mainLock = new ReentrantLock();
                    mainLock.lock();
                    try {
                        // Recheck while holding lock.
                        // Back out on ThreadFactory failure or if
                        // shut down before lock acquired.
                        int rs = runStateOf(ctl.get());

                        if (rs < SHUTDOWN || (rs == SHUTDOWN && firstTask == null)) {
                            if (t.isAlive()){
                                throw new IllegalThreadStateException();
                                // precheck that t is startable
                            }
                            workers.add(w);
                            int s = workers.size();
                            if (s > largestPoolSize)
                                largestPoolSize = s;
                            workerAdded = true;
                        }
                    } finally {
                        mainLock.unlock();
                    }
                    if (workerAdded) {
                        t.start();
                        workerStarted = true;
                    }
                }
            } finally {
                if (! workerStarted){
                    //  addWorkerFailed(w);
                }

            }
            return workerStarted;
        }

    }

}
