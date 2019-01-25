package org.lc.se.concurrent;

public class ThreadOperationTest extends Thread {

    public static void main(String[] args) throws InterruptedException {
        Thread tot = new ThreadOperationTest();
        tot.start();
        for (int i=0; i<30; i++) {
            System.out.println(Thread.currentThread().getName() + i);
            sleep(10);
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName("son thread");
        for (int i=0; i<30; i++) {
            System.out.println(Thread.currentThread().getName() + "/" + i);
            System.out.println(this.getName());
            try {
                if (i == 20) {
                    this.interrupt();
                }
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
