package org.lc.se.collection;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author lc
 */
public class LinkedListDemo {

    @Test
    public void asQueue() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);


        while (list.size() > 0) {
            System.out.println(list.removeLast());
        }
    }

    @Test
    public void firstAndLast() {
        LinkedList<Integer> list = new LinkedList<>();
        System.out.println(list.size());
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
    }
}
