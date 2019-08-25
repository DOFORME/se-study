package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lc
 * 可重入读写锁
 */
public class ReentrantReadWriteLockTest {

    private Map<String, String> map = new HashMap<>();

    public void unsafeAdd(String key, String value) {
        map.put(key, value);
    }

    public void show() {
        System.out.println(map);
    }


    @Test
    void unsafeAdd() {
        for (int i = 0; i < 10; i++) {
            String temp = String.valueOf(i);
            new Thread(() -> {
                System.out.println("添加元素： " + temp);
                unsafeAdd(temp, temp);
            }, "lc-thread-" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        show();
    }
}
