package org.lc.se.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * DatagramChannel receive
 */
public class DatagramChannelReceiveDemo {

    public static void main(String[] args) {
        testCreate();
    }

    private static void testCreate() {
        try {
            DatagramChannel dc = DatagramChannel.open();
            dc.socket().bind(new InetSocketAddress(9999));
            ByteBuffer bb = ByteBuffer.allocate(20);
            dc.receive(bb);
            bb.flip();
            System.out.println("*********msg begin*********");
            while (bb.hasRemaining()) {
                System.out.print((char) bb.get());
            }
            System.out.println();
            System.out.println("**********msg end**********");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
