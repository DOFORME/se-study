package org.lc.se.concurrent;

/**
 * @author lc
 */
public class ThreadLocalDemo {


    public static void main(String[] args) {

    }

    private static void createThreadLocal() {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){};
    }
}
