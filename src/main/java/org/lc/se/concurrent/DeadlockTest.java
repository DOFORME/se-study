package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * 死锁：多个线程都在等待对方释放持有的锁才能继续执行下去
 */
public class DeadlockTest {

    @Test
    void test1() {
        // 生命两对象作为后续线程的锁对象
        ClassA a = new ClassA();
        ClassB b = new ClassB();

        // 开启两线程
        new Thread(() -> a.m(b)).start();
        new Thread(() -> b.m(a)).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 与上面的单元测试不同，这里程序不退出
        ClassA a = new ClassA();
        ClassB b = new ClassB();
        new Thread(() -> a.m(b)).start();
        new Thread(() -> b.m(a)).start();
        // main是用户线程
        System.out.println(Thread.currentThread().isDaemon());
    }


}

class ClassA{
    synchronized void m(ClassB b) {
        System.out.println("a m");
        try {
            TimeUnit.SECONDS.sleep(2);
            // 获取b对象的锁
            b.n();
            System.out.println("a finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void n() {
        System.out.println("a n");
    }
}

class ClassB{
    synchronized void m(ClassA a) {
        System.out.println("b m");
        try {
            TimeUnit.SECONDS.sleep(2);
            // 获取a对象的锁
            a.n();
            System.out.println("b finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void n() {
        System.out.println("b n");
    }
}
