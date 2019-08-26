package org.lc.se.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author lph
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        ReadWrite rw = new ReadWrite();

        new Thread(() ->
                rw.write(new Random().nextInt())
        ).start();

        for (int i = 0; i < 100; i++) {
            new Thread(rw::read
            ).start();
        }
    }


}

class ReadWrite {
    int count;

    ReadWriteLock lock = new ReentrantReadWriteLock();

    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();



    void read() {
        readLock.lock();
        try {
            System.out.println("read lock get");
            Thread.sleep(1000);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }

    }

    void write(int count) {
        writeLock.lock();
        try {
            System.out.println("write lock get");
            Thread.sleep(2000);
            this.count = count;
            System.out.println("over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}