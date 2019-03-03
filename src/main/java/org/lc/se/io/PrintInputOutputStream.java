package org.lc.se.io;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintInputOutputStream {



    public static void main(String[] args) {
        writeWithPrintStream();
        writeWithPrintWriter();
    }

    private static void writeWithPrintStream() {
        try {
            PrintStream ps = new PrintStream("d:\\test.txt");
            ps.println("*************");
            ps.println("测试");
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeWithPrintWriter() {
        try {
            PrintWriter pw = new PrintWriter("d:\\test.txt");
            pw.println("测试2");
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
