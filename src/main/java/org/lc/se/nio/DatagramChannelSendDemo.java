package org.lc.se.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * DatagramChannel send
 */
public class DatagramChannelSendDemo {
    private static final String MSG = "this is the string for test...";

    public static void main(String[] args) {
        testCreate();
    }

    private static void testCreate() {
        try {
            DatagramChannel dc = DatagramChannel.open();
            ByteBuffer bb = ByteBuffer.allocate(100);
            bb.put(MSG.getBytes());
            bb.flip();
            int hasSent = dc.send(bb, new InetSocketAddress("127.0.0.1", 9999));
            System.out.println(hasSent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
