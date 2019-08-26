package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintWriterDemo {

    @Test
    void printf() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("/a.txt"));
        pw.println("abc");
        pw.println("efg");
        pw.close();
    }
}
