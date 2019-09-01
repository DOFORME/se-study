package org.lc.se.lang;

import org.junit.jupiter.api.Test;

public class RuntimeTest {

    @Test
    void properties() {
        // 逻辑处理器个数，线程池设置线程数需要按照此参数来设置
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
