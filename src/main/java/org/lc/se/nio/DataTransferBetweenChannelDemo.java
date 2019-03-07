package org.lc.se.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 数据在channel之间的传输
 */
public class DataTransferBetweenChannelDemo {
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

    private static final String FILE_PATH2 = "/test2.txt";
    private static final File TEST_FILE2 = new File(FILE_PATH2);
    private static RandomAccessFile RANDOM_ACCESS_FILE2;
    static {
        try {
            RANDOM_ACCESS_FILE2 = new RandomAccessFile(TEST_FILE2, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testTransfer();
    }

    /**
     * 从from channel将数据传输到to channel
     */
    private static void testTransfer() {
        FileChannel from = RANDOM_ACCESS_FILE.getChannel();
        FileChannel to = RANDOM_ACCESS_FILE2.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(100);
        ByteBuffer bb2 = ByteBuffer.allocate(100);
        try {
            to.transferFrom(from, 0, from.size());
            to.read(bb2);
            bb2.flip();
            while (bb.hasRemaining()) {
                System.out.print((char) bb.get());
            }
            while (bb2.hasRemaining()) {
                System.out.print((char) bb2.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                from.close();
                to.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
