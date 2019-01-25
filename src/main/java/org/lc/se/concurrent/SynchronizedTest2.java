package org.lc.se.concurrent;

public class SynchronizedTest2 implements Runnable {

    private int i;


    public static void main(String[] args) {
        Runnable r = new SynchronizedTest2();
        Thread thread = new Thread(r);
        Thread thread2 = new Thread(r);
        thread.start();
        thread2.start();
    }

    @Override
    public void run() {
        for (int j=0; j<10; j++) {
            System.out.println(Thread.currentThread().getName() + "/" + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            add();
            System.out.println(Thread.currentThread().getName() + "/" + i);
        }
    }

    void add() {
        i++;
    }
}
