package org.lc.se.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * http://ifeve.com/buffers/
 */
public class ChannelDemo {

    private static final String PATH = "/test.txt";

    public static void main(String[] args) {
        testFileChannel();
    }

    private static void testFileChannel() {
        try {
            RandomAccessFile raf = new RandomAccessFile(PATH, "rw");
            FileChannel fc = raf.getChannel();
            ByteBuffer bb = ByteBuffer.allocate(100);
            int length;
            while ((length = fc.read(bb)) != -1) {
                System.out.println("has read:" + length);
                System.out.println("limit:" + bb.limit());
                System.out.println("position:" + bb.position());
                System.out.println("capacity:" + bb.capacity());
                // 缓冲区从写模式切换到读模式
                bb.flip();
                System.out.println("limit:" + bb.limit());
                System.out.println("position:" + bb.position());
                System.out.println("capacity:" + bb.capacity());
                while (bb.hasRemaining()) {
                    System.out.println((char) bb.get());
                }
                // 清空缓冲区已读数据
                bb.compact();
                // 清空缓冲区所有数据
//                bb.clear();
            }
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
