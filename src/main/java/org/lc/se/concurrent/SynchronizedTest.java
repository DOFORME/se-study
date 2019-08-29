package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class SynchronizedTest implements Runnable {
    static SynchronizedTest instance = new SynchronizedTest();

    static int i = 0;

    public static void main(String[] args) throws Exception {
        testAddWithoutSynchronized();
    }

    static void testAddWithoutSynchronized() throws Exception {
        Thread t = new Thread(instance);
        Thread t2 = new Thread(instance);
        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(i);
    }


    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            i++;
        }
    }

    @Test
    void test1() {
        new Thread(() -> new SynchronizedTestThread().m1()).start();

        new Thread(() -> new SynchronizedTestThread().m1()).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SynchronizedTestThread {

    synchronized void m1() {
        System.out.println("come in");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("over in");
    }
}
