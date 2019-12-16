package lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLCachedData {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();


    void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did.
                if (!cacheValid) {
                    data = "数据";
                    cacheValid = true;
                }
                // Downgrade by acquiring read lock before releasing write lock
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }
        try {
            //TODO 用来使用数据  这里做测试  只是打印
            System.out.println("当前线程的名称："+Thread.currentThread().getName()+",数据:"+data);
        } finally {
            rwl.readLock().unlock();
        }
    }


    public static void main(String[] args) {
        RWLCachedData cachedData = new RWLCachedData();
        while (true){
            cachedData.processCachedData();
        }
    }
}
