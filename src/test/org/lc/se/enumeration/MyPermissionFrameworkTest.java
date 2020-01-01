package org.lc.se.enumeration;

import org.junit.jupiter.api.Test;

/**
 * @author lc
 */
public class MyPermissionFrameworkTest {

    /**
     * 常量相关的方法
     */
    @Test
    void testInstanceMethod() {
        MyPermissionFramework[] values = MyPermissionFramework.values();
        for (MyPermissionFramework f : values) {
            System.out.println(f.getInfo());
        }
    }
}