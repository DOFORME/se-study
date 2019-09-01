package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 * 用户控制资源被同时访问的个数
 * 应用：连接池连接
 */
class SemaphoreTest {

    @Test
    void test1() {
        Connection connection = new Connection();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                connection.getConnection();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                connection.closeConnection();
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Connection{
        Semaphore semaphore = new Semaphore(10);

        void getConnection() {
            try {
                long start = System.currentTimeMillis();
                boolean tryAcquire = semaphore.tryAcquire(5, TimeUnit.SECONDS);
                System.out.println("此次获取许可耗时： " + (System.currentTimeMillis() - start) + "ms");
                if (tryAcquire) {
                    System.out.println(UUID.randomUUID().toString());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new RuntimeException("get connection fail");
        }

        void closeConnection() {
            semaphore.release();
            System.out.println("连接释放");
        }
    }
}
