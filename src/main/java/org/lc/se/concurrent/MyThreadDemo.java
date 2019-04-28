package org.lc.se.concurrent;

public class MyThreadDemo extends Thread {
    private int threadNo;
    private static int threadCount;
    private int count = 5;

    MyThreadDemo() {
        threadNo = threadCount++;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("current thread no: " + threadNo + ",count: " + count);
            count--;
            if (count == 0) {
                System.out.println("thread no: " + threadNo + "finished");
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            new MyThreadDemo().start();
        }
    }
}
