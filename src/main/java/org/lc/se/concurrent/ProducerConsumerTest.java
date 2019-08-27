package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 线程操作资源类
 * 2. 判断干活通知
 * 3. 防止虚假唤醒机制（object类wait方法文档里提到的多线程防止虚假唤醒，判断时使用while，不能使用if）
 * As in the one argument version, interrupts and spurious wakeups are
 * possible, and this method should always be used in a loop.
 *
 * synchronized lock版区别：
 */
class ProducerConsumerTest {

    /**
     * synchronized wait notify 方式
     */
    @Test
    void version1() {
        Restaurant restaurant = new Restaurant();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                restaurant.produce();
            }
        }, "cook").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                restaurant.consume();
            }
        }, "consumer").start();
    }

    /**
     * lock await signal 方式
     */
    @Test
    void version2() {
        Restaurant restaurant = new Restaurant();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                restaurant.produce2();
            }
        }, "cook").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                restaurant.consume2();
            }
        }, "consumer").start();
    }

    /**
     * 阻塞队列
     */
    @Test
    void version3() {
        Restaurant restaurant = new Restaurant();
        new Thread(restaurant::produce3, "cook").start();

        new Thread(restaurant::consume3, "consumer").start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Restaurant {

    private Integer foodNum = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private volatile boolean flag = true;
    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
    private AtomicInteger atomicInteger = new AtomicInteger();

    synchronized void produce() {
        while (foodNum != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "生产一份食物");
        foodNum++;
        notifyAll();
    }

    synchronized void consume() {
        while (foodNum == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "消耗一份食物");
        foodNum--;
        notifyAll();
    }

    void produce2() {
        lock.lock();

        try {
            while (foodNum != 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            foodNum++;
            System.out.println(Thread.currentThread().getName() + "生产一份食物");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    void consume2() {
        lock.lock();

        try {
            while (foodNum == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            foodNum--;
            System.out.println(Thread.currentThread().getName() + "消耗一份食物");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    void produce3() {
        while (flag) {
            try {
                boolean result = queue.offer(atomicInteger.toString(), 2, TimeUnit.SECONDS);
                if (!result) {
                    continue;
                }
                System.out.println(Thread.currentThread().getName() + "生产一份食物" + atomicInteger.toString());
                TimeUnit.SECONDS.sleep(2);
                atomicInteger.incrementAndGet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void consume3() {
        while (flag) {
            try {
                String result = queue.poll(1, TimeUnit.SECONDS);
                if (result == null) {
                    continue;
                }
                System.out.println(Thread.currentThread().getName() + "取出食物" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
