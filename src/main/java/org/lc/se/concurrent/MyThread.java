package org.lc.se.concurrent;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("my thread is running...");
    }
}
