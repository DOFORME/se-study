package org.lc.se.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lc
 */
public class HashMapDemo {

    @Test
    public void capacity() {
        Map<String, String> map = new HashMap<>(17);
        map.put("t", "t");
        map.put("s", "t");
        System.out.println(map.get("t"));
    }

    @Test
    public void hashcode() {
        String s = "s";
        System.out.println(s.hashCode());
    }

    @Test
    public void hashSet() {
        Set<Integer> set = new HashSet<>();
        System.out.println("ready");
        set.add(1);
        System.out.println("yes");
    }

    @Test
    public void specialCapacity() {
        HashMap<String, String> map = new HashMap<>(3);
        map.put("a", "a");
        map.put("b", "b");
    }

}
