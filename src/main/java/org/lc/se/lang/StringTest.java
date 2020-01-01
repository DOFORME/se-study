package org.lc.se.lang;


import org.junit.jupiter.api.Test;

/**
 * @author lph
 */
class StringTest {

    @Test
    void cutString() {
        String s = "abcdefgh";
        String ss = s.substring(1, 2);
        System.out.println(ss);
    }

    @Test
    void createString() {
        String a = "a";
        String s1 = a + "bc";
        String s2 = "abc";
        System.out.println(s1 == s2);
    }
}
