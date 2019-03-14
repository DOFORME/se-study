package org.lc.se.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {
    private static final String MSG = "this is server response data...";

    public static void main(String[] args) throws Exception {
        createServerSocket();
//        showNormalMethod();
//        showNormalMethod2();
    }

    private static void createServerSocket() throws IOException {
        ServerSocket ss = new ServerSocket(9999);
        Socket socket = ss.accept();
        DataInputStream in = new DataInputStream(socket.getInputStream());
        System.out.println(in.readUTF());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(MSG);

        in.close();
        out.close();
        socket.close();
    }

    private static void showNormalMethod() throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        System.out.println(ss.getLocalPort());
    }

    private static void showNormalMethod2() throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(8888));
        System.out.println(ss.getLocalPort());
    }
}
