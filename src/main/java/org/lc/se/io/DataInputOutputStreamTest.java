package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.*;

class DataInputOutputStreamTest {

    @Test
    void read1() {
        String srcPath = "file/src/e.txt";

        try (
                InputStream is = new FileInputStream(srcPath);
                DataInputStream dis = new DataInputStream(is)
        ) {
            System.out.println(dis.readUTF());
            System.out.println(dis.readBoolean());
            System.out.println(dis.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void write1() {
        String srcPath = "file/src/e.txt";
        DataOutputStream dos = null;
        OutputStream os = null;

        try {
            os = new FileOutputStream(srcPath);
            dos = new DataOutputStream(os);
            dos.writeUTF("测试");
            dos.writeBoolean(true);
            dos.writeDouble(1232.456);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
