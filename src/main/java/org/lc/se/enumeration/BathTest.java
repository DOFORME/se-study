package org.lc.se.enumeration;

import org.junit.jupiter.api.Test;

/**
 * @author lc
 */
public class BathTest {

    /**
     * 使用枚举实现简单的责任链
     */
    @Test
    void testTakeBath() {
        Bath bath = new Bath();
        Person person1 = new Person();
        person1.setReady(Person.Ready.YES);
        person1.setClothes(Person.Clothes.SOME);
        person1.setWater(Person.Water.COLD);
        bath.takeBath(person1);

        Person person2 = new Person();
        person2.setReady(Person.Ready.YES);
        person2.setClothes(Person.Clothes.NOTHING);
        person2.setWater(Person.Water.SUITABLE);
        bath.takeBath(person2);
    }

    // todo 状态机 多路分发
}
