package org.lc.se.util;

import java.util.Random;

/**
 * @author lc
 */
public class RandomDemo {

    public static void main(String[] args) {
        testNextIntWithArgs();
    }

    private static void testNextIntWithArgs() {
        Random random = new Random(99);
        for (int i=0; i<20000; i++) {
            // 生成的随机数范围为[0, 500)
            int value = random.nextInt(500);
            if (value >= 500) {
                System.out.println("value large than 500, value: " + value);
            }
            if (value <= 0) {
                System.out.println("value less than 0, value: " + value);
            }
        }
    }
}
