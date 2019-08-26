package org.lc.se.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class FileDemo {


    @Test
    void constructor() throws URISyntaxException {
        // 路径字符串构造器
        File file = new File("c:\\test\\a.txt");
        System.out.println(file.length());
        // 使用符号/代替\
        file = new File("c:/test/a.txt");
        System.out.println(file.length());

        // 父文件路径，子路径构造器
        file = new File("c:\\test", "a.txt");
        System.out.println(file.length());
        file = new File("c:\\test", "b\\a.txt");
        System.out.println(file.length());

        // 父文件对象，子路径构造器
        file = new File(new File("c:\\test"), "a.txt");
        System.out.println(file.length());

        // uri构造器
        file = new File(new URI("file:///c://test/a.txt"));
        System.out.println(file.length());
    }

    /**
     * 文件分隔符
     * windows: \
     * linux: /
     *
     * 路径分隔符
     * windows：;
     * linux: :
     */
    @Test
    void testSystemSeparator() {
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
    }

    /**
     * 路径
     */
    @Test
    void path() throws IOException {
        File file;


        // 项目的绝对路径
        System.out.println(System.getProperty("user.dir"));

        // 按绝对路径构造返回绝对路径，按相对路径构造返回相对路径
        file = new File("/test");
        System.out.println(file.getPath());
        file = new File("c:\\test\\a.txt");
        System.out.println(file.getPath());

        // 绝对路径
        System.out.println(file.getAbsolutePath());

        // 父路径
        System.out.println(file.getParent());

        System.out.println("name: " + file.getName());


        System.out.println(file.getCanonicalPath());
    }

    /**
     * 文件状态：
     * 1. 不存在
     * 2. 存在
     *     (1)是文件
     *     (1)是文件夹
     */
    @Test
    void status() {
        File file;

        file = new File("c:\\test.png");
        System.out.println("存在： " + file.exists());
        System.out.println("是文件： " + file.isFile());
        System.out.println("是文件夹： " + file.isDirectory());

        file = new File("c:\\test");
        System.out.println("存在： " + file.exists());
        System.out.println("是文件： " + file.isFile());
        System.out.println("是文件夹： " + file.isDirectory());

        file = new File("c:\\test\\a.txt");
        System.out.println("存在： " + file.exists());
        System.out.println("是文件： " + file.isFile());
        System.out.println("是文件夹： " + file.isDirectory());
    }

    @Test
    void length() {
        File file;

        // 文件字节数
        file = new File("c:/test/a.txt");
        System.out.println(file.length());

        // 不存在的文件长度为0
        file = new File("c:/a.txt");
        System.out.println(file.length());

        // 文件夹长度为0
        file = new File("c:/test");
        System.out.println(file.length());
    }

    @Test
    void create() throws IOException {
        File file;

        // 创建文件，存在则创建失败
        file = new File("c:/test/a.txt");
        System.out.println(file.createNewFile());

        // 不存在，创建成功
        file = new File("c:/test/b.txt");
        System.out.println(file.createNewFile());

        // 文件夹创建，存在则创建失败
        file = new File("c:/test/b");
        System.out.println(file.mkdir());

        // 不存在则创建成功
        file = new File("c:/test/c");
        System.out.println(file.mkdir());

        file = new File("c:/test/d/a");
        // 上级目录不存在则创建失败
        System.out.println(file.mkdir());
        // 上级目录不存在则创建上级目录
        System.out.println(file.mkdirs());
    }

    @Test
    void delete() {
        File file;

        // 文件不存在删除失败，返回false
        file = new File("c:/test/d.txt");
        System.out.println(file.delete());

        // 文件夹有下级目录则删除失败
        file = new File("c:/test/d");
        System.out.println(file.delete());

        // 删除成功返回true
        file = new File("c:/test/d/a");
        System.out.println(file.delete());

        // 存在则删除，不存在不做处理
        file = new File("c:/test/d.txt");
        file.deleteOnExit();
    }


    /**
     * 根据路径名创建文件
     * File(String pathname)
     */
    @Test
    void createFileWithPath() throws IOException {
        // 需要文件夹存在，否则java.io.IOException: 系统找不到指定的路径。
//        String pathAndName = "c:\\test\\new";
        String pathAndName = "c:\\test.txt";
        File myFile = new File(pathAndName);
        // 真正创建文件
        boolean result = myFile.createNewFile();
        System.out.println(result);
    }

    /**
     * 根据父文件夹路径名和自身文件名来创建文件
     * File(String parent, String child)
     */
    @Test
    void createByParentPathAndFileName() throws IOException {
        String parentPath = "c:\\";
        // 问题同上，需要文件夹存在
//        String parentPath = "c:\\test";
        String fileName = "test.txt";
        File myFile = new File(parentPath, fileName);
        boolean result = myFile.createNewFile();
        System.out.println(result);
    }

    /**
     * File(File parent, String child)
     */
    @Test
    void createFileWithPathAndName() {
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
     *
     * @see URI
     * @see java.net.URL
     *
     */
    @Test
    void createByURI() throws URISyntaxException, IOException {
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

    @Test
    void operateFile() throws IOException {
        // 创建成功
        File f1 = new File("/1.txt");
        System.out.println(f1.createNewFile());

        // 如果test文件夹不存在就创建失败抛出异常
        File f2 = new File("/test/2.txt");
        System.out.println(f2.createNewFile());

        // 创建目录
        File f3 = new File("/3");
        System.out.println(f3.mkdir());

        // 如果文件夹4不存在，则创建失败但没有异常
        File f4 = new File("/4/a");
        System.out.println(f4.mkdir());

        // 如果文件夹5不存在则创建
        File f5 = new File("/5/a");
        System.out.println(f5.mkdirs());
    }



    /**
     * 创建文件
     */
    @Test
    void testMethod() {
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

    @Test
    void testFileLength() {
        File file = new File("d://test.txt");
        System.out.println(file.length());
    }

    @Test
    void fileNameFilter() {
        File root = new File("/");
        File[] files = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("i");
            }
        });

        System.out.println(Arrays.toString(files));
    }
}
