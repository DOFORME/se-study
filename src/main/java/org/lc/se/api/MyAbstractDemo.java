package org.lc.se.api;

public class MyAbstractDemo {

    public static void main(String[] args) {
//        testAbstractStaticMethod();

        testAbstractInstanceMethod();
    }

    private static void testAbstractStaticMethod() {
        MyAbstract.staticMethod();
    }

    private static void testAbstractInstanceMethod() {
        MyAbstract myAbstract = new MyAbstract() {
            @Override
            void quite() {
                System.out.println("quite method must be implemented");
            }
        };
        myAbstract.show();
        System.out.println(myAbstract.getClass());

        MyInterface myInterface = new MyInterface() {
            @Override
            public void test1() {
                System.out.println("test");
            }
        };
        System.out.println(myInterface.getClass());
    }
}
