package org.lc.se.collection;

import org.junit.Test;

import java.util.*;

/**
 * @author lc
 */
public class ArrayListDemo {


    @Test
    public void create1() {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>(5);
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        ArrayList<Integer> list3 = new ArrayList<>(set);

        System.out.println(list1.size());
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);

    }

    @Test
    public void iterator() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(0);
        list.add(9);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    public void range() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.set(0, 5));
        System.out.println(list.set(-10, 10));
    }

    @Test
    public void add() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1, 101);
        System.out.println(list);
    }

    @Test
    public void arrayCopy() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = new int[10];
        System.arraycopy(arr1, 2, arr2, 0, 1);
        System.out.println(Arrays.toString(arr2));
    }

    @Test
    public void base() {
        if (true | getBool(1)) {
            System.out.println("========");
        }

        if (true || getBool(2)) {
            System.out.println("========");
        }
    }

    boolean getBool(int i) {
        System.out.println("come in" + i);
        return i>0;
    }
}
