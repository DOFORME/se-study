package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 通过AQS实现自己的锁（排他/共享锁）
 */
class MyLockTest {

    /**
     * 排他锁测试
     */
    @Test
    void test1() {
        Phone phone = new Phone();
        for (int i = 0; i < 10; i++) {
            new Thread(phone::m).start();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 共享锁测试
     */
    @Test
    void test2() {
        Phone phone = new Phone();

        for (int i = 0; i < 5; i++) {
            new Thread(phone::m2).start();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 独占锁实现
     * 参考ReentrantLock
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if (!isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }

    /**
     * 共享锁实现
     * 参考CountDownLatch
     */
    private static class Sync2 extends AbstractQueuedSynchronizer{
        @Override
        protected int tryAcquireShared(int arg) {
            return getState() == 0 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            while (true) {
                int currentState = getState();
                if (currentState == 0) {
                    // 表示没有加锁的线程了
                    return false;
                }

                // 有加锁的线程
                int nextState = currentState -1;
                if (compareAndSetState(currentState, nextState)) {
                    return nextState == 0;
                }
            }
        }
    }

    /**
     * 独占锁
     */
    private static class MyExclusiveLock implements Lock {

        private Sync sync = new Sync();

        @Override
        public void lock() {
            sync.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            sync.release(1);
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }

    /**
     * 共享锁
     */
    private static class MySharedLock implements Lock{
        private Sync2 sync2 = new Sync2();

        @Override
        public void lock() {
            sync2.acquireShared(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            sync2.releaseShared(1);
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }

    private static class Phone {
        MyExclusiveLock lock = new MyExclusiveLock();
        MySharedLock lock2 = new MySharedLock();

        void m() {
            System.out.println("come in");
            lock.lock();
            try {
                System.out.println("locked");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock over");
            } finally {
                lock.unlock();
            }
        }

        void m2() {
            System.out.println("m2 come in");
            lock2.lock();
            try {
                System.out.println("locked");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock over");
            } finally {
                lock2.unlock();
            }
        }
    }
}
