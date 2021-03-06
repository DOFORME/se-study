package org.lc.se.api;

import java.util.Comparator;

public class MyNumTest {

    public static void main(String[] args) {
//        MyNumTest myNumTest = new MyNumTest();
//
//        myNumTest.testComparable();
//        myNumTest.testComparator();

//        testNullParameter();

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    private void testComparable() {
        MyNum myNum1 = new MyNum();
        MyNum myNum2 = new MyNum();
        myNum1.setNum(5);
        myNum2.setNum(10);
        int result = myNum1.compareTo(myNum2);
        System.out.println(result);
    }

    private void testComparator() {
        Comparator<MyNum> comparator = new MyNumComparator();
        MyNum myNum1 = new MyNum();
        MyNum myNum2 = new MyNum();
        myNum1.setNum(15);
        myNum2.setNum(100);
        int result = comparator.compare(myNum1, myNum2);
        System.out.println(result);
    }

    private static void testNullParameter() {
        MyNum myNum1 = new MyNum();
        myNum1.setNum(5);
        int result = myNum1.compareTo(null);
        System.out.println(result);
    }
}
