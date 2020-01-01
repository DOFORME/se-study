package org.lc.se.genericity;

import org.junit.jupiter.api.Test;

public class GenericClassTest {

    /**
     * Class<T> 来传递Class对象
     */
    @Test
    void testClass() {
        Class<String> clz = String.class;
        printClassName(clz);
        printClassName(String.class);
    }

    private <T> void printClassName(T clz) {
        System.out.println(clz.getClass().getSimpleName());
    }
}
