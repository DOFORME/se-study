package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * CyclicBarrier栅栏
 * 将线程拦截直至所有线程都在await，然后继续执行
 *
 * CountDownLatch：一个或者多个线程，等待其他多个线程完成某件事情之后才能执行；
 * CyclicBarrier：多个线程互相等待，直到到达同一个同步点，再继续一起执行。
 */
class CyclicBarrierTest {

    @Test
    void test1() {
        ExecutorService service = Executors.newCachedThreadPool();
        // 主线程（1） + 子线程数（10）
        CyclicBarrier barrier = new CyclicBarrier(11);

        for (int i = 0; i < 10; i++) {
            final int temp = i;
            service.execute(() -> {
                System.out.println(temp + " come in");
                try {
                    // 等待所有线程就绪
                    barrier.await();
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(temp + " go");
            });
        }
        try {
            // 等待所有线程就绪
            barrier.await();
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("main go");
    }
}
