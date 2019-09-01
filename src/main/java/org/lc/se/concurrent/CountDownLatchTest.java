package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch闭锁/共享锁
 */
public class CountDownLatchTest {

    /**
     * 控制主线程等待子线程全部执行完
     */
    @Test
    void test1() {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    System.out.println("i = " + temp);
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(temp + "等待结束");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        // 等待子线程全部执行完
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
