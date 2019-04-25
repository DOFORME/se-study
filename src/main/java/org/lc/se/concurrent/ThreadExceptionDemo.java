package org.lc.se.concurrent;

import java.util.concurrent.*;

/**
 * 处理线程里发生异常的情况，通常的情况下无法捕获
 *
 * @author lc
 */
public class ThreadExceptionDemo {

    public static void main(String[] args) {
        delException();
//        delExceptionWithExecutorService();
//        delExceptionWithNothing();
//        delExceptionWithThreadUncaughtExceptionHandler();
//        delExceptionWithDefaultExceptionHandler();
    }

    private static void delException() {
        Thread t = new Thread(() -> {
            try {
                System.out.println("exception test");
                throw new RuntimeException("test");
            } catch (Exception e) {
                System.out.println("exception occur");
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("join exception");
        }
    }

    private static void delExceptionWithNothing() {
        try {
            Thread t = new Thread(() -> {
                throw new RuntimeException("thread exception...");
            });
            t.start();
        } catch (Exception e) {
            System.out.println("exception");
        }
    }

    private static void delExceptionWithExecutorService() {
        ExecutorService es = Executors.newCachedThreadPool();
        try {
            es.execute(() -> {
                throw new RuntimeException("thread exception...");
            });
        } catch (Exception e) {
            System.out.println("exception");
        }
        es.shutdown();
    }

    private static void delExceptionWithThreadUncaughtExceptionHandler() {
        ExecutorService es = Executors.newCachedThreadPool(new MyUncaughtExceptionThreadFactory());
        es.execute(() -> {
            System.out.println("抛出异常的线程");
            throw new RuntimeException("exception thread");
        });
        es.shutdown();
    }

    private static void delExceptionWithDefaultExceptionHandler() {
        // 给线程设置默认异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new MyThreadUncaughtHandler2());
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(() -> {
            System.out.println("exception thread");
            throw new RuntimeException("e...");
        });
        es.shutdown();
    }

    private static class MyThreadUncaughtHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("caught exception: " + e);
        }
    }

    private static class MyThreadUncaughtHandler2 implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName());
            System.out.println(e.toString());
            System.out.println("handler 2");
        }
    }

    private static class MyUncaughtExceptionThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            System.out.println("come in...");
            t.setUncaughtExceptionHandler(new MyThreadUncaughtHandler());
            return t;
        }
    }
}
