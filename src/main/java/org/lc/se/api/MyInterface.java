package org.lc.se.api;

public interface MyInterface {

    MyNum MY_NUM = new MyNum();

    String TEST = "test";

    String CONFLICT_FIELD = "interface";

    void test1();

    static void test2() {
        System.out.println("interface static method from 1.8");
    }

    default void test3() {
        System.out.println("interface default method from 1.8");
    }

    default void conflict() {
        System.out.println("my interface conflict method");
    }

    default void conflict2() {
        System.out.println("my interface conflict 2 method");
    }

    static void conflict3() {
        System.out.println("interface");
    }

}
