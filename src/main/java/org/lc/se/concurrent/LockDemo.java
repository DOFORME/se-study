package org.lc.se.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lph
 */
public class LockDemo {

    public static void main(String[] args) {
        testWait();
    }

    private static void testWait() {
        new Thread(new LockThreadDemo()).start();
    }
}

class LockThreadDemo implements Runnable {

    @Override
    public void run() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            // 不能使用该语句，锁对象不对，应该使用Lock对应的方式
//            this.wait();

            Condition condition = lock.newCondition();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
            System.out.println("over");
        }
    }
}
