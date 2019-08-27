package org.lc.se.nio;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;

/**
 * 在nio中，channel负责连接，buffer复制存取数据（数组）
 * 8种基本数据类型中除了boolean，其他都有对应的buffer
 *
 * 通过allocate()获取缓冲区
 *
 * 存：put()
 * 取：get()
 *
 * Buffer四个核心属性
 * capacity：容量，一但声明不能改变
 * limit：界限，缓冲区中可以操作的数据大小，后面的数据不能读写
 * position：位置，表示缓冲区中正在操作数据的位置，必须满足
 *
 * mark：标记，记录position的位置，可以通过reset()回到mark的位置 mark <= position <= limit <= capacity
 *
 * flip()：切换到读模式
 *
 * rewind()：倒带，可重复读
 *
 * clear()：清空缓冲区，回到最初的状态（里面存放的数据还在，处于被遗忘的数据），可以重新写数据
 *
 * hasRemaining()：判断是否还有剩余
 */
public class BufferTest {

    @Test
    void allocate() {
        // 指定大小的缓冲区
        ByteBuffer bb = ByteBuffer.allocate(1024);

        System.out.println(bb.capacity());
        System.out.println(bb.limit());
        System.out.println(bb.position());
    }

    public static void main(String[] args) {
//        judgeModelWithLimit();
//        transferToDoubleBuffer();
//        transferToCharBuffer();
//        storeWithDefaultEndian();
//        testFlip();
//        testFlip2();
//        testClear();
//        testLimit();
//        testLimit2();
        testReadAndWrite();
    }

    /**
     * 通过Buffer的limit方法来判断当前Buffer处于读还是写模式
     */
    private static void judgeModelWithLimit() {
    }

    private static void transferToDoubleBuffer() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
        DoubleBuffer db = bb.asDoubleBuffer();
        while (db.hasRemaining()) {
            System.out.println(db.get());
        }
    }

    private static void transferToCharBuffer() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{0, 97});
        CharBuffer cb = bb.asCharBuffer();
        System.out.println(cb.get());
    }

    private static void storeWithDefaultEndian() {
        ByteBuffer bb = ByteBuffer.allocate(2);
        operateBuffer(bb);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        operateBuffer(bb);
        bb.order(ByteOrder.BIG_ENDIAN);
        operateBuffer(bb);
    }

    private static void operateBuffer(ByteBuffer bb) {
        bb.asCharBuffer().put('a');
        System.out.println(bb.position());
        while (bb.hasRemaining()) {
            System.out.print(bb.get());
        }
        System.out.println();
        bb.clear();
    }

    private static void storeWithLittleEndian() {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put('a');
        while (bb.hasRemaining()) {
            System.out.print(bb.get());
        }
    }

    /**
     * flip()的作用是将limit设为position的值，position的值设为0
     * 通过调用此方法，缓冲区从写模式切换到读模式
     */
    private static void testFlip() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{5, 2, 3});
        System.out.println(bb.position());
        System.out.println(bb.get());
        System.out.println(bb.position());
        bb.flip();
        System.out.println(bb.position());
        System.out.println(bb.get());
    }

    /**
     * 没有读数据就直接flip()，导致limit为0，不能继续读或写
     */
    private static void testFlip2() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{4, 5, 6});
        System.out.println(bb.position());
        bb.flip();
        System.out.println(bb.position());
        System.out.println(bb.limit());
    }

    /**
     * 设置position的值为0，limit的值位capacity。
     * 此方法可以用来覆写缓冲区
     */
    private static void testClear() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{2, 3, 4});
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        bb.get();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        bb.clear();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
    }

    /**
     * 写模式下limit()的值一直为capacity()
     */
    private static void testLimit() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{1, 2, 3});
        System.out.println(bb.limit());
        System.out.println("no1:" + bb.get());
        System.out.println(bb.limit());
    }

    /**
     * 读模式下limit()的值一直为实际数据长度
     */
    private static void testLimit2() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{1, 2, 3});
        System.out.println(bb.limit());
        ByteBuffer bb2 = ByteBuffer.allocate(3);
        System.out.println(bb2.limit());
        bb2.put((byte) 2);
        bb2.put((byte) 3);
        bb2.put((byte) 4);
        System.out.println(bb2.position());
        System.out.println(bb2.limit());
        bb2.flip();
        bb2.get();
        System.out.println(bb2.position());
        bb2.get();
        System.out.println(bb2.position());
        System.out.println(bb2.limit());
    }

    /**
     * ByteBuffer可读的同时也可写
     */
    private static void testReadAndWrite() {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.put((byte) 1);
        bb.put((byte) 2);
        // 倒带
        bb.rewind();
        // 打印第一个数据
        System.out.println(bb.get());
        // 覆盖第二个数据
        bb.put((byte) 4);
        bb.rewind();
        System.out.println(bb.get());
        System.out.println(bb.get());
    }
}
