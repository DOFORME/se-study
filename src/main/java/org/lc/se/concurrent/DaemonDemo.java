package org.lc.se.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 守护线程，不是应用里不可或缺的一部分，当所有的非守护线程结束时程序结束
 *
 * @author lc
 */
public class DaemonDemo extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        setDaemon();
        testDaemonFinally();
    }

    /**
     * 不等守护线程结束程序就结束
     */
    private static void setDaemon() {
        Thread t = new DaemonDemo();
        t.setDaemon(true);
        t.start();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试守护线程的finally块代码是否执行
     * 结果：不执行
     */
    private static void testDaemonFinally() {
        Thread t = new Thread(() -> {
            try {
                System.out.println("wait...");
                Thread.sleep(10000);
            } catch (Exception e) {
                System.out.println("exception");
            } finally {
                System.out.println("run in finally");
            }
        });
        t.setDaemon(true);
        t.start();
        System.out.println("application finished");
    }

    /**
     * 测试守护线程的子线程是否是守护线程
     * 结果：是
     */
    private static void testDaemonChildThread() {

    }
}
