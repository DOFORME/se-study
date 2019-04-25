package org.lc.se.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    public static void main(String[] args) {
//        createCachedThreadPool();
        createSingleThreadPool();
    }

    private static void task(ExecutorService es) {
        for (int i = 0; i < 5; i++) {
            es.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + "-num:" + j);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void createCachedThreadPool() {
        ExecutorService es = Executors.newCachedThreadPool();
        task(es);
        es.shutdown();
    }

    private static void createSingleThreadPool() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        task(es);
        es.shutdown();
    }
}
