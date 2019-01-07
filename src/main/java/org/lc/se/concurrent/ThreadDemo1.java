package org.lc.study.concurrent;

public class ThreadDemo1 extends Thread {

    String v1 = null;
    String v2 = null;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        ThreadDemo1 t1 = new ThreadDemo1();
        t1.start();
        ThreadDemo1 t2 = new ThreadDemo1();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("子线程执行时长：" + (end - start));
        System.out.println(t1.v1 + "----" + t1.v2);
    }

    @Override
    public void run() {
        v1 = "test1";
        v2 = "test2";
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
