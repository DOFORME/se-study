package org.lc.se.concurrent;

/**
 * @author lph
 */
public class SynchronizedDemo {

    public static void main(String[] args) {
        testSync();
    }

    private static void testSync() {
        Demo demo = new Demo();
        Demo demo2 = new Demo();
        new Thread(new SyncDemo1(demo)).start();
        new Thread(new SyncDemo2(demo2)).start();
    }
}

class SyncDemo1 implements Runnable {
    Demo demo;

    public SyncDemo1(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.m1();
    }
}

class SyncDemo2 implements Runnable {
    Demo demo;

    public SyncDemo2(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.m2();
    }
}

class Demo {

    void m1() {
        synchronized (Demo.class) {
            System.out.println("come in m1");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1 has finished");
        }
    }

    void m2() {
        synchronized (Demo.class) {
            System.out.println("come in m2");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m2 has finished");
        }
    }
}
