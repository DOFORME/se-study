package org.lc.se.enumeration;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 测试无参枚举
 *
 * @author lc
 */
public class MyColorEnumTest {

    /**
     * 打印的是声明的枚举名
     */
    @Test
    void testPrint() {
        System.out.println(MyColorEnum.RED);
        System.out.println(MyColorEnum.ORANGE);
        System.out.println(MyColorEnum.YELLOW);
        System.out.println(MyColorEnum.GREEN);
        System.out.println(MyColorEnum.CYAN);
        System.out.println(MyColorEnum.BLUE);
        System.out.println(MyColorEnum.PURPLE);

        // toString()没有重写时，这两个效果一样，返回实例声明的名字
        System.out.println(MyColorEnum.RED.name());
        System.out.println(MyColorEnum.RED.toString());
    }

    /**
     * 遍历值
     * values()是编译器加的静态方法，并不是来自Enum类，此外还有valueOf()，与Enum的valueOf()不同
     */
    @Test
    void testValues() {
        for (MyColorEnum e : MyColorEnum.values()) {
            System.out.println(e);
        }

        // 反射方式获取
        Class<MyColorEnum> clz = MyColorEnum.class;
        MyColorEnum[] enumConstants = clz.getEnumConstants();
        System.out.println(Arrays.toString(enumConstants));

        // 当枚举通过上转型后无法使用values时，依旧可以通过类来获取
        Enum<MyColorEnum> e = MyColorEnum.RED;
        System.out.println(Arrays.toString(e.getClass().getEnumConstants()));
    }

    /**
     * 父类是Enum类
     */
    @Test
    void testParentClass() {
        Class<MyColorEnum> clz = MyColorEnum.class;
        String parentSimpleName = clz.getSuperclass().getSimpleName();
        System.out.println(parentSimpleName);
    }

    /**
     * 查看一个枚举实例所属类
     */
    @Test
    void testEnumClass() {
        Class<MyColorEnum> clz = MyColorEnum.RED.getDeclaringClass();
        // class org.lc.se.enumeration.MyColorEnum
        System.out.println(clz);
    }

    @Test
    void testEnumStructure() {
        Class<MyColorEnum> clz = MyColorEnum.class;
        Field[] fields = clz.getFields();
        System.out.println(Arrays.toString(fields));
    }

    /**
     * 通过反射获取构造器
     * 枚举类没有public构造器
     *
     * Constructor<MyColorEnum> c1 = clz.getConstructor();
     * Constructor<?>[] constructors = clz.getConstructors();
     * 这样是获取不到的
     */
    @Test
    void testConstructor() {
        Class<MyColorEnum> clz = MyColorEnum.class;


        Constructor<?>[] declaredConstructors = clz.getDeclaredConstructors();
        System.out.println(Arrays.toString(declaredConstructors));


        Constructor<MyColorEnum> c2 = null;
        try {
            c2 = clz.getDeclaredConstructor(String.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(c2);

        Constructor<MyColorEnum> c3 = (Constructor<MyColorEnum>) declaredConstructors[0];
        System.out.println(c3);

    }

    /**
     * 实例声明的次序，从0开始计数
     */
    @Test
    void testOrdinal() {
        System.out.println(MyColorEnum.RED.ordinal());
        System.out.println(MyColorEnum.ORANGE.ordinal());
    }

    /**
     * 通过反射查看枚举类里面有哪些结构
     */
    @Test
    void testStructure() {
        Class<MyColorEnum> color = MyColorEnum.class;
        Package pack = color.getPackage();
        System.out.println(pack.getName());

        Class<?>[] classes = color.getClasses();
        System.out.println(Arrays.toString(classes));
    }
}
