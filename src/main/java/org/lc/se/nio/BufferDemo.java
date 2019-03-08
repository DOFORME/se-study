package org.lc.se.nio;

import java.nio.ByteBuffer;

public class BufferDemo {

    public static void main(String[] args) {
        judgeModelWithLimit();
    }

    /**
     * 通过Buffer的limit方法来判断当前Buffer处于读还是写模式
     */
    private static void judgeModelWithLimit() {
        ByteBuffer bb = ByteBuffer.allocate(5);
        System.out.println(bb.position());
        System.out.println(bb.limit());
        bb.put((byte) 12);
        System.out.println(bb.position());
        System.out.println(bb.limit());
        bb.put((byte) 12);
        System.out.println(bb.position());
        System.out.println(bb.limit());
        bb.put((byte) 12);
        System.out.println(bb.position());
        System.out.println(bb.limit());

        System.out.println("*-******************");
        bb.flip();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        bb.get();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println("---------------------");
        bb.flip();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        bb.put((byte) 12);
        System.out.println(bb.position());
        System.out.println(bb.limit());
    }
}
