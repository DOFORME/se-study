package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 资源释放由虚拟机gc处理，不用手动通知操作系统（close）
 * 数据源是内存
 */
class ByteArrayInputOutputStreamTest {

    @Test
    void read1() {
        byte[] src = "this is the test string".getBytes();
        InputStream is;
        try {
            is = new ByteArrayInputStream(src);
            byte[] buffer = new byte[5];
            int length;
            while ((length = is.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void write1() {
        // 有专有API，不使用多态
        ByteArrayOutputStream os;
        byte[] src = "this is the test string".getBytes();
        try {
            os = new ByteArrayOutputStream();
            os.write(src);
            byte[] result = os.toByteArray();
            // 拿数据
            System.out.println(new String(result));
            System.out.println(os.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
