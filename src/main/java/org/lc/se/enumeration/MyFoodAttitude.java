package org.lc.se.enumeration;

/**
 * @author lc
 */

public enum MyFoodAttitude {

    /**
     * 喜欢
     */
    LOVE(MyFood.Meat.class),
    /**
     * 不喜欢
     */
    HATE(MyFood.Vegetable.class),
    /**
     * 不关心
     */
    NOT_CARE(MyFood.Fruit.class);

    private MyFood[] foods;

    MyFoodAttitude(Class<? extends MyFood> clz) {
        this.foods = clz.getEnumConstants();
    }

    static MyFood[] getFoods(MyFoodAttitude love) {
        return love.foods;
    }
}
