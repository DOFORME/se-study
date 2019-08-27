package org.lc.se.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;

public class PipedStreamTest {
    private static PipedInputStream is = new PipedInputStream();
    private static PipedOutputStream os = new PipedOutputStream();

    public static void main(String[] args) {
        try {
            // 关联管道流
            is.connect(os);
        } catch (IOException e) {
            System.out.println("connect failed...");
            e.printStackTrace();
        }
        send();
        receive();
    }


    private static void send() {
        new Thread(() -> {
            String s = "this is a string for test";
            byte[] buffer = s.getBytes();
            try {
                os.write(buffer, 0, buffer.length);
                System.out.println("write complete...");
            } catch (IOException e) {
                System.out.println("write with exception...");
                e.printStackTrace();
            }
        }).start();
    }

    private static void receive() {
        new Thread(() -> {
            byte[] buffer = new byte[1];
            int length;
            try {
                while ((length = is.read(buffer, 0, buffer.length)) != -1) {
                    System.out.println("has read" + length + "bytes");
                    System.out.println(Arrays.toString(buffer));
                }
            } catch (IOException e) {
                System.out.println("read failed...");
                e.printStackTrace();
            }
        }).start();
    }
}

