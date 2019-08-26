package org.lc.se.concurrent;

/**
 * 此例中会导致某个线程最终一直等待，程序得不到结束。
 * @author lph
 */
public class ProducerConsumerDemo1 {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Waiter waiter = new Waiter();

        new Thread(new Producer(waiter)).start();
        new Thread(new Consumer(waiter)).start();
    }
}

class Waiter {

    int capacity = 0;

    synchronized void in() {
        if (capacity > 0) {
            System.out.println("已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("生产产品，当前产品存量：" + ++capacity);
            this.notifyAll();
        }
    }

    synchronized void out() {
        if (capacity <= 0) {
            System.out.println("已空");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("消费产品，当前产品存量：" + --capacity);
            this.notifyAll();
        }
    }

}

class Producer implements Runnable{
    private Waiter waiter;

    public Producer(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            waiter.in();
        }
    }
}

class Consumer implements Runnable {
    private Waiter waiter;

    public Consumer(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            waiter.out();
        }
    }
}
