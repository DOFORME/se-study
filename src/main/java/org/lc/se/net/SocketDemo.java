package org.lc.se.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketDemo {
    private static final String MSG = "socket data for test...";

    public static void main(String[] args) throws Exception {
        createClientSocket();
    }

    private static void createClientSocket() throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(MSG);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        System.out.println(in.readUTF());
        System.out.println();

        in.close();
        out.close();
        socket.close();
    }
}
