package org.lc.se.enumeration;

import java.util.Random;

/**
 * 实现了接口的枚举
 * @author lc
 */

public enum MyNumberEnum implements MyEnumInterface<MyNumberEnum> {
    /**
     * 1
     */
    ONE,
    /**
     * 2
     */
    TWO,
    /**
     * 3
     */
    THREE,
    /**
     * 4
     */
    FOUR,
    /**
     * 5
     */
    FIVE,
    /**
     * 6
     */
    SIX,
    /**
     * 7
     */
    SEVEN,
    /**
     * 8
     */
    EIGHT,
    /**
     * 9
     */
    NINE,
    /**
     * 10
     */
    TEN;

    Random random = new Random();

    @Override
    public MyEnumInterface<MyNumberEnum> next() {
        return values()[random.nextInt(9)];
    }
}
