package org.lc.se.reflect;

import org.junit.jupiter.api.Test;

/**
 * 获取类的包信息
 */
public class PackageTest {

    @Test
    void testGetPackage() {
        Class<String> stringClass = String.class;
        Package classPackage = stringClass.getPackage();
        // 包信息，如果是自带API包含包名、API类型和java版本
        System.out.println(classPackage);
        // 包名
        System.out.println(classPackage.getName());

        Class<ReflectDemo> demoClass = ReflectDemo.class;
        // 自定义类，只有包名
        System.out.println(demoClass.getPackage());
        // 与上面相比少了package字符串
        System.out.println(demoClass.getPackage().getName());
    }
}
