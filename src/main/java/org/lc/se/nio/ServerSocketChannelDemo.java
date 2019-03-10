package org.lc.se.nio;

import org.lc.se.constant.CharsetString;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ServerSocketChannelDemo {
    private static final String MSG = "msg for socket channel test...";

    public static void main(String[] args) {
        createServerSocket();
    }

    private static void createServerSocket() {
        SocketChannel sc = null;
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(80));
            sc = ssc.accept();
            System.out.println("receive msg from client...");
            ByteBuffer bb = ByteBuffer.allocate(1024);
            sc.read(bb);
            Charset charset = Charset.forName(CharsetString.UTF8);
            CharBuffer cb = charset.decode(bb);
            cb.flip();
            System.out.println(cb.toString());
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
