package org.lc.se.api;

import org.lc.se.reflect.InnerClass;

public class InnerClassDemo {
    private static String string = "test";
    private String s = "string";

    void m() {
        System.out.println(this.s);
    }

    public static void main(String[] args) {
        new InnerClassDemo().m();
    }

    class MyInnerClass{

        void m() {
            System.out.println(string);
        }
    }
}

class MyClass {

    void myMethod() {
        // 访问不到
//        System.out.println(InnerClassDemo.string);
    }
}


