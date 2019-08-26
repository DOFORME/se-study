package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

public class CharsetDemo {

    /**
     * 查看系统默认字符集
     */
    @Test
    void seeDefaultCharset() {
        System.out.println(System.getProperty("file.encoding"));

        String s = "测试字符串";

        byte[] b1 = s.getBytes(Charset.forName("gbk"));
        System.out.println(new String(b1));

        byte[] b2 = s.getBytes(Charset.forName("utf8"));
        System.out.println(new String(b2));
    }

    /**
     * 查看unicode十进制编码
     */
    @Test
    void code1() {
        char c = '严';
        System.out.println((int) c);
    }

    @Test
    void code2() {
        char c = '严';
        System.out.println(Integer.toHexString((int) c));
    }
}
