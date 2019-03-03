package org.lc.se.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayInputOutputStream {

    private static final String s = "this is the test string";

    public static void main(String[] args) {
//        testConstructor();
        testByteArrayOutputStream();
    }

    private static void testConstructor() {
        byte[] bytes = s.getBytes();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        int temp;
        while ((temp = is.read()) != -1) {
            System.out.println((char) temp);
        }
    }

    private static void testByteArrayOutputStream() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            os.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(os.toString());
    }
}
