package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * jdk7特性，自动释放流
 */
class TryWithResourceTest {

    @Test
    void autoClose() {
        try (InputStream is = new FileInputStream("file/src/a.txt")
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                System.out.println("读了" + length + "字节");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
