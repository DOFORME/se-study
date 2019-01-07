package org.lc.se.encrypt;

import java.util.Base64;

public class Base64Demo {
    public static void main(String[] args) throws Exception {
        String cryptograph = Base64.getEncoder().encodeToString("my str".getBytes());
        System.out.println(cryptograph);

        byte[] myBytes = Base64.getDecoder().decode("bXkgc3Ry");
        System.out.println(new String(myBytes));
    }
}
