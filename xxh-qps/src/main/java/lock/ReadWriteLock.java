package lock;

public class ReadWriteLock {
    /**
     * 读锁持有个数
     */
    private int readCount = 0;
    /**
     * 写锁持有个数
     */
    private int writeCount = 1;

    /**
     * 获取读锁,读锁在写锁不存在的时候才能获取
     */
    public synchronized void lockRead() throws InterruptedException {
        // 写锁存在,需要wait
        while (writeCount > 0) {
            wait();
        }
        readCount++;
    }

    /**
     * 释放读锁
     */
    public synchronized void unlockRead() {
        readCount--;
        notifyAll();
    }

    /**
     * 获取写锁,当读锁存在时需要wait.
     */
    public synchronized void lockWrite() throws InterruptedException {
        System.out.println("执行锁写请求：lockWrite");
        // 先判断是否有写请求
        while (writeCount > 0) {
            wait();
        }

        // 此时已经不存在获取写锁的线程了,因此占坑,防止写锁饥饿
        writeCount++;

        // 读锁为0时获取写锁
        while (readCount > 0) {
            wait();
        }
    }

    /**
     * 释放读锁
     */
    public synchronized void unlockWrite() {
        writeCount--;
        notifyAll();
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(int writeCount) {
        this.writeCount = writeCount;
    }
}
