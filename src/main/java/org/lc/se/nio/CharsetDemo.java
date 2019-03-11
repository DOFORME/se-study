package org.lc.se.nio;

import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

public class CharsetDemo {

    public static void main(String[] args) {
        showCharsets();
    }

    /**
     * 展示Charset支持哪些编码
     */
    private static void showCharsets() {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(map.get(key));
        }
    }
}
