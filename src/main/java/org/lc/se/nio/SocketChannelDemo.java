package org.lc.se.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    private static final String MSG = "msg for socket channel test from client...";

    public static void main(String[] args) {
        createSocketChannel();
    }

    private static void createSocketChannel() {
        SocketChannel sc = null;
        try {
            sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("127.0.0.1", 80));
            ByteBuffer bb = ByteBuffer.allocate(1024);
            bb.get(MSG.getBytes());
            sc.write(bb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
