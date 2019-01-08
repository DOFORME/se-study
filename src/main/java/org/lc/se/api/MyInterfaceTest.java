package org.lc.se.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MyInterfaceTest {

    public static void main(String[] args) throws Exception {
        testFieldModifier();

        testInterfaceAbstractStaticDefaultMethod();

        testInterfaceSuperClassConflict();

        testInterfaceSuperClassConflict2();

        testFieldInInherit();

        testInterfaceSuperClassConflict3();
    }

    /**
     * 测试接口里字段和方法的修饰符
     */
    static void testFieldModifier() throws Exception {
        Class myInterfaceClass = Class.forName("org.lc.se.api.MyInterface");
        Field myField = myInterfaceClass.getField("MY_NUM");
        // Modifier:修饰符
        int mode = myField.getModifiers();

        // 接口会将声明的属性默认设置为public、static、final修饰的
        System.out.println(Modifier.isStatic(mode));
        System.out.println(Modifier.isFinal(mode));
        System.out.println(Modifier.isPublic(mode));

        Method[] methods = myInterfaceClass.getMethods();
        Method method = methods[0];
        System.out.println(Modifier.isStatic(method.getModifiers()));
    }

    static void testInterfaceAbstractStaticDefaultMethod() {
        MyInterface myInterface = new MyInterface1Impl();
        myInterface.test1();
        myInterface.test3();

        MyInterface myInterface1 = new MyInterface1Impl();

        // 只能被声明了该方法的接口调用
//        myInterface1.test2();
        MyInterface.test2();
    }

    static void testInterfaceSuperClassConflict() {
        MyInterface myInterface = new MyInterface1Impl();
        myInterface.conflict();
    }

    static void testInterfaceSuperClassConflict2() {
        MyInterface2 myInterface = new MyThread();
        myInterface.run();
    }

    static void testInterfaceSuperClassConflict3() {
        MyInterface.conflict3();
        MyInterface1Impl.conflict3();
        MyInterface myInterface = new MyInterface1Impl();
        // Static method may be invoked on containing interface class only
//        myInterface.conflict3();
        ((MyInterface1Impl) myInterface).conflict4();
    }

    static void testFieldInInherit() {
        MyInterface1Impl myInterface = new MyInterface1Impl();
        System.out.println(myInterface.s);
        System.out.println(myInterface.TEST);
        System.out.println(myInterface.MY_NUM);
        MyInterface myInterface1 = new MyInterface1Impl();
        System.out.println(myInterface1.TEST);
        System.out.println(myInterface1.MY_NUM);

        // 冲突
        System.out.println(myInterface  .CONFLICT_FIELD);
        System.out.println(MyInterface1Impl.CONFLICT_FIELD);
    }
}
