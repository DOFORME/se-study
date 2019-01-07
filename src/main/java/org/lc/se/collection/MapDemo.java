package org.lc.se.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {

    public static void main(String[] args) {
        printHashMap();
        printTreeMap();
    }

    private static void printHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("map", "map");
        map.put("test", "test");
        map.put("test-map", "map");
        System.out.println("hash map-" + map);

        Collection<String> stringCollection = map.values();
        System.out.println("collections" + stringCollection);

        map.put(null, null);
        System.out.println(map);

        System.out.println(map.get(null));

    }

    private static void printTreeMap() {
        Map<String, String> map = new TreeMap<>();
        map.put("test", "test");
        map.put("map", "map");
        System.out.println("tree map-" + map);
    }
}
