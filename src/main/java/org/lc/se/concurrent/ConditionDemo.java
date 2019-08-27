package org.lc.se.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信，交替打印，多条件精确唤醒
 * @author lph
 */
public class ConditionDemo {

    public static void main(String[] args) {
        testSignal();
    }

    private static void testSignal() {
        Print print = new Print();

        new Thread(new ConditionThread(print), "A").start();
        new Thread(new ConditionThread(print), "B").start();
    }

}

class ConditionThread implements Runnable {
    Print print;

    public ConditionThread(Print print) {
        this.print = print;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            print.printThreadName();
        }
    }
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
}
