package org.lc.se.concurrent;

/**
 * 此例中会导致出现超过容量capacity的情况，例如负数和大于capacity的值（虚假唤醒）
 *
 * @author lph
 */
public class ProducerConsumerDemo3 {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Waiter3 waiter = new Waiter3();

        new Thread(new Producer3(waiter), "生产者A").start();
        new Thread(new Consumer3(waiter), "消费者A").start();

        new Thread(new Producer3(waiter), "生产者B").start();
        new Thread(new Consumer3(waiter), "消费者B").start();
    }
}

class Waiter3 {

    int capacity = 0;

    synchronized void in() {
        if (capacity > 0) {
            System.out.println(Thread.currentThread().getName() + "已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "生产产品，当前产品存量：" + ++capacity);
        this.notifyAll();

    }

    synchronized void out() {
        if (capacity <= 0) {
            System.out.println(Thread.currentThread().getName() + "已空");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "消费产品，当前产品存量：" + --capacity);
        this.notifyAll();

    }

}

class Producer3 implements Runnable {
    private Waiter3 waiter;

    public Producer3(Waiter3 waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            waiter.in();
        }
    }
}

class Consumer3 implements Runnable {
    private Waiter3 waiter;

    public Consumer3(Waiter3 waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            waiter.out();
        }
    }
}
