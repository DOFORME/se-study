package org.lc.se.collection;

import java.util.*;

public class MapToArray {

    private static final List<String> STATIC_LIST = new ArrayList<>();

    static {
        STATIC_LIST.add("test");
        STATIC_LIST.add("list");
    }

    public static void main(String[] args) {
        MapToArray mta = new MapToArray();

        mta.testConcurrentModificationException();

        mta.testTreeSet();
    }

    public void testBase() {
        List<String> list = new ArrayList<>();
        list.add("test");
        list.add("list");
        String[] strings = new String[1];
        strings = list.toArray(strings);
        for (String s : strings) {
            System.out.println(s);
        }

        STATIC_LIST.add("DYNAMIC");
        for (String s : STATIC_LIST) {
            System.out.println("static list-" + s);
        }

        ListIterator<String> iterator = list.listIterator();
        iterator.hasPrevious();
        iterator.add("iterator");
        System.out.println(iterator.nextIndex());
        String str = null;
        if (iterator.hasNext()) {
            str = iterator.next();
            System.out.println(str);
        }
        iterator.set("it has changed");
        System.out.println(str);

        for (String s : list) {
            System.out.println(s);
        }
    }

    public void testConcurrentModificationException() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bc");
        Iterator iterator1 = list.iterator();
        Iterator iterator2 = list.iterator();

        iterator1.next();
        iterator2.next();
    }

    public void testTreeSet() {
        Set<String> strings = new TreeSet<>();
        strings.add("bob");
        strings.add("alice");
        strings.add("carl");
        for (String s : strings) {
            System.out.println(s);
        }

        Set<String> newSet = new HashSet<>(strings);
        for (String s : newSet) {
            System.out.println("hash set-" + s);
        }

    }


}
