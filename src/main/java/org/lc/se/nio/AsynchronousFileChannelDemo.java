package org.lc.se.nio;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousFileChannelDemo {

    private static final String FILE_PATH = "/test.txt";

    public static void main(String[] args) {
//        readFromFileWithFuture();
        readFromFileWithCompletionHandler();
    }

    /**
     * 通过Future从文件中读取
     */
    private static void readFromFileWithFuture() {
        Path path = Paths.get(FILE_PATH);
        AsynchronousFileChannel afc = null;
        try {
            afc = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer bb = ByteBuffer.allocate(200);
            Future<Integer> operation = afc.read(bb, 0);
            while (!operation.isDone()) {
                System.out.println("waiting...");
            }
            bb.flip();
            byte[] data = new byte[bb.limit()];
            bb.get(data);
            System.out.println(new String(data));
            bb.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (afc != null) {
                try {
                    afc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void readFromFileWithCompletionHandler() {
        Path path = Paths.get(FILE_PATH);
        AsynchronousFileChannel afc = null;
        try {
            afc = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer bb = ByteBuffer.allocate(200);
            ByteBuffer bb2 = ByteBuffer.allocate(200);
            afc.read(bb, 0, bb2, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("completed method...");
                    System.out.println("has read " + result + " bytes");
                    attachment.flip();
                    byte[] data = new byte[attachment.limit()];
                    attachment.get(data);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println("failed method...");
                }
            });
            System.out.println(bb.position());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (afc != null) {
                try {
                    afc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void writeIntoFileWithFuture() {

    }

    private static void writeIntoFileWithCompletionHandler() {

    }
}
