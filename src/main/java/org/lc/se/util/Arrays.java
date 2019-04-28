package org.lc.se.util;

import java.util.Collections;
import java.util.List;

/**
 * 测试Arrays类
 * @author lc
 */
public class Arrays {

    public static void main(String[] args) {
        testAsListMethod();
    }

    private static void testAsListMethod() {
        List<String> strings = java.util.Arrays.asList("a", "b", "c");
        System.out.println(strings);

        List<String> single = Collections.singletonList("a");
        System.out.println(single);
    }
}
