package org.lc.se.nio;

import org.junit.jupiter.api.Test;
import org.lc.se.constant.CharsetString;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * FileChannel无法直接打开
 * Channel是双向的
 * 需要使用InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例
 */
public class FileChannelTest {
    private static final String PATH = "/test.txt";

    public static void main(String[] args) {
        FileChannel channel = createFileChannelWithRandomAccessFile();
        if (channel != null) {
            String msg = readDataFromChannelWithoutGarbled(channel);
            System.out.println(msg);
        }
    }

    /**
     * 通过FileInputStream创建FileChannel
     *
     * @return FileChannel
     */
    private static FileChannel createFileChannelWithFileInputStream() {
        try {
            FileInputStream fis = new FileInputStream(PATH);
            return fis.getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过FileOutputStream创建FileChannel
     *
     * @return FileChannel
     */
    private static FileChannel createFileChannelWithFileOutputStream() {
        try {
            FileOutputStream fos = new FileOutputStream(PATH);
            return fos.getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过RandomAccessFile创建FileChannel
     *
     * @return FileChannel
     */
    private static FileChannel createFileChannelWithRandomAccessFile() {
        try {
            RandomAccessFile raf = new RandomAccessFile(PATH, "rw");
            return raf.getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从channel中读取数据，该方法读取中文无法显示
     *
     * @param channel 被读取的channel
     * @return 内容字符串
     */
    private static String readDataFromChannel(FileChannel channel) {
        try {
            ByteBuffer bb = ByteBuffer.allocate(1024);
            channel.read(bb);
            bb.flip();
            StringBuilder sb = new StringBuilder();
            while (bb.hasRemaining()) {
                sb.append((char) bb.get());
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取含中文内容的文件方法
     *
     * @param channel 被读取的channel
     * @return 内容字符串
     */
    private static String readDataFromChannelWithoutGarbled(FileChannel channel) {
        try {
            ByteBuffer bb = ByteBuffer.allocate(1024);
            channel.read(bb);
            bb.flip();

            Charset charset = Charset.forName(CharsetString.GBK);

            // 解码的第一种方式，直接用Charset的decode方法，CharBuffer不需要切换模式
            CharBuffer cb;
            cb = charset.decode(bb);

            // 解码的第二种方式，通过Charset对象的newDecoder()获取CharsetDecoder对象
            // 然后通过CharsetDecoder对象的decode方法解码
            // CharBuffer需要切换模式
//            CharBuffer cb = CharBuffer.allocate(1024);
//            CharsetDecoder cd = charset.newDecoder();
//            cd.decode(bb, cb, false);
//            cb.flip();

            StringBuilder sb = new StringBuilder();
            while (cb.hasRemaining()) {
                sb.append(cb.get());
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    void copy1() {
        String srcPath = "file/src/d.txt";
        String desPath = "file/des/d-copy.txt";
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            fis = new FileInputStream(srcPath);
            fos = new FileOutputStream(desPath);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
