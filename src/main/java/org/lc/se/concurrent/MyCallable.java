package org.lc.se.concurrent;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    private int i = 0;

    @Override
    public Integer call() throws Exception {
        System.out.println("my callable is running...");
        Thread.sleep(1000);
        return i;
    }
}
