package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制动态数组，每次添加都会复制一份，不适合频繁增删的场景（内存资源浪费）
 * 适合并发迭代多时使用
 * @author lph
 */
public class CopyOnWriteArrayListTest {

    @Test
    void testSyncList() {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (String s : list) {
                    System.out.println(s);
                    list.add("z");
                }
            }).start();
        }
    }
}

