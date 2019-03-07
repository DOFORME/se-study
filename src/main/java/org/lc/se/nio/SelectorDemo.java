package org.lc.se.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * 选择器
 * 与Selector一起使用时，Channel必须处于非阻塞模式下。
 * 这意味着不能将FileChannel与Selector一起使用，
 * 因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
 */
public class SelectorDemo {

    public static void main(String[] args) {
        createSelector();
    }

    private static void createSelector() {
        try {
            Selector selector = Selector.open();
            DatagramChannel channel1 = createReceiveChannel();
            SelectionKey key1 = channel1.register(selector, SelectionKey.OP_READ);
            System.out.println(key1.interestOps() & SelectionKey.OP_READ);

            // 判断该channel的兴趣集合中是否有read
            boolean isInterestedRead = (key1.interestOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ;
            System.out.println(isInterestedRead);
            System.out.println(key1.isReadable());

            key1.attach("key1");


            while (selector.select(1000) == 0) {
                System.out.println("nothing received...");
            }
            System.out.println(key1.isReadable());
            ByteBuffer bb = ByteBuffer.allocate(100);
            Channel c = key1.channel();
            if (c instanceof DatagramChannel) {
                System.out.println("is DatagramChannel's instance");
                System.out.println(c == channel1);
                ((DatagramChannel) c).receive(bb);
            }
            bb.flip();
            System.out.print("received:");
            while (bb.hasRemaining()) {
                System.out.print((char) bb.get());
            }

            selector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DatagramChannel createReceiveChannel() throws IOException {
        DatagramChannel dc = DatagramChannel.open();
        dc.bind(new InetSocketAddress(9999));
        dc.configureBlocking(false);
        return dc;
    }
}
