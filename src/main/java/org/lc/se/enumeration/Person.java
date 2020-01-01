package org.lc.se.enumeration;

/**
 * @author lc
 */
public class Person {

    enum Ready {
        /**
         * 准备好了
         */
        YES,
        /**
         * 没准备好
         */
        NO
    }

    enum Clothes {
        /**
         * 没衣服
         */
        NOTHING,
        /**
         * 有一些
         */
        SOME
    }

    enum Water {
        /**
         * 烫了
         */
        HOT,
        /**
         * 冷
         */
        COLD,
        /**
         * 刚好
         */
        SUITABLE
    }

    private Ready ready;
    private Clothes clothes;
    private Water water;

    public Ready getReady() {
        return ready;
    }

    public void setReady(Ready ready) {
        this.ready = ready;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }
}
