package org.lc.se.enumeration;

/**
 * 使用接口来组织枚举
 * 拓展原枚举中的元素或将枚举元素分组
 * @author lc
 */
public interface MyFood {
    enum Vegetable implements MyFood {
        /**
         * 马铃薯
         */
        POTATO,
        /**
         * 西红柿
         */
        TOMATO,
    }

    enum Fruit implements MyFood {
        /**
         * 香蕉
         */
        BANANA,
        /**
         * 苹果
         */
        APPLE
    }

    enum Meat implements MyFood {
        /**
         * 猪肉
         */
        PORK,
        /**
         * 牛肉
         */
        BEEF,
    }
}
