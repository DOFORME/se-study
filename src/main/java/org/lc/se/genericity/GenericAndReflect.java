package org.lc.se.genericity;

public class GenericAndReflect {

    public static void main(String[] args) {
        showClassTrueType();
    }

    public static void showClassTrueType() {
        Class clz = String.class;
        System.out.println(clz.getTypeName());
    }
}
