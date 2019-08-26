package org.lc.se.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author lph
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        testCountDown();
    }

    private static void testCountDown() {
        CountDownLatch latch = new CountDownLatch(10);
        countDemo cd = new countDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(cd).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("cost " + (end - start) + "ms");
    }
}

class countDemo implements Runnable {
    CountDownLatch latch;

    public countDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        int sum = 0;
        synchronized (this) {
            try {
                for (int i = 0; i < 100000; i++) {
                    sum = sum + i;
                }
            } finally {
                System.out.println(sum);
                latch.countDown();
            }
        }
    }
}
