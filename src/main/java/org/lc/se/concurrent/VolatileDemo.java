package org.lc.se.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lph
 */
public class VolatileDemo {

    public static void main(String[] args) {
        testReadable2();
    }

    private static void testReadable() {
        VolatileThreadDemo vtd = new VolatileThreadDemo();
        new Thread(vtd).start();

        while (true) {
            if (vtd.isFlag()) {
                System.out.println("结束");
                break;
            }
        }
    }

    private static void testReadable2() {
        VolatileThreadDemo2 vtd = new VolatileThreadDemo2();
        for (int i = 0; i < 10; i++) {
            new Thread(vtd).start();
        }
    }
}

class VolatileThreadDemo implements Runnable {

    private volatile boolean flag;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag:" + flag);
    }

    public boolean isFlag() {
        return flag;
    }
}

class VolatileThreadDemo2 implements Runnable {
    private volatile AtomicInteger value = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(value.getAndIncrement());
    }
}
