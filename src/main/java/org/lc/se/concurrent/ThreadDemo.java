package org.lc.se.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo extends Thread {

    public ThreadDemo() {
        System.out.println("on creating my thread");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "my thread is running...");
        }
        System.out.println(Thread.currentThread().getName() + "task end...");
    }

    public static void main(String[] args) {
        testYield();
    }

    private static void test() {
        for (int i = 0; i < 100; i++) {
            new ThreadDemo().start();
        }
    }

    private static void testYield() {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            es.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + "-" + j);
                    Thread.yield();
                }
            });
        }
    }
}
