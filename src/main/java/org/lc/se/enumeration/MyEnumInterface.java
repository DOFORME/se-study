package org.lc.se.enumeration;

/**
 * @author lc
 */
public interface MyEnumInterface<T> {

    /**
     * 获取一个枚举实例
     *
     * @return MyEnumInterface
     */
    MyEnumInterface<T> next();
}
