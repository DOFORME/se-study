package org.lc.se.api;

public interface MySubInterface extends MyInterface {

    default void sub() {
        System.out.println("MySubInterface.sub()");
    }
}
