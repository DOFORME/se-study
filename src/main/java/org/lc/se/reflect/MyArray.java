package org.lc.se.reflect;

public class MyArray {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5,};
        System.out.println(ints.length);
        System.out.println(java.lang.reflect.Array.getInt(ints, 3));

        java.lang.reflect.Array.setInt(ints, 3, 0);

        Object o = java.lang.reflect.Array.get(ints, 3);
        if (o instanceof Integer) {
            int i = (int) o;
            System.out.println(i);
        }

        Class clz = int.class;
        Object o2 = java.lang.reflect.Array.newInstance(clz, 5);
        if (o2 instanceof int[]) {
            int[] ints1 = (int[]) o2;
            for (int i : ints1) {
                System.out.println(ints1[i]);
            }
        }


        MyArray a = new MyArray();
        a.m("s", "ss", "sss");


        double f = 111.123456;
        System.out.printf("%1.4f", f);

        a.m(1);
        a.m("1");
    }

    public <T> void m(T s) {
        if (s instanceof String) {
            System.out.println("string");
        }
        if (s instanceof Integer) {
            System.out.println("int");
        }
    }

    public void m(String... ss) {
        System.out.println(ss.getClass());
    }
}
