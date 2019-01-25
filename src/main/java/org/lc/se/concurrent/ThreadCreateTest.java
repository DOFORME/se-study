package org.lc.se.concurrent;

import java.util.concurrent.*;

/**
 * 创建线程的三种方式优势对比：
 * 实现Callable、Runnable接口方式优势是实现类可以继承其他的类，劣势是编码稍微复杂，访问当前线程需要使用Thread.currentThread()方法
 * 继承Thread类创建线程的优势是编码简单，访问当前线程时直接实用this就行，劣势是继承了Thread类无法继承其他类
 * https://www.nowcoder.com/questionTerminal/e33c72bceb4343879948342e2b6e3bca
 */
public class ThreadCreateTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testMyThread();

//        testMyRunnable();

//        testMyRunnable2();

//        testRunnableWithLambda();

//        testMyCallableWithFuture();

//        testMyCallable();

//        testMyCallableWithFutureTask();

//        testExecutorService();

        testExecutorService2();
    }

    /**
     * 继承Thread类方式创建线程
     */
    static void testMyThread() {
        MyThread mt = new MyThread();
        mt.start();
    }

    /**
     * 实现Callable接口方式创建线程
     */
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

    /**
     * callable方式创建线程
     * @throws ExecutionException
     * @throws InterruptedException
     */
    static void testMyCallableWithFuture() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        MyCallable mc = new MyCallable();
        Future f = es.submit(mc);
        es.shutdown();
        System.out.println(f.get());
    }

    static void testMyCallable() throws ExecutionException, InterruptedException {
        MyCallable mc = new MyCallable();
        FutureTask<Integer> futureTask =  new FutureTask<Integer>(mc);
        Thread thread = new Thread(futureTask, "test");
        thread.start();
        System.out.println(futureTask.get());
        System.out.println(thread.getName());
    }

    static void testMyCallableWithFutureTask() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        MyCallable mc = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(mc);
        es.submit(ft);
        es.shutdown();
        System.out.println(ft.get());
    }

    static void testExecutorService() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Thread t = new MyThread();
        es.execute(t);
        es.shutdown();
    }

    static void testExecutorService2() {
        ExecutorService es = Executors.newSingleThreadExecutor();
//        ExecutorService es = Executors.newFixedThreadPool(0)
        for (int i=0; i<5; i++) {
            Runnable runnable = new MyRunnable();
            es.execute(runnable);
        }
        es.shutdown();
    }
}
