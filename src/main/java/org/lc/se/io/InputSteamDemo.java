package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class InputSteamDemo {

    /**
     * 返回此流下一次方法调用不受阻塞的从输入流中读取到的字节数
     */
    @Test
    void available() throws IOException {
        File file = new File("/a.txt");
        InputStream is = new FileInputStream(file);
        System.out.println(is.available());
        is.close();
    }

    @Test
    void mark() throws IOException {
        File file = new File("/a.txt");
        InputStream is = new FileInputStream(file);

        System.out.println(is.markSupported());
//        int i = 0;
//        int code;
//        for (; (code = is.read()) == -1; ){
//            i++;
//            if (i == 1) {
//                is.mark(3);
//            }
//            char c = (char) code;
//            System.out.println(c);
//        }
//
//        is.reset();
//        for (; (code = is.read()) == -1; ){
//            i++;
//            if (i == 1) {
//                is.mark(3);
//            }
//            char c = (char) code;
//            System.out.println(c);
//        }
//
        is.close();
    }

    /**
     * 每次读一字节
     */
    @Test
    void read1() throws IOException {
        File file = new File("/a.txt");
        InputStream is = new FileInputStream(file);
        int b = is.read();
        System.out.println((char) b);
        is.close();
    }

    /**
     * 读取字节到字节数组，返回读取到的字节数
     */
    @Test
    void read2() throws IOException {
        File file = new File("/a.txt");
        InputStream is = new FileInputStream(file);
        byte[] bs = new byte[10];
        int result = is.read(bs);
        System.out.println(result);
        System.out.println(new String(bs));
        is.close();
    }

    /**
     * 将输入流中的len个字节读入字节数组，在数组中的偏移量为off
     */
    @Test
    void read3() throws IOException {
        File file = new File("/a.txt");
        InputStream is = new FileInputStream(file);
        byte[] bs = new byte[10];
        int result = is.read(bs, 2, 3);
        for (byte b : bs) {
            System.out.println(b);
        }
        System.out.println(result);
        System.out.println(new String(bs));
        is.close();
    }

    /**
     * 跳过指定字节数不读
     */
    @Test
    void skip() throws IOException {
        File file = new File("/a.txt");
        InputStream is = new FileInputStream(file);
        long skip = is.skip(4);
        System.out.println(skip);
        byte[] bs = new byte[10];
        int read = is.read(bs);
        System.out.println(read);
        System.out.println(new String(bs));
        is.close();
    }

    @Test
    void download() throws IOException {
        URL url = new URL("https://www.gstatic.com/images/branding/googlemic/2x/googlemic_color_24dp.png");
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        File localFile = new File("/icon.png");
        OutputStream os = new FileOutputStream(localFile);

        byte[] bs = new byte[10240];
        int size;
        while ((size = is.read(bs)) != -1) {
            // 读到多少内容就写多少，而不是直接将字节数组的内容全写完
            os.write(bs, 0, size);
        }
        os.close();
        is.close();
    }
}
