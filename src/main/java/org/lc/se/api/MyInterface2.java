package org.lc.se.api;

public interface MyInterface2 {
    default void conflict2() {
        System.out.println("my interface 2 method conflict 2");
    }

    default void run() {
        System.out.println("my interface 2 run method");
    }
}
