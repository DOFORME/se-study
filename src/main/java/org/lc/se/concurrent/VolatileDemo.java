package org.lc.se.concurrent;

import org.junit.Test;

/**
 * @author lc
 */
public class VolatileDemo {

    @Test
    public void t1() {
        Data data = new Data();
        Thread t = new Thread(data::add);
        t.start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a" + data.count);
    }

}

class Data extends Thread {
    int count = 0;

    synchronized void add() {
        System.out.println(count);
        count++;
        try {
            System.out.println("wait begin");
            wait();
            System.out.println("wait over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t" + count);
    }
}