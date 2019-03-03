package org.lc.se.io;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class FileDemo {

    public static void main(String[] args) throws IOException, URISyntaxException {
//        createFileWithPathAndName();

//        createFileWithPath();

//        createByParentPathAndFileName();

//        createByURI();

//        testSystemSeparator();

//        testMethod();

        testFileLength();

        File file = new File("/");
        System.out.println(file.getAbsolutePath());
    }

    /**
     * File(String pathname)
     */
    private static void createFileWithPath() throws IOException {
        String pathAndName = "c:\\test\\new";
        File myFile = new File(pathAndName);
        boolean result = myFile.createNewFile();
        System.out.println(result);
    }

    /**
     * 根据父文件夹路径名和自身文件名来创建文件
     * File(String parent, String child)
     */
    private static void createByParentPathAndFileName() throws IOException {
        String parentPath = "c:\\";
        String fileName = "new_2";
        File myFile = new File(parentPath, fileName);
        boolean result = myFile.createNewFile();
        System.out.println(result);
    }

    /**
     * File(File parent, String child)
     */
    private static void createFileWithPathAndName() throws IOException {
        File parent = new File("c:\\");
        String fileName = "test";
        File myFile = new File(parent, fileName);
        boolean result = myFile.mkdir();
        System.out.println(myFile.getPath());
        System.out.println(result);
    }

    /**
     * 通过前缀file:和uri来在指定位置创建文件
     * File(URI uri)
     */
    private static void createByURI() throws URISyntaxException, IOException {
        URI uri = new URI("file:///c:/test/new");
        File myFile = new File(uri);
        System.out.println(uri.getPath());
        System.out.println(myFile.getPath());
        boolean result = myFile.delete();
        System.out.println(result);
        result = myFile.createNewFile();
        System.out.println(result);
        System.out.println(myFile.getPath());
    }

    /**
     * 文件分隔符
     * windows: \
     * linux: /
     */
    private static void testSystemSeparator() {
        System.out.println(File.separator);
        // 系统路径分隔符（windows:';'）
        System.out.println(File.pathSeparator);
    }

    /**
     * 创建文件
     */
    private static void testMethod() {
        // 如果只有这一行，文件不会被创建，需要明确创建(file.createNewFile())或者写入内容
        // 在项目根目录创建文件
        // "d:"+File.separator
        File file3 = new File("a.txt");
        // d盘根目录
        File file = new File("d:\\a.txt");
        File file4 = new File("d://a.txt");

        System.out.println(file3.delete());
        System.out.println(file4.delete());

        if (file.exists()) {
            System.out.println("if exists then delete..." + file.delete());
        }
        File file2 = new File("d:\\a.txt\\a.txt");
        // 判断文件是否可以通过抽象名称执行
        System.out.println("can execute:" + file.canExecute());
        System.out.println("can execute:" + file2.canExecute());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println("delete result:" + file.delete());

        try {
            boolean result = file.createNewFile();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("test......");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(file.canWrite());
        System.out.println("set read only..." + file.setReadOnly());
        System.out.println(file.canWrite());

        System.out.println(file.canExecute());
        System.out.println("set executable to false..." + file.setExecutable(false));
        System.out.println(file.canExecute());
        System.out.println("delete result:" + file.delete());


        File root = new File("D:\\");
        String[] filesAndDirectory = root.list();
        File[] files = root.listFiles();
        if (filesAndDirectory != null) {
            for (String s : filesAndDirectory) {
                // 打印的是文件名而不是路径
                System.out.println(s);
            }
        }
        System.out.println("***********");
        if (files != null) {
            for (File f : files) {
                System.out.println(f.getName());
            }
        }

        File testDir = new File("d:\\test");
        if (testDir.exists()) {
            System.out.println("delete directory:" + testDir.delete());
        }
        System.out.println(testDir.mkdir());
        System.out.println("set writable to false result:" + testDir.setWritable(false));
        System.out.println(testDir.delete());
    }

    private static void testFileLength() {
        File file = new File("d://test.txt");
        System.out.println(file.length());
    }
}
