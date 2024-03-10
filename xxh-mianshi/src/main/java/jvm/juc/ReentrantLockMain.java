package jvm.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMain {


    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        Condition condition = reentrantLock.newCondition();


    }
}
