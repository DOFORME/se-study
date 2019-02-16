package org.lc.se.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FileCreateTest {

    public static void main(String[] args) throws IOException, URISyntaxException {
//        createFileWithPathAndName();

//        createFileWithPath();

        createByParentPathAndFileName();

//        createByURI();
    }

    private static void createFileWithPathAndName() throws IOException {
        File parent = new File("c:\\");
        String fileName = "test";
        File myFile = new File(parent, fileName);
        boolean result = myFile.mkdir();
        System.out.println(myFile.getPath());
        System.out.println(result);
    }

    private static void createFileWithPath() throws IOException {
        String pathAndName = "c:\\test\\new";
        File myFile = new File(pathAndName);
        boolean result = myFile.createNewFile();
        System.out.println(result);
    }

    private static void createByParentPathAndFileName() throws IOException {
        String parentPath = "c:\\test";
        String fileName = "new_2";
        File myFile = new File(parentPath, fileName);
        boolean result = myFile.createNewFile();
        System.out.println(result);
    }

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
}
