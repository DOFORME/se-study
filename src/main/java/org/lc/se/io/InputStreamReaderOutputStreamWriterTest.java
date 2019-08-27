package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 转换流
 * <p>
 * 作用：
 * 1.将字节流转换成字符流
 * 2.字符集转换
 */
class InputStreamReaderOutputStreamWriterTest {

    /**
     * 读取gbk文件内容
     */
    @Test
    void read1() {
        String filePath = "file/src/a-gbk.txt";
        try (InputStream is = new FileInputStream(filePath);
             Reader reader = new InputStreamReader(is, Charset.forName("gbk"))) {
            char[] buffer = new char[2];
            while (reader.read(buffer) != -1) {
                System.out.print(Arrays.toString(buffer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将系统字符串（utf8编码）写入gbk文件中
     */
    @Test
    void write1() {
        String filePath = "file/des/a-gbk-copy.txt";
        try (OutputStream os = new FileOutputStream(filePath, true);
             Writer writer = new OutputStreamWriter(os, Charset.forName("gbk"))) {
            String s = "中文测试";
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件从utf8编码复制为gbk编码文件
     */
    @Test
    void copy1() {
        String srcPath = "file/src/c-utf8.txt";
        String desPath = "file/des/c-gbk-copy.txt";
        try (
                InputStream is = new FileInputStream(srcPath);
                InputStreamReader reader = new InputStreamReader(is, Charset.forName("utf8"));
                OutputStream os = new FileOutputStream(desPath);
                OutputStreamWriter writer = new OutputStreamWriter(os, Charset.forName("gbk"))
        ) {
            char[] buffer = new char[2];
            int length;
            while ((length = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
