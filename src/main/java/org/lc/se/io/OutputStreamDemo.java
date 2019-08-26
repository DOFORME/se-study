package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class OutputStreamDemo {

    /**
     * 向文件写入数据，数据为ascii表第b个字符
     */
    @Test
    void write1() throws IOException {
        File file = new File("/a.txt");
        OutputStream os = new FileOutputStream(file);
        os.write(40);
        os.close();
    }

    /**
     * 将字节数组的数据写入输出流
     */
    @Test
    void write2() throws IOException {
        File file = new File("/a.txt");
        OutputStream os = new FileOutputStream(file);
        byte[] bs = new byte[10];
        bs[0] = 40;
        bs[1] = 70;
        // 实际有10个字节，没有赋值的使用默认值0，ascii表null
        System.out.println(bs.length);
        os.write(bs);
        os.close();
    }

    /**
     * 字节数组从off写len个到输出流
     */
    @Test
    void write3() throws IOException {
        File file = new File("/a.txt");
        OutputStream os = new FileOutputStream(file);
        byte[] bs = {0,0,0,81,82,83,84};
        os.write(bs, 2, 5);
        os.close();
    }

    /**
     * 刷新输出流，并强制写出所有的缓冲字节
     */
    @Test
    void flush() throws IOException {
        File file = new File("/a.txt");
        OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bs = {0,0,0,81,82,83,84};
        os.write(bs, 2, 5);
        os.flush();
        // close时会自动刷新，可以在close这打断点查看不同
        os.close();
    }


}
