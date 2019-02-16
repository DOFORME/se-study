package org.lc.se.io;

import java.io.*;
import java.nio.CharBuffer;

/**
 * 参考1（https://juejin.im/post/5af79bcc51882542ad771546）
 * 参考2（https://www.yiibai.com/java/io/reader_read_charbuffer.html）
 */
public class InputStreamTest {

    static String path = "/Users/lc/Desktop/test";

    public static void main(String[] args) {
//        readDataFromFileWithReader1();
//        readDataFromFileWithReader2();
//        readDataFromFileWithReader3();
//        readDataFromFileWithReader4();

//        readDataFromFileWithInputStream1();

//        testAvailable();
//        testMark1();
//        testMark2();
        testSkip();
    }

    /**
     * 每次读一个字符，result存储字符在ASCII码表对应的整数值
     */
    static void readDataFromFileWithReader1() {
        String path = "/Users/lc/Desktop/test";
        Reader reader = null;
        try {
             reader = new FileReader(path);
            int result;
            while ((result = reader.read()) != -1) {
                System.out.println();
                System.out.println(result);
                System.out.print((char) result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 正确关闭流的姿势
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 每次将读到的字符存储到缓存数组，每次读取的长度为缓存数组的长度
     */
    static void readDataFromFileWithReader2() {
        String path = "/Users/lc/Desktop/test";
        try {
            Reader reader = new FileReader(path);
            int result;
            char[] buffer = new char[5];
            while ((result = reader.read(buffer)) != -1) {
                System.out.println(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *每次读取的值存入缓存数组，但是不一定是刚好填充数组（具体填充方案由offset和length决定）
     */
    static void readDataFromFileWithReader3() {
        String path = "/Users/lc/Desktop/test";
        Reader reader;
        try {
            reader = new FileReader(path);
            int result;
            char[] buffer = new char[5];
            while ((result = reader.read(buffer, 0, buffer.length)) != -1) {
                System.out.println(buffer);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 使用nio包里的CharBuffer作缓存
     */
    static void readDataFromFileWithReader4() {
        String path = "/Users/lc/Desktop/test";
        try {
            Reader reader = new FileReader(path);
            CharBuffer cb = CharBuffer.allocate(5);
            while (reader.read(cb) != -1) {
                System.out.println(cb.flip());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用字节流读取文本文档时注意编码格式
     * utf-8编码中，一个英文字母占一个字节，一个汉字占三到四个字节
     */
    static void readDataFromFileWithInputStream1() {
        try {
            InputStream is = new FileInputStream(path);
            int result = is.read();
            System.out.println(result);
            System.out.println(System.getProperty("file.encoding"));
            byte[] bytes = new byte[3];
            System.out.println(result = is.read());
            bytes[0] = (byte) result;
            System.out.println(result = is.read());
            bytes[1] = (byte) result;
            System.out.println(result = is.read());
            bytes[2] = (byte) result;
            String s = new String(bytes, "utf-8");
            System.out.println(s);

            System.out.println(is.read());

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取剩余字节数
     */
    static void testAvailable() {
        InputStream is = null;

        try {
            is = new FileInputStream(path);
            while (is.read() != -1) {
                System.out.println("remain " + is.available() + "bytes");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 在输入流中标记当前位置，以便重新读取相同的字节
     * readLimit：需要重读的字节数
     */
    static void testMark1() {
        InputStream is = null;

        try {
            // FileInputStream不支持mark操作
            is = new FileInputStream(path + ".txt");
            System.out.println((char)is.read());
            System.out.println((char)is.read());
            System.out.println((char)is.read());
            System.out.println((char)is.read());
            System.out.println((char)is.read());
            System.out.println((char)is.read());

            // 参考：https://blog.csdn.net/u011494050/article/details/41891817
            is.mark(0);

            System.out.println(is.markSupported());

            System.out.println((char)is.read());

            if (is.markSupported()) {
                is.reset();
                for (int i=0; i<5; i++) {
                    System.out.println((char)is.read());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * readLimit参数是指当读取的字节数超过指定值时，标记可能失效，此时调用reset()方法产生异常
     * 参考：https://blog.csdn.net/u012814377/article/details/17137949
     */
    static void testMark2() {
        InputStream is = null;

        try {
            // FileInputStream不支持mark操作
            is = new FileInputStream(new File(path));
            InputStream is2 = new BufferedInputStream(is);
            for (int i=0; i<5; i++) {
                System.out.println((char)is2.read());
            }

            System.out.println(is2.markSupported());
            is2.mark(2);

            for (int i=0; i<5; i++) {
                System.out.println((char)is2.read());
            }


            if (is2.markSupported()) {
                is2.reset();
                for (int i=0; i<5; i++) {
                    System.out.println((char)is2.read());
                }

                System.out.println(is2.markSupported());

                is2.reset();
                for (int i=0; i<5; i++) {
                    System.out.println((char)is2.read());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 跳过指定个数个字节
     */
    static void testSkip() {
        InputStream is = null;

        try {
            is = new FileInputStream(path);
            int result;
            while ((result = is.read()) != -1) {
                System.out.println((char) result);
                is.skip(2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
