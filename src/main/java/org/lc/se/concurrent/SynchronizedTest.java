package org.lc.se.concurrent;

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
}
