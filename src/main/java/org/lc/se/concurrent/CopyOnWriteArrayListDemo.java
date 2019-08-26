package org.lc.se.concurrent;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lph
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        testSyncList();
    }

    private static void testSyncList() {
        new Thread(new SyncArrayListDemo()).start();
    }
}

class SyncArrayListDemo implements Runnable {

    //    static List<String> list = Collections.synchronizedList(new ArrayList<>());
//    static List<String> list = new ArrayList<>();
    static List<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("a");
        list.add("b");
        list.add("c");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            list.add("z");
        }
    }
}
