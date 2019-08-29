package org.lc.se.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "--- my callable is running...");
        TimeUnit.SECONDS.sleep(1);
        return (int) (Math.random() * 10);
    }
}
