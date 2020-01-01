package org.lc.se.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * 获取类的构造器
 */
public class ConstructorTest {

    @Test
    void testGetConstructor() {
        // 获取类
        Class<String> stringClass = String.class;

        // 获取构造器数组
        Constructor<?>[] constructors = stringClass.getConstructors();

        // 打印构造器定义
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
    }

    /**
     * 获取构造器各种信息
     * @throws NoSuchMethodException 没有该方法
     */
    @Test
    void testConstructorMessage() throws NoSuchMethodException {
        Class<String> stringClass = String.class;
        Constructor<String> constructor = stringClass.getConstructor();
        // 修饰符
        System.out.println(Modifier.toString(constructor.getModifiers()));
        // 全名
        System.out.println(constructor.getName());
        // 参数类型
        System.out.println(Arrays.toString(constructor.getParameterTypes()));
        // 参数个数
        System.out.println(constructor.getParameterCount());
    }
}
