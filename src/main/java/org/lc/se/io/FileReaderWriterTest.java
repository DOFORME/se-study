package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 字符流只适合处理文档
 */
class FileReaderWriterTest {

    /**
     * 直接读文档不乱码的前提是文档的字符集和平台一致
     */
    @Test
    void read1() {
        String filePath = "file/src/a.txt";
        FileReader reader = null;
        try {
            reader = new FileReader(filePath);
            int length;
            char[] buffer = new char[10];
            while ((length = reader.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取和平台不一致的文档，乱码，可以用转换流处理
     */
    @Test
    void read2() {
        String filePath = "file/src/b-gbk.txt";
        FileReader reader = null;
        try {
            reader = new FileReader(filePath);
            int length;
            char[] buffer = new char[10];
            while ((length = reader.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void write1() {
        String filePath = "file/src/a.txt";
        Writer writer = null;
        try {
            writer = new FileWriter(filePath);
            writer.append("写入测试1\n");
            writer.append("写入测试2\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void copy1() {
        String srcPath = "file/src/b-gbk.txt";
        String desPath = "file/des/b-gbk-copy.txt";
        Reader reader = null;
        Writer writer = null;

        try {
            reader = new FileReader(srcPath);
            writer = new FileWriter(desPath);
            char[] buffer = new char[10];
            int length;
            while ((length = reader.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
                writer.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
