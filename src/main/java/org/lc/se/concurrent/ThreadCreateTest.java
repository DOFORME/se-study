package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * 创建线程的三种方式优势对比：
 * 实现Callable、Runnable接口方式优势是实现类可以继承其他的类，劣势是编码稍微复杂，访问当前线程需要使用Thread.currentThread()方法
 * 继承Thread类创建线程的优势是编码简单，访问当前线程时直接实用this就行，劣势是继承了Thread类无法继承其他类
 * https://www.nowcoder.com/questionTerminal/e33c72bceb4343879948342e2b6e3bca
 */
public class ThreadCreateTest {

    /**
     * 继承Thread类方式创建线程
     */
    static void testMyThread() {
        ThreadDemo mt = new ThreadDemo();
        mt.start();
    }

    /**
     * 实现Callable接口方式创建线程
     */
    static void testMyRunnable() {
        Thread t = new Thread(new RunnableDemo());
        t.start();
    }

    static void testMyRunnable2() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        RunnableDemo mr = new RunnableDemo();
        es.submit(mr);
        es.shutdown();
    }

    static void testRunnableWithLambda() {
        Runnable runnable = () -> System.out.println("runnable and lambda");
        Thread t = new Thread(runnable);
        t.start();
    }

    /**
     * 通过FutureTask和Thread调用Callable
     */
    @Test
    void createWithCallable1() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask, "test").start();
        // get方法会阻塞等待任务完成，任务发生异常时这里会报异常
        System.out.println(futureTask.get());
    }

    /**
     * Callable、线程池方式调用任务
     */
    @Test
    void createWithCallable2() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        // 提交任务获得Future对象
        Future<Integer> future = es.submit(new MyCallable());
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程池、FutureTask方式创建任务
     */
    @Test
    void createWithCallable3() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        FutureTask<Integer> ft = new FutureTask<>(new MyCallable());
        es.submit(ft);

        // 如果任务还没完成就强制关闭会报异常，使用温和版的关闭
        // java.util.concurrent.ExecutionException: java.lang.InterruptedException: sleep interrupted
        // 此处sleep interrupted是因为测试时使用的是TimeUnit.SECONDS.sleep(1)延长任务执行时间
        // es.shutdownNow();
        es.shutdown();
        System.out.println(ft.get());
    }

    static void testExecutorService() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Thread t = new ThreadDemo();
        es.execute(t);
        es.shutdown();
    }

    static void testExecutorService2() {
        ExecutorService es = Executors.newSingleThreadExecutor();
//        ExecutorService es = Executors.newFixedThreadPool(0)
        for (int i = 0; i < 5; i++) {
            Runnable runnable = new RunnableDemo();
            es.execute(runnable);
        }
        es.shutdown();
    }
}
