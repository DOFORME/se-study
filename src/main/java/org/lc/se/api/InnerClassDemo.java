package org.lc.se.api;

public class InnerClassDemo {
    private static String string = "test";
    private String s = "string";

    private MyClass myClass;

    void m() {
        System.out.println(this.s);
    }

    public static void main(String[] args) {
        new InnerClassDemo().m();
    }

    class MyInnerClass{
        static final int i = 0;
        // 不允许
//        static int j = 0;

        void m() {
            System.out.println(string);
            System.out.println(s);
            System.out.println(InnerClassDemo.this.s);
        }
    }
}

class MyClass {

    void myMethod() {
        // 访问不到
//        System.out.println(InnerClassDemo.string);
    }
}


