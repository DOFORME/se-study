package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lc
 * 使用原子引用实现自旋锁
 */
public class SpinLockTest {

    @Test
    void test1() {
        Home home = new Home();

        for (int i = 0; i < 10; i++) {
            new Thread(home::m).start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Home {
        MySpinLock lock = new MySpinLock();
        void m() {
            lock.lock();
            try {
                System.out.println("lock part");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("wake up");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

class MySpinLock {
    // 原子引用线程
    private AtomicReference<Thread> reference = new AtomicReference<>();

    void lock() {
        Thread thread = Thread.currentThread();
        System.out.println("current thread is " + thread.getName());

        // 上锁，自旋思想
        while (!reference.compareAndSet(null, thread)) {

        }
    }

    void unlock() {
        Thread thread = Thread.currentThread();
        // 解锁，讲原子引用值置空
       reference.compareAndSet(thread, null);
    }
}



