package org.lc.se.concurrent;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author lc
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) {
//        checkInterrupted();
        delNeedCleanResource();
    }

    /**
     * 检查中断状态
     */
    private static void checkInterrupted() {
        new Thread(() -> {
            System.out.println("current interrupted:" + Thread.interrupted());
            System.out.println("current interrupted:" + Thread.currentThread().isInterrupted());
            System.out.println("==== interrupt ====");
            Thread.currentThread().interrupt();

            System.out.println(Thread.currentThread().getName());
            // 这两行代码不能调换顺序
            System.out.println("current interrupted:" + Thread.currentThread().isInterrupted());
            System.out.println("current interrupted:" + Thread.interrupted());

            System.out.println("current interrupted:" + Thread.interrupted());
            System.out.println("current interrupted:" + Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName());
        }).start();

    }

    /**
     * 使用interrupt()时，对需要清理的资源处理
     */
    private static void delNeedCleanResource() {
        Thread t = new Thread(() -> {
            while (!Thread.interrupted()) {
                NeedCleanResource ncr = new NeedCleanResource();
                LinkedList<Integer> list = new LinkedList<>();
                try {
                    System.out.println("thread running...");

                    // 某个耗时操作
                    for (int i = 0; i < 10000000; i++) {
                        list.add(i);
                    }
                } finally {
                    ncr.clean();
                    System.out.println("list: " + list.size());
                }
            }
        });
        t.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }
}

class NeedCleanResource {
    public void clean() {
        System.out.println("resource has cleaned");
    }
}
