package org.lc.se.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock解决同步问题，使用Condition和循环解决虚假唤醒问题(即容量超过边界问题，当被唤醒时不是第一时间去消费或生产，而是重新判断一次)
 *
 * @author lph
 */
public class ProducerConsumerDemo4 {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Waiter4 waiter = new Waiter4();

        new Thread(new Producer4(waiter), "生产者A").start();
        new Thread(new Consumer4(waiter), "消费者A").start();

        new Thread(new Producer4(waiter), "生产者B").start();
        new Thread(new Consumer4(waiter), "消费者B").start();
    }
}

class Waiter4 {

    int capacity = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    void in() {
        lock.lock();
        try {
            while (capacity > 0) {
                System.out.println(Thread.currentThread().getName() + "已满");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(Thread.currentThread().getName() + "生产产品，当前产品存量：" + ++capacity);
            condition.signalAll();
        } finally {
            lock.unlock();
        }


    }

    void out() {
        lock.lock();
        try {
            while (capacity <= 0) {
                System.out.println(Thread.currentThread().getName() + "已空");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "消费产品，当前产品存量：" + --capacity);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

}

class Producer4 implements Runnable {
    private Waiter4 waiter;

    public Producer4(Waiter4 waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            waiter.in();
        }
    }
}

class Consumer4 implements Runnable {
    private Waiter4 waiter;

    public Consumer4(Waiter4 waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            waiter.out();
        }
    }
}
