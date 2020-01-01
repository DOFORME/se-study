package org.lc.se.enumeration;

import org.junit.jupiter.api.Test;

/**
 * @author lc
 */
public class MyNumberEnumTest {

    @Test
    void testInterfaceMethod() {
        // 直接用枚举实例调用方法
        MyEnumInterface<MyNumberEnum> next = MyNumberEnum.ONE.next();
        System.out.println(next);
    }
}
