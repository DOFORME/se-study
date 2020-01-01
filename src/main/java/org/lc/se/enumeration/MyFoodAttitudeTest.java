package org.lc.se.enumeration;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author lc
 */
public class MyFoodAttitudeTest {

    /**
     * 通过创建新的枚举来对旧的枚举分组
     */
    @Test
    void testClassification() {
        MyFood[] love = MyFoodAttitude.getFoods(MyFoodAttitude.LOVE);
        MyFood[] hate = MyFoodAttitude.getFoods(MyFoodAttitude.HATE);
        MyFood[] notCare = MyFoodAttitude.getFoods(MyFoodAttitude.NOT_CARE);
        System.out.println(Arrays.toString(love));
        System.out.println(Arrays.toString(hate));
        System.out.println(Arrays.toString(notCare));
    }

    /**
     * 比HashSet速度快
     */
    @Test
    void testEnumSet() {
        EnumSet<MyFood.Vegetable> set = EnumSet.noneOf(MyFood.Vegetable.class);
        set.add(MyFood.Vegetable.TOMATO);
        for (MyFood.Vegetable v : set) {
            System.out.println(v);
        }
    }

    /**
     * 比HashMap速度快，key为枚举实例，因此内部可以由数组实现，其他与一般的Map一样
     */
    @Test
    void testEnumMap() {
        EnumMap<MyFood.Vegetable, String> map = new EnumMap<>(MyFood.Vegetable.class);
        map.put(MyFood.Vegetable.POTATO, "土豆");
        System.out.println(map);
    }
}
