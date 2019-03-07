package org.lc.se.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * http://ifeve.com/buffers/
 * 四种channel：
 * FileChannel
 * DatagramChannel
 * SocketChannel
 * ServerSocketChannel
 */
public class ChannelAndBufferDemo {

    private static final String PATH = "/test.txt";

    public static void main(String[] args) {
//        testFileChannel();
//        testPut();
//        testReadFromBuffer();
        testRewind();
    }

    /**
     * 读取一个文件
     */
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

    /**
     * 向buffer里添加数据
     */
    private static void testPut() {
        CharBuffer cb = CharBuffer.allocate(10);
        cb.put('z');
        cb.flip();
        while (cb.hasRemaining()) {
            System.out.print(cb.get());
        }
    }

    /**
     * 从buffer中读取数据并写入channel
     */
    private static void testReadFromBuffer() {
        try {
            RandomAccessFile raf = new RandomAccessFile(PATH, "rw");
            FileChannel fc = raf.getChannel();
            ByteBuffer bb = ByteBuffer.allocate(20);
            bb.put((byte) 97);
            bb.flip();
            int length = fc.write(bb);
            System.out.println(length);
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试倒带重读
     */
    private static void testRewind() {
        CharBuffer cb = CharBuffer.allocate(20);
        cb.put('a');
        cb.put('b');
        cb.put('c');
        cb.flip();
        for (int i=0; i<3; i++) {
            System.out.println(cb.get());
            if (i==1) {
                cb.rewind();
            }
        }
    }
}
