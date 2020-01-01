package org.lc.se.jvm;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class OomTest {

    @Test
    void testDirectMemory() {
        // 1.配置直接内存的启动参数 java -XX:MaxDirectMemory=5m

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
