package org.lc.se.genericity;

import org.junit.jupiter.api.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型方法的声明，类可以是非泛型的
 */
public class GenericMethodStatement {

    public static <T> void test(T t) {
        System.out.println(t.getClass().getName());
    }

    public <T> void test2(T t) {
        System.out.println(t.getClass().getName());
    }

    /**
     * 功能是比较两个数值的大小
     * T是继承Number的某种类型
     */
    public <T extends Number> void test3(T t, T t2) {
        System.out.println(t.doubleValue() > t2.doubleValue());
    }

    /**
     * T是AbstractList的子类
     * AbstractList里存储的是E类型数据
     * <T extends AbstractList<E>, E>该结构是用来声明这个方法是泛型方法
     */
    public <T extends AbstractList<E>, E> void test4(T t) {
        for (E e : t) {
            System.out.println(e);
        }
    }

    public <T extends Comparable<T>> boolean test5(T t, T t2) {
        return t.compareTo(t2) > 0;
    }

    /**
     * 意义为List里的泛型为Double或者Double的父类
     */
    public void test6(List<? super Double> t) {
        if (t.size() > 0) {
            System.out.println(t.get(0).getClass().getSimpleName());
        }
    }

    public void test7(Comparable<? extends Double> t) {

    }

    public void test8(Comparable<? super Double> t) {

    }

    @Test
    void test3() {
        test3(100, 100.000);
    }

    @Test
    void test4() {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(5);
        a.add(1);
        this.test4(a);
    }

    @Test
    void test5() {
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        System.out.println(test5(a, b));
        System.out.println(test5(d, c));
    }

    @Test
    void test6() {
        List<Number> numbers = new ArrayList<>();
        numbers.add(10.2);
        test6(numbers);
        List<Object> objects = new ArrayList<>();
        objects.add(new Object());
        test6(objects);
    }
}
