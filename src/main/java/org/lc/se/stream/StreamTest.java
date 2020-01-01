package org.lc.se.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    void testCreatStream() {
        List<String> list1 = new ArrayList<>();
        list1.add("test");
        Stream<String> stream1 = list1.stream();
        stream1.forEach(System.out::println);

        String[] list2 = {"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(list2);
        stream2.forEach(System.out::println);

        Stream<String> stream3 = Stream.of("b", "c", "d");
        stream3.forEach(System.out::println);

        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
        stream4.limit(5).forEach(System.out::println);

        Stream<Double> stream5 = Stream.generate(Math::random);
        stream5.limit(5).forEach(System.out::println);
    }








    @Test
    @SuppressWarnings("all")
    void testFilter() {
        List<String> strings = Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2");
        // filter传入Predicate接口的lambda
        strings.stream().filter(s -> s.startsWith("b")).forEach(System.out::println);

        strings.stream().filter(s -> {
            // 没有终止操作，中间操作不执行
            boolean result = s.startsWith("b");
            System.out.println(s);
            return result;
        });
    }

    @Test
    void testMap() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        // result: 2345
        list.stream().map(i -> i + 1).forEach(System.out::print);
    }

    @Test
    void testMapToInt() {
        List<Double> list = Arrays.asList(1.0, 0.2, 0.5, 4.5, 8.2);
        // 映射成int集合
        list.stream().mapToInt(Double::intValue).forEach(System.out::print);
    }

    @Test
    void testDistinct() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 1, 2);
        // List<Integer> list = Arrays.asList(1, 2, 3, 4);
        list.stream().distinct().forEach(System.out::print);
    }


    @Test
    void testForEach() {
        List<Integer> list = Arrays.asList(5, 1, 4, 2, 6, 7);
        // result: 514267
        list.forEach(System.out::print);
    }




}
