package org.lc.se.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock为可重入锁、排它锁
 * 可重入锁；某个线程请求获取一个它已经获取的锁时（方法内调用同类中的同步方法或同步方法递归调用时发生），
 *         这个请求立即成功，且锁计数+1，当线程退出同步代码块时，锁计数-1，当锁计数值为0时释放锁。
 *
 * 排它锁（写锁）：同一时刻只允许一个线程持有锁
 * @author lc
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendMsg2();
        }, "t1").start();

        new Thread(() -> {
            phone.sendEmail2();
        }, "t2").start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        Phone phone = new Phone();

        new Thread(() -> {
//            phone.sendMsg();
            phone.sendNotice();
        }, "t1").start();

        new Thread(() -> {
            phone.sendEmail();
        }, "t2").start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Phone {

    public synchronized void sendMsg() {
        System.out.println("###send msg###");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println("###send email###");
    }

    public synchronized void sendNotice() {
        System.out.println("###send notice###");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg2() {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println("locked code");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("msg2 unlock");
            lock.unlock();
        }
    }

    public void sendEmail2() {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println("locked code");
        }finally {
            System.out.println("email 2 unlock");
            lock.unlock();
        }
    }
}
