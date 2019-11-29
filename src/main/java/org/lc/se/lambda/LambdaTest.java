package org.lc.se.lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * lambda表达式构成：
 *     左侧为参数列表
 *     右侧为表达式所执行的功能，即表达式体
 *
 * 语法：
 *     无参数：
 *         () -> express;
 *     一个参数：
 *         (t) -> express;
 *         t -> express;
 *     多个参数：具体参数对应关系看接口声明
 *         (m, n) -> express;
 *
 *
 *     无返回值：
 *         () -> System.out.println("0 param");
 *     有返回值：
 *         x -> x * x;
 *         x -> {
 *             return x * x;
 *         }
 *
 *     一行表达式体：没有大括号
 *     多行表达式体：大括号包裹
 *
 * java8 内置函数式接口：
 *     1. Consumer<T> 消费型接口 void accept(T t);
 *     2. Supplier<T> 供给型接口 T get();
 *     3. Function<T, R> 函数型接口 R apply(T, t);
 *     4. Predicate<T> 断言型接口 boolean test(T t);
 *
 *
 * 方法引用：
 *      ps：
 *          表达式体中调用的方法参数列表与返回值类型要与函数式接口中抽象方法的保持一致
 *          若表达式体参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName:method
 *     语法：
 *         对象::实例方法名
 *         类::静态方法名
 *         类::实例方法名
 *
 * 构造器引用：类名::new
 */
public class LambdaTest {

    /**
     * 无参数
     */
    @Test
    void test0() {
        Runnable runnable1 = () -> System.out.println("0 param");
        new Thread(runnable1).start();
    }

    /**
     * 一个参数，无返回值
     */
    @Test
    @SuppressWarnings("")
    void test1() {
        // 省略括号式写法
        Consumer<String> consumer1 = x -> System.out.println(x + "");
        consumer1.accept("consumer");

        Consumer<String> consumer2 = (x) -> System.out.println(x + "");
        consumer2.accept("consumer");
    }

    /**
     * 多个参数
     */
    @Test
    void test2() {
        // 这里可以使用方法引用简化
        Comparator<Integer> comparator = (x, y) -> x = x + y;
        System.out.println(comparator.compare(10, 5));
    }

    /**
     * 有返回值
     */
    @Test
    void test3() {
        // 单行模式返回，省略return
        Supplier<String> supplier1 = () -> "supplier";
        System.out.println(supplier1.get());

        Supplier<String> supplier2 = () -> {
            // 多行模式返回值，不省略return
            return "supplier";
        };
        System.out.println(supplier2.get());
    }
    /**
     * 一参数一返回值
     */
    @Test
    void test4() {
        // 具体哪个是参数哪个是返回值看函数式接口的声明
        Function<Integer, Integer> function = x -> x * x;
        System.out.println(function.apply(100));
    }

    @Test
    void testConsumer() {
        Consumer<String> consumer = (x) -> System.out.println(x + "");
        consumer.accept("consumer");
    }

    @Test
    void testSupplier() {
        Supplier<Double> supplier = () -> 0.5;
        System.out.println(supplier.get());
    }

    @Test
    void testFunction() {
        Function<Integer, String> function = (x) -> x + "f";
        System.out.println(function.apply(5));
    }

    /**
     * 多个参数的函数式接口
     */
    @Test
    void testBiFunction() {
        BiFunction<String, String, Boolean> biFunction = (x, y) -> x.equals(y + "");
        System.out.println(biFunction.apply("a", "a"));
    }

    @Test
    void testPredicate() {
        Predicate<Integer> predicate = (x) -> x > 100;
        System.out.println(predicate.test(50));
    }

    @Test
    void testMethodReference1() {
        StringBuilder sb = new StringBuilder("abc");
        // 实现了返回值为String的方法引用
        Supplier<String> supplier = sb::toString;
        System.out.println(supplier.get());
    }

    @Test
    void testMethodReference2() {
        Function<Integer, String> function = Integer::toHexString;
        System.out.println(function.apply(500));
    }

    @Test
    void testMethodReference3() {
        BiFunction<Integer, Integer, Boolean> biFunction = Integer::equals;
        System.out.println(biFunction.apply(100, 50));
    }

    @Test
    void testConstructorReference() {
        Supplier<StringBuilder> supplier = StringBuilder::new;
        System.out.println(supplier.get() == null);
    }
}
