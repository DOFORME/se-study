package org.lc.se.keywords;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Java在执行的时候默认是不启动断言检查
 * 如果要开启断言检查，则需要用开关 -enableassertions 或 -ea 来开启
 * 单元测试中启用检查
 */
public class AssertTest {

    /**
     * 当assert后面的值为false时，抛出异常java.lang.AssertionError(单元测试中，main方法中不执行)
     * 否则正常运行
     */
    @Test
    void testAssert() {
        double d = new Random().nextDouble();
        boolean b= d > 0.5;
        assert b;
    }

    /**
     * 为true时没有信息打印，为false时打印异常信息且 : 后的信息作为异常提示的信息
     */
    @Test
    void testAssert2() {
        double d = new Random().nextDouble();
        boolean b= d > 0.5;
        assert b : "语法2";
    }
}
