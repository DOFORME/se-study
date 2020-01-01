package org.lc.se.reflect;

import org.junit.jupiter.api.Test;

/**
 * 获取类对象
 */
public class ClassTest {

    /**
     * 获取Class对象的方式
     */
    @Test
    void testGetClass() throws ClassNotFoundException {
        // 1.ClassName.class
        Class<Number> numberClass = Number.class;
        System.out.println(numberClass.getSimpleName());

        // 2.Object.getClass()
        Class<? extends String> stringClass = "abc".getClass();
        System.out.println(stringClass.getSimpleName());

        // 3.Class.forName("") 反射
        Class<?> integerClass = Class.forName("java.lang.Integer");
        System.out.println(integerClass.getSimpleName());
    }
}
