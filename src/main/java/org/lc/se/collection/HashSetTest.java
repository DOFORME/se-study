package org.lc.se.collection;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

class HashSetTest {

    /**
     * 可以存null
     */
    @Test
    void testAddNull() {
        HashSet set = new HashSet();
        set.add(null);
        System.out.println(set);
    }
}
