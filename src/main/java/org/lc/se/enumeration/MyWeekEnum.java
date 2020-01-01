package org.lc.se.enumeration;

import com.sun.istack.internal.NotNull;

import java.util.Random;

/**
 * 星期枚举
 *
 * @author lc
 */
public enum MyWeekEnum {
    /**
     * 星期一
     */
    MONDAY("星期一"),

    /**
     * 星期二
     */
    TUSEDAY("星期二"),

    /**
     * 星期三
     */
    WEDNESDAY("星期三"),

    /**
     * 星期四
     */
    THURSEDAY("星期四"),

    /**
     * 星期五
     */
    FRIDAY("星期五"),

    /**
     * 星期六
     */
    SATURDAY("星期六"),

    /**
     * 星期日
     */
    SUNDAY("星期日");


    private String desc;

    /**
     * 枚举类构造器默认是private，通过编译后的文件可以看到private被补充了
     *
     * @param desc desc
     */
    MyWeekEnum(String desc) {
        this.desc = desc;
    }

    /**
     * 枚举类可以添加方法，但是需要在枚举实例最后加上’;‘号
     */
    public String getDesc() {
        return desc;
    }

    public static MyWeekEnum get(@NotNull int index) {
        for (MyWeekEnum e : MyWeekEnum.values()) {
            if (e.ordinal() == index) {
                return e;
            }
        }
        return null;
    }

    public static MyWeekEnum randomNext() {
        // 产生随机数[0,7)
        int index = new Random().nextInt(7);
        for (MyWeekEnum e : MyWeekEnum.values()) {
            if (e.ordinal() == index) {
                return e;
            }
        }
        throw new RuntimeException();
    }

    /**
     * 枚举类不能派生子类，但是可以有main方法在其内部
     */
    public static void main(String[] args) {
        System.out.println("this is main method");
    }
}
