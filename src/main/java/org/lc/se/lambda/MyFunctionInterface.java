package org.lc.se.lambda;

/**
 * 函数式接口的写法：只有一个抽象方法
 * @param <T>
 */
@FunctionalInterface
public interface MyFunctionInterface<T> {

    void execute(T t);
}
