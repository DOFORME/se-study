package org.lc.se.api;

public class LambdaDemoInterfaceImpl implements LambdaDemoInterface<String> {

    LambdaDemoInterfaceImpl() {}

    LambdaDemoInterfaceImpl(String s) {
        System.out.println("give interface my constructor");
        System.out.println(s);
    }

    @Override
    public void test(String s) {
        System.out.println("LambdaDemoInterfaceImpl.class test method");
    }
}
