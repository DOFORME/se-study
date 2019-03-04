package org.lc.se.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BufferedReaderWriterDemo {
    private static final String TEST_FILE_PATH = "/test.txt";
    private static final String TEST_FILE_PATH2 = "/test2.txt";

    public static void main(String[] args) {
        testCreate();
    }

    private static void testCreate() {
        File test = new File(TEST_FILE_PATH);
        try {
            // 指定缓冲区大小
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(test), "gbk"), 1024);
            // 不指定
            BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(test), "gbk"));
            System.out.println(br.readLine());
            System.out.println(br2.readLine());

            // 注意：使用FileWriter、FileReader读取windows默认TXT文件时乱码（windows简体中文默认编码gbk，Java系统默认UTF-8）
            BufferedWriter bw = new BufferedWriter(new FileWriter(TEST_FILE_PATH2));
            bw.write("中文测试");

            BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE_PATH2), StandardCharsets.UTF_8), 1024);
            System.out.println("************");
            // 不加flush()可能此时还未写入文件
            bw.flush();
            System.out.println(br3.readLine());

            br.close();
            br2.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
