package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 文件流没有专属API，直接使用多态即可
 */
class FileInputOutputStreamTest {

    /**
     * 普通读字节
     */
    @Test
    void read1() {
        InputStream is = null;
        try {
            is = new FileInputStream("file/src/a.txt");
            byte[] buffer = new byte[1024];
            int length;
            String s;
            while ((length = is.read(buffer)) != -1) {
                s = new String(buffer, 0, length);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 文件流关闭实际是通知操作系统释放资源
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用文件类读取文本时需要注意编码
     */
    @Test
    void read2() {
        InputStream is = null;
        try {
            is = new FileInputStream("file/src/a-gbk.txt");
            // 这里并不十分严谨，可能某个字的字节刚好被拆开
            byte[] buffer = new byte[1024];
            int length;
            String s;
            while ((length = is.read(buffer)) != -1) {
                s = new String(buffer, 0, length, Charset.forName("gbk"));
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 覆盖旧数据的写
     */
    @Test
    void write1() {
        OutputStream os = null;
        try {
            // 这种实例化方式会覆盖现有数据（覆盖的是文件以前的数据），同一个输出流中多次写出不会覆盖
            os = new FileOutputStream("file/src/a.txt");
            String s = "这是需要写入的数据";
            // 这里需要注意编码
            os.write(s.getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 追加式写
     */
    @Test
    void write2() {
        OutputStream os = null;
        try {
            // 这种实例化方式会在现有数据上追加
            os = new FileOutputStream("file/src/a.txt", true);
            String s = "这是需要写入的数据";
            // 这里需要注意编码
            os.write(s.getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 这两个流常用作非文本文件操作
     */
    @Test
    void copy1() {
        InputStream is = null;
        OutputStream os = null;
        String srcPath = "file/src/icon.png";
        String desPath = "file/des/icon-copy.png";

        try {
            // 10个字节的缓冲区
            byte[] buffer = new byte[10];
            is = new FileInputStream(srcPath);
            os = new FileOutputStream(desPath);
            // 实际读取到的字节（并不一定能刚好填满缓冲区）
            int length;
            while ((length = is.read(buffer)) != -1) {
                // 将缓冲区读到的数据写出，这里不能使用缓冲区的length
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流，先打开的后关闭
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
