package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信，交替打印，多条件精确唤醒
 * @author lph
 */
public class ConditionDemo {

    @Test
    void testSignal() {
        Print print = new Print();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                print.printThreadName();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                print.printThreadName();
            }
        }, "B").start();
    }

    /**
     * 精确唤醒
     */
    @Test
    void test1() {
        Print print = new Print();

        // 两个线程均阻塞等待
        new Thread(print::print1).start();
        new Thread(print::print2).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 先唤醒2，再唤醒1，注意这几个方法都需要被加锁
        print.aware2();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print.aware1();
    }


    class Print {
        Lock lock = new ReentrantLock();
        String lastThreadName = "";

        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        void printThreadName() {
            lock.lock();
            try {
                String name = Thread.currentThread().getName();
                if (lastThreadName.equalsIgnoreCase(name)) {
                    if ("A".equalsIgnoreCase(name)) {
                        condition2.signal();
                        condition1.await();
                    } else {
                        condition1.signal();
                        condition2.await();
                    }
                }
                System.out.println(name);
                lastThreadName = name;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        void print1() {
            lock.lock();
            try {
                condition1.await();
                System.out.println("print1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        void print2() {
            lock.lock();
            try {
                condition2.await();
                System.out.println("print2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        void aware1() {

            lock.lock();
            try {
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }

        void aware2() {
            lock.lock();
            try {
                condition2.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}


