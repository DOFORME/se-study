package org.lc.se.enumeration;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.lc.se.enumeration.MyWeekEnum.MONDAY;

/**
 *
 *
 * @author lc
 */
public class MyWeekEnumTest {

    @Test
    void testPrint() {
        System.out.println(MyWeekEnum.MONDAY);
        System.out.println(MyWeekEnum.TUSEDAY);
        System.out.println(MyWeekEnum.WEDNESDAY);
        System.out.println(MyWeekEnum.THURSEDAY);
        System.out.println(MyWeekEnum.FRIDAY);
        System.out.println(MyWeekEnum.SATURDAY);
        System.out.println(MyWeekEnum.SUNDAY);

        System.out.println(MyWeekEnum.MONDAY.getDesc());
    }

    /**
     * 获取不到枚举类构造器
     */
    @Test
    void testConstructor() throws NoSuchMethodException {
        Class<MyWeekEnum> clz = MyWeekEnum.class;
        Constructor<?>[] constructors = clz.getConstructors();
        System.out.println(constructors.length);

        Constructor<MyWeekEnum> constructor = clz.getConstructor(String.class, int.class);
        System.out.println(constructor);
    }



    /**
     * 静态导入枚举
     */
    @Test
    void testStaticImport() {
        System.out.println(MONDAY);
    }

    /**
     * 枚举常用场景，用在switch结构的condition expression
     */
    @Test
    void testInSwitch() {
        switch (MyWeekEnum.randomNext()) {
            case MONDAY:
            case TUSEDAY:
            case WEDNESDAY:
            case THURSEDAY:
            case FRIDAY:
                System.out.println("work");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("relax");
                break;
            default:
                System.out.println("unknown");
        }

    }
}
