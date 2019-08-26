package org.lc.se.concurrent;

/**
 * 此例正常，但仅限于消费者和生产者只有一个的情况
 * @author lph
 */
public class ProducerConsumerDemo2 {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Waiter2 waiter = new Waiter2();

        new Thread(new Producer2(waiter)).start();
        new Thread(new Consumer2(waiter)).start();
    }
}

class Waiter2 {

    int capacity = 0;

    synchronized void in() {
        if (capacity > 0) {
            System.out.println("已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("生产产品，当前产品存量：" + ++capacity);
        this.notifyAll();

    }

    synchronized void out() {
        if (capacity <= 0) {
            System.out.println("已空");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费产品，当前产品存量：" + --capacity);
        this.notifyAll();

    }

}

class Producer2 implements Runnable {
    private Waiter2 waiter;

    public Producer2(Waiter2 waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            waiter.in();
        }
    }
}

class Consumer2 implements Runnable {
    private Waiter2 waiter;

    public Consumer2(Waiter2 waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            waiter.out();
        }
    }
}
