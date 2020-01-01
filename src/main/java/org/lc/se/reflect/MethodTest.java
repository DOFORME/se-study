package org.lc.se.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class MethodTest {

    @Test
    void testGetMethods() {
        Class<String> strClass = String.class;
        Method[] methods = strClass.getMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
    }
}
