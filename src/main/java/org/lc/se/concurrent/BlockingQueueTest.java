package org.lc.se.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lc
 * 阻塞队列
 *
 * 操作方法组：
 * 1.错误时报异常
 * add(e),remove(),element()
 * 2.返回特殊值
 * offer(e),poll(),peek()
 * 3.阻塞
 * put(e),take()
 * 4.超时
 * offer(e,time,unit),poll(time,unit)
 */
public class BlockingQueueTest {

    /**
     * 使用add，当队列满了继续加元素时报异常
     */
    @Test
    void add() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        // true
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
        // java.lang.IllegalStateException: Queue full
        System.out.println(queue.add("d"));
    }

    /**
     * 从队列移除一个元素，当队列为空还继续移除时报异常，不为空时返回移除的元素
     */
    @Test
    void remove() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add("a"));
        // a
        System.out.println(queue.remove());
        // java.util.NoSuchElementException
        System.out.println(queue.remove());
    }

    /**
     * 返回队首元素，当队列为空时报异常
     */
    @Test
    void element() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
        // a
        System.out.println(queue.element());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        // c
        System.out.println(queue.element());
        System.out.println(queue.remove());
        // java.util.NoSuchElementException
        System.out.println("finally: " + queue.element());
    }

    /**
     * 添加元素，成功返回true，失败返回false
     */
    @Test
    void offer() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        // true
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        // false
        System.out.println(queue.offer("d"));
    }

    /**
     * 成功返回相应元素，失败返回null
     */
    @Test
    void poll() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        // a
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        // null
        System.out.println(queue.poll());
    }

    /**
     * 成功返回队首元素，失败返回null
     */
    @Test
    void peek() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        // a
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        // null
        System.out.println(queue.peek());
    }
}
