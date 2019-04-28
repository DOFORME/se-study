package org.lc.se.type;

public class ArrayDemo {

    public static void main(String[] args) {
        testArrayElement();
    }

    private static void testArrayElement() {
        StringBuilder sb = new StringBuilder("abc");
        StringBuilder[] sbs = new StringBuilder[10];
        sbs[0] = sb;
        System.out.println(sbs[0]);
        sb.append("cba");
        System.out.println(sbs[0]);
    }



}

