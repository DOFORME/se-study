package org.lc.se.api;

public class InnerClassTest {

    public static void main(String[] args) {
        testInitializeInnerClass();
    }

    static void testInitializeInnerClass() {
        MyClass myClass = new MyClass();
        // 不能这样使用
//        InnerClassDemo.MyInnerClass mic = new InnerClassDemo.MyInnerClass();
        // 不能去除外部类
        InnerClassDemo.MyInnerClass mic = new InnerClassDemo().new MyInnerClass();
    }
}
