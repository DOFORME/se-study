package org.lc.se.api;

public abstract class MyAbstract {

    static void staticMethod() {
        System.out.println("MyAbstract's static method");
    }

    public MyAbstract() {
        System.out.println("no argument constructor of MyAbstract");
    }

    void show() {
        System.out.println("show method");
    }

    abstract void quite();
}
