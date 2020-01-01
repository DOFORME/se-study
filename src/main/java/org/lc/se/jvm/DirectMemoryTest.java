package org.lc.se.jvm;

import org.junit.jupiter.api.Test;

public class DirectMemoryTest {

    @Test
    void printDirectMemory() {
        // 默认单位Byte，大致是物理内存的1/4
        System.out.println(sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024);
    }
}
