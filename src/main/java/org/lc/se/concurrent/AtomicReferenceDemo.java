package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lc
 */
public class AtomicReferenceDemo {

    private AtomicReference<Thread> ar = new AtomicReference<>();



    @Test
    void test1() {
        System.out.println(ar == null);
        System.out.println(ar.get() == null);


    }
}
