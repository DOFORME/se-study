package org.lc.se.io;

import java.io.*;
import java.util.Scanner;

public class SystemInputOutputStream {
    private static byte[] BUFFER = new byte[1024];
    private static final String S = "this string for test...";

    public static void main(String[] args) {
//        testSystemIn();
        testScanner();
//        testScannerWithFile();
//        testSystemOut();
//        testSystemOutWithFormatter();
    }

    private static void testSystemIn() {
        System.out.print("input please:");
        InputStream is = System.in;
        try {
            while (is.read(BUFFER) != -1) {
                System.out.print(new String(BUFFER));
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testScanner() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
            if ("n".equalsIgnoreCase(scanner.next())) {
                return;
            }
        }
    }

    private static void testScannerWithFile() {
        try {
//            Scanner scanner = new Scanner(new File("/test.txt"), "gbk");
            // 如果编码不一致则无法读取，需要使用上面的指定编码的方式
            Scanner scanner = new Scanner(new File("/test2.txt"));
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testSystemOut() {
        BUFFER = S.getBytes();
        OutputStream os = System.out;
        PrintStream ps = System.out;
        try {
            os.write(BUFFER);
            System.out.println();

            ps.println(new String(BUFFER));
            ps.write(BUFFER);
            os.close();
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testSystemOutWithFormatter() {
        String msg = "test";
        int i = 29;
        char c = 'c';
        PrintStream ps = System.out;
        ps.printf("字符串：%s，数字：%d，字符：%s", msg, i, c);
    }
}
