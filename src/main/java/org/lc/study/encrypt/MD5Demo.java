package org.lc.study.encrypt;

import sun.plugin2.message.Message;

import java.security.MessageDigest;
import java.util.Base64;

public class MD5Demo {

    private final static char[] HEX = "0123456789abcdef".toCharArray();

    private final static String salt = "mySalt";

    public static void main(String[] args) throws Exception {
        String str = "asdfg";
        MessageDigest md = MessageDigest.getInstance("md5");

        byte[] md5Encode = md.digest(str.getBytes());
        byte[] md5Encode2 = md.digest((str + salt).getBytes());

        byte[] bytes = {95,96,97};
        System.err.println(new String(md5Encode));
        System.err.println(new String(bytes));

        // 乱码
        System.out.println(bytes2Hex(md5Encode));

        String encode = Base64.getEncoder().encodeToString(md.digest(str.getBytes()));
        // 正常
        System.out.println(encode);

        System.out.println(bytes2Hex(md5Encode));
        System.out.println(bytes2Hex(md5Encode2));
        System.out.println(bytes2Hex(md.digest("asdfgmySalt".getBytes())) + "/// test");
    }

    private static String bytes2Hex(byte[] bys) {
        char[] chs = new char[bys.length * 2];
        for(int i = 0, offset = 0; i < bys.length; i++) {
            chs[offset++] = HEX[bys[i] >> 4 & 0xf];
            chs[offset++] = HEX[bys[i] & 0xf];
        }
        return new String(chs);
    }

}
