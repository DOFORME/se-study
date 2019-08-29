package org.lc.se.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 1.nio同步阻塞模式（即没有selector）
 * 2.nio同步非阻塞模式
 */
public class SocketChannelTest {

}

class Client1 {
    public static void main(String[] args) {
        SocketChannel sc = null;
        try {
            // 打开通道，绑定主机
            sc = SocketChannel.open(new InetSocketAddress(9999));
            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 接收控制台输入
            Scanner scanner = new Scanner(System.in);
            String msg;
            // 输入轮询
            while (scanner.hasNext()) {
                msg = scanner.next();
                if (msg.equals("exit")) {
                    sc.close();
                }
                buffer.put(msg.getBytes());
                buffer.flip();
                // 将缓冲区的数据写出
                sc.write(buffer);
                System.out.println("发送完成");
                buffer.clear();
            }
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

class Server1 {
    public static void main(String[] args) {
        SocketChannel sc = null;
        ServerSocketChannel ssc = null;
        try {
            // 开启服务端通道，并绑定端口
            ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(9999));

            // 等待客户端连接（此时阻塞）
            System.out.println("等待连接");
            sc = ssc.accept();
            System.out.println("连接完成");

            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String msg;

            // 将客户端通道里的数据读到缓冲区
            while (sc.read(buffer) != -1) {
                buffer.flip();
                msg = new String(buffer.array(), 0, buffer.limit());
                System.out.println("收到：" + msg);
                buffer.clear();
            }
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
            if (ssc != null) {
                try {
                    ssc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class client2{
    public static void main(String[] args) {
        SocketChannel sc = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        try {
            // 开启通道绑定地址
            sc = SocketChannel.open(new InetSocketAddress(9999));
            // 关闭阻塞模式
            sc.configureBlocking(false);

            while (scanner.hasNext()) {
                buffer.put(scanner.next().getBytes());
                buffer.flip();
                sc.write(buffer);
                buffer.clear();
            }
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
class Server2{
    public static void main(String[] args) {
        ServerSocketChannel ssc = null;
        SocketChannel sc = null;
        Selector selector = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String msg;
        try {
            // 打开通道绑定端口监听TCP连接
            ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(9999));
            // 配置为非阻塞的通道
            ssc.configureBlocking(false);

            // 打开选择器
            selector = Selector.open();

            // 将通道注册到选择器上，并绑定感兴趣的操作
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("可接受操作注册结束");

            // 轮询检查已选择的选择键
            while (selector.select() > 0) {
                // 选择键迭代器
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                // 遍历集合，对相应时间进行处理
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        System.out.println("可接受准备就绪");
                        sc = ssc.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()) {
                        if (sc == null) {
                            throw new RuntimeException("非法可读状态");
                        }
                        System.out.println("可读准备就绪");
                        // 非阻塞模式这里不能用(!= -1)来判断
                        while (sc.read(buffer) > 0) {
                            msg = new String(buffer.array(), 0, buffer.limit());
                            System.out.println("接收到：" + msg);
                            buffer.clear();
                        }
                        System.out.println("本次读完");

//                        buffer.put("我收到了".getBytes());
//                        buffer.flip();
//                        sc.write(buffer);
//                        buffer.clear();
                    }

                    // 这里需要注意集合或迭代器移除元素的问题（https://blog.csdn.net/github_2011/article/details/54927531）
                    iterator.remove();
                }
            }
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

            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ssc != null) {
                try {
                    ssc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
