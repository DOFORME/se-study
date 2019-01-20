package org.lc.se.concurrent;

import java.util.concurrent.*;

public class ThreadCreateTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testMyThread();

//        testMyRunnable();

//        testMyRunnable2();

//        testRunnableWithLambda();

//        testMyCallableWithFuture();

//        testMyCallableWithFutureTask();
    }

    static void testMyThread() {
        MyThread mt = new MyThread();
        mt.start();
    }

    static void testMyRunnable() {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }

    static void testMyRunnable2() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        MyRunnable mr = new MyRunnable();
        es.submit(mr);
        es.shutdown();
    }

    static void testRunnableWithLambda() {
        Runnable runnable = () -> System.out.println("runnable and lambda");
        Thread t = new Thread(runnable);
        t.start();
    }

    static void testMyCallableWithFuture() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        MyCallable mc = new MyCallable();
        Future f = es.submit(mc);
        es.shutdown();
        System.out.println(f.get());
    }

    static void testMyCallableWithFutureTask() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        MyCallable mc = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(mc);
        es.submit(ft);
        es.shutdown();
        System.out.println(ft.get());
    }
}
