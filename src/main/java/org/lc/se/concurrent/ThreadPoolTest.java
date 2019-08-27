package org.lc.se.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lph
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        judgeSame();
    }

    private static void judgeSame() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        System.out.println(es.equals(es2));
    }
}
