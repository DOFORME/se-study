package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class WaitNotifyTest {

    /**
     * 测试object的wait和notify以及notifyAll方法
     */
    @Test
    void test1() {
        MyThread myThread = new MyThread();
        for (int i = 0; i < 5; i++) {
            new Thread(myThread::m1).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 这里无论是调用notify会随机唤醒一个线程
        // myThread.m2();
        // 调用notifyAll会唤醒所有线程，但是同一时刻只有一个能抢到对象锁
        myThread.m3();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生产消费（适合两个线程的）
     */
    @Test
    void test2() {
        MyThread myThread = new MyThread();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                myThread.increment();
            }
        }).start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                myThread.decrement();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 适合多线程的（自旋思想）
     */
    @Test
    void test3() {
        MyThread myThread = new MyThread();
        for (int i = 0; i < 10; i++) {
            new Thread(myThread::increment2).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(myThread::decrement2).start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread {

    private int num = 0;
    /**
     * wait是object的方法，会释放锁
     * 必须在同步代码块里面使用，否则报java.lang.IllegalMonitorStateException
     */
    synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "==> come in");
        try {
            this.wait();
            // 测试1中如果这里执行太久，则后面的代码得不到执行程序就退出了
            // TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "==> over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 必须在同步代码块里面使用，否则报java.lang.IllegalMonitorStateException
     */
    synchronized void m2() {
        this.notify();
    }

    synchronized void m3() {
        System.out.println("notifyAll");
        this.notifyAll();
    }

    synchronized void increment() {
        if (num != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ++num;
        System.out.println(num);
        this.notify();
    }

    synchronized void decrement() {
        if (num == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        --num;
        System.out.println(num);
        this.notify();
    }

    synchronized void increment2() {
        while (num != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ++num;
        System.out.println(num);
        this.notifyAll();
    }

    synchronized void decrement2() {
        while (num == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        --num;
        System.out.println(num);
        this.notifyAll();
    }
}
