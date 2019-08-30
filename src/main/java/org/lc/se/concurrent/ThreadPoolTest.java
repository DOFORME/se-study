package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author lph
 */
public class ThreadPoolTest {


    /**
     * 每次只有一个线程在工作
     * 使用的是无界队列（无界队列描述是该方法上的，实际上LinkedBlockingQueue是有界队列，但容量为
     * Integer.MAX_VALUE）
     * 适合有序执行
     */
    @Test
    void single() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            es.submit(() -> System.out.println(Thread.currentThread().getName() + "正在执行任务"));
        }

    }

    /**
     * 最多固定个数线程在工作
     * 使用的是无界队列
     * 适合少量耗时长的任务
     */
    @Test
    void fixed() {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            es.submit(() -> System.out.println(Thread.currentThread().getName() + "正在执行任务"));
        }
    }

    /**
     * 可以自动扩充线程个数
     * 使用的是有界队列，但是线程数可能无限增长
     * 适合大量耗时短的任务
     */
    @Test
    void cached() {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            es.submit(() -> System.out.println(Thread.currentThread().getName() + "正在执行任务"));
        }
    }

    /**
     * 带调度的线程池
     * 底层是延迟队列DelayedWorkQueue
     * 适用于延迟或定时执行的线程
     */
    @Test
    void scheduled() {
        // 使用schedule方式，带调度
        ScheduledExecutorService scheduledExecutorService = null;
        try {
             scheduledExecutorService = Executors.newScheduledThreadPool(3);
             Future<Double> result;
            for (int i = 0; i < 10; i++) {
                result = scheduledExecutorService.schedule(() -> {
                    System.out.println("scheduling");
                    return Math.random();
                }, 2, TimeUnit.SECONDS);
                System.out.println(result.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdown();
            }
        }

        // 使用submit方式，和普通线程池一样
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(3);
            Future<Double> result;
            for (int i = 0; i < 10; i++) {
                result = service.submit(() -> {
                    System.out.println("scheduling");
                    return Math.random();
                });
                System.out.println(result.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    @Test
    void myThreadPool() {
        ExecutorService es = MyThreadPool.newMyThreadPool();
        for (int i = 0; i < 100; i++) {
            es.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "正在执行任务");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 通常项目中自定义线程池而不是使用现有的线程池
 * 通过服务器逻辑处理器的个数(coreSize)确定具体corePoolSize
 * CPU密集型系统：corePoolSize = coreSize + 1
 * IO密集型系统：corePoolSize = coreSize/(1- 0.1(0.2))
 */
class MyThreadPool{
    static ThreadPoolExecutor newMyThreadPool() {
        return new ThreadPoolExecutor(20, 40, 60, TimeUnit.SECONDS
        , new LinkedBlockingQueue<>(200), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }
}
