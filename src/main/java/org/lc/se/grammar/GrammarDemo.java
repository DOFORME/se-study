package org.lc.se.grammar;

/**
 * @author lc
 */
public class GrammarDemo {

    public static void main(String[] args) {
        new GrammarDemo().testInnerClassThis();
    }

    private void testInnerClassThis() {
        System.out.println(this);
        new Test().testMethod();
        new InnerClass().testMethod();
    }

    /**
     * 匿名内部类
     */
    private void testAnonymous() {
        // 该匿名类是Runnable接口的实现类
        Thread t = new Thread(() -> System.out.println("anonymous inner class test"));
        t.start();
    }

    /**
     * 内部类
     */
    class InnerClass {
        void testMethod() {
            System.out.println(this);
        }
    }
}

/**
 * 同文件非public类
 */
class Test {

    void testMethod() {
        System.out.println(this);
    }
}
