package org.lc.se.io;

import java.io.File;
import java.io.IOException;

public class FileNormalMethodTest {

    public static void main(String[] args) throws IOException {
//        testPathSeparator();

        testPermissions();

//        testSetPermissions();
    }

    private static void testPathSeparator() throws IOException {
        File file = new File("c:\\test");
        System.out.println(file.separator);
        System.out.println(file.pathSeparator);

        File myFile = new File("c:\\test" + File.separator + "separator");
        boolean result = myFile.createNewFile();
        System.out.println(result);
    }

    private static void testPermissions() {
        File file = new File("c:\\test\\new");
        System.out.println("can execute:" + file.canExecute());
        System.out.println("can read:" + file.canRead());
        System.out.println("can write:" + file.canWrite());
        System.out.println("absolute path:" + file.getAbsolutePath());

        File myFile = new File("new3");
        System.out.println("absolute path:" + myFile.getAbsolutePath());
        System.out.println("absolute file:" + myFile.getAbsoluteFile().getName());
        System.out.println("is directory:" + myFile.isDirectory());
        System.out.println("is file:" + myFile.isFile());
        System.out.println("is hidden:" + myFile.isHidden());
    }

    private static void testSetPermissions() throws IOException {
        File file = new File("c:\\test\\separator2");
        System.out.println("can read:" + file.canRead());
        file.setReadable(false);
        System.out.println("can read:" + file.canRead());
        boolean result = file.createNewFile();
        System.out.println("result:" + result);

        System.out.println("can read:" + file.canRead());
        file.setReadable(false);
        System.out.println("can read:" + file.canRead());

        System.out.println("can write:" + file.canWrite());
        file.setWritable(false);
        System.out.println("can write:" + file.canWrite());

        System.out.println("relative path:" + file.getName());

        boolean delete = file.delete();
        System.out.println("delete:" + delete);
    }
}
