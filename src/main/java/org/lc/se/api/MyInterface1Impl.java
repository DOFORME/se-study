package org.lc.se.api;

public class MyInterface1Impl extends MyInterfaceBaseImpl implements MyInterface, MyInterface2 {

    static String CONFLICT_FIELD = "implement";

    @Override
    public void test1() {
        System.out.println("my interface impl test1 method");
    }

    @Override
    public void conflict() {
        super.conflict();
    }

    @Override
    public void conflict2() {
        System.out.println("impl class conflict method 2");
    }

//    @Override
//    public void conflict3() {
//
//    }

    public static void conflict4() {
        System.out.println("implements");
    }
}
