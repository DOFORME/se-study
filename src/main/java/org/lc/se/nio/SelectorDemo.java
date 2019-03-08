package org.lc.se.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

/**
 * 选择器
 * http://ifeve.com/selectors/
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
            // 将channel注册到selector上，并选择兴趣集
            SelectionKey key1 = channel1.register(selector, SelectionKey.OP_READ);

            // 判断该channel的兴趣集合中是否有read
            boolean isInterestedRead = (key1.interestOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ;
            System.out.println(key1.interestOps() & SelectionKey.OP_READ);
            System.out.println(isInterestedRead);
            System.out.println(key1.isReadable());

            // 给SelectionKey添加附加对象
            key1.attach("key1");

            // 阻塞到注册的通道上（至少有一个兴趣就绪）
            while (selector.select(1000) == 0) {
                System.out.println("nothing received...");
            }

            // 判断某项兴趣是否就绪
            System.out.println((key1.readyOps() & SelectionKey.OP_READ) != 0);
            System.out.println((key1.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ);
            System.out.println((key1.readyOps() & SelectionKey.OP_WRITE) != 0);
            System.out.println(key1.isReadable());
            System.out.println(key1.isWritable());

            // 已选择的键集
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            for (SelectionKey sk : selectedKeys) {
                // 附加对象
                Object o = sk.attachment();
                System.out.println(o);
            }


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
        // 配置为非阻塞模式
        dc.configureBlocking(false);
        return dc;
    }

}
