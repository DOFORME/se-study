package org.lc.se.nio;

/**
 * 主要java.nio.channels.Channel实现
 * FileChannel
 * SocketChannel
 * ServerSocketChannel
 * DatagramChannel
 *
 * 获取通道
 * 1.通过getChannel()方法
 *     本地io：
 *         FileInputStream/FileOutputStream
 *         RandomAccessFile
 *
 *     网络io：
 *         Socket
 *         ServerSocket
 *         DatagramSocket
 *
 * 2.jdk1.7后的nio2提供的静态方法open()
 * 3.jdk1.7后的nio2提供的Files工具类的newByteChannel()
 *
 */
public class ChannelTest {
}
