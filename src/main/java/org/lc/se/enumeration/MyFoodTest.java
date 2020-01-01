package org.lc.se.enumeration;

import org.junit.jupiter.api.Test;

import static org.lc.se.enumeration.MyFood.*;

/**
 * @author lc
 */
public class MyFoodTest {

    /**
     * 通过接口拓展了枚举
     */
    @Test
    void testExpand () {
        MyFood food1 = Vegetable.TOMATO;
        MyFood food2 = Fruit.APPLE;
        MyFood food3 = Meat.BEEF;
        System.out.println(food1);
        System.out.println(food2);
        System.out.println(food3);
    }
}
