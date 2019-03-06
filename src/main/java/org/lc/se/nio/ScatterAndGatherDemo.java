package org.lc.se.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 分散与聚集
 */
public class ScatterAndGatherDemo {
    private static final String FILE_PATH = "/test.txt";
    private static final File TEST_FILE = new File(FILE_PATH);
    private static RandomAccessFile RANDOM_ACCESS_FILE;

    static {
        try {
            RANDOM_ACCESS_FILE = new RandomAccessFile(TEST_FILE, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final FileChannel FC = RANDOM_ACCESS_FILE.getChannel();
    private static final ByteBuffer BB1 = ByteBuffer.allocate(10);
    private static final ByteBuffer BB2 = ByteBuffer.allocate(20);
    private static final ByteBuffer[] BBS = {BB1, BB2};

    public static void main(String[] args) {
        testScatter();
        testGather();

        try {
            FC.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分散：一个channel里的数据分散到多个buffer里
     */
    private static void testScatter() {
        try {
            BB1.clear();
            BB2.clear();
            FC.read(BBS);
            BB1.flip();
            BB2.flip();
            while (BB1.hasRemaining()) {
                System.out.print((char) BB1.get());
            }
            System.out.println("***********");
            while (BB2.hasRemaining()) {
                System.out.print((char) BB2.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 聚集：将多个buffer里的数据聚集写入channel
     */
    private static void testGather() {
        try {
            BB1.clear();
            BB2.clear();
            for (int i = 65; i < 75; i++) {
                BB1.put((byte) i);
            }
            for (int i = 75; i < 94; i++) {
                BB2.put((byte) i);
            }
            BB1.flip();
            BB2.flip();
            FC.write(BBS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
