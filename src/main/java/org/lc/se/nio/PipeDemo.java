package org.lc.se.nio;

import org.lc.se.constant.CharsetString;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.Charset;

public class PipeDemo {
    private static final String MSG = "msg for test pipe channel...";

    public static void main(String[] args) {
        readAndWriteWithPipeChannel();
    }

    private static void readAndWriteWithPipeChannel() {
        Pipe.SinkChannel sinkChannel = null;
        Pipe.SourceChannel sourceChannel =null;
        try {
            Pipe pipe = Pipe.open();
            sinkChannel = pipe.sink();
            sourceChannel = pipe.source();
            ByteBuffer bb = ByteBuffer.allocate(100);
            bb.put(MSG.getBytes());
            bb.flip();
            sinkChannel.write(bb);
            ByteBuffer bb2 = ByteBuffer.allocate(100);
            sourceChannel.read(bb2);
            bb2.flip();
            Charset charset = Charset.forName(CharsetString.UTF8);
            while (bb2.hasRemaining()) {
                CharBuffer cb = charset.decode(bb2);
                System.out.println(cb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sinkChannel != null) {
                    sinkChannel.close();
                }
                if (sourceChannel != null) {
                    sourceChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
