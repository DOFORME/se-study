package org.lc.se.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;

public class BufferDemo {

    public static void main(String[] args) {
//        judgeModelWithLimit();
//        transferToDoubleBuffer();
//        transferToCharBuffer();
        storeWithDefaultEndian();
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
}
