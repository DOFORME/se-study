package org.lc.se.api;

public class MyInterfaceBaseImpl {

    String s = "super class";

    public static final String CONFLICT_FIELD = "super class";

    protected void conflict() {
        System.out.println("my interface base impl conflict method");
    }

    static void conflict3() {
        System.out.println("my interface base impl conflict method 3");
    }
}
