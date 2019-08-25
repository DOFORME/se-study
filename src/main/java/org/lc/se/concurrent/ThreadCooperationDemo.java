package org.lc.se.concurrent;

/**
 * @author lc
 */
public class ThreadCooperationDemo {

    public static void main(String[] args) {
//        testWaitInUnsyncBlock();
        connectThreadMsg();
    }

    private static void testWaitInUnsyncBlock() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=== begin ===");
                try {
                    Thread.sleep(5000);
                    test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            private synchronized void test() {
                try {
                    Thread.currentThread().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
//        try {
//            synchronized (ThreadCooperationDemo.class) {
//                t.wait();
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    // todo: 待解决
    private static void connectThreadMsg() {
        MyThread1 mt1 = new MyThread1();
        MyThread2 mt2 = new MyThread2();
        mt1.start();
        mt2.start();
        try {
            mt1.join();
            mt2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        String s1 = mt1.msg.get();
//        String s2 = mt2.msg.get();
//        System.out.println(s1 + s2);
        ThreadLocal<String> tl1 = mt1.msg;
        System.out.println(tl1.get());
    }

}

class MyThread1 extends Thread {
    ThreadLocal<String> msg = new ThreadLocal<>();

    @Override
    public void run() {
        msg.set("thread1");
        System.out.println("thread 1 end");
        System.out.println(msg.get());
    }
}

class MyThread2 extends Thread {
    ThreadLocal<String> msg = new ThreadLocal<>();

    @Override
    public void run() {
        msg.set("thread2");
        System.out.println("thread 2 end");
        System.out.println(msg.get());
    }


}

