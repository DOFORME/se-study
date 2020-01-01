package org.lc.se.genericity;

import java.util.AbstractList;

public class GenericClassStatement<T> {

    public void test(T t) {
        System.out.println(t.getClass().getName());
    }

    public static <T> void test2(T t) {
        System.out.println(t.getClass().getName());
    }


}
