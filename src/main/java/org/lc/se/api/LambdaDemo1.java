package org.lc.se.api;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class LambdaDemo1 {

    public static void main(String[] args) throws Exception {
//        testLambda1();

//        testLambdaMethodInvoke();

//        testLambdaConstructorInvoke();

//        testLambdaFreeVariable();

//        testLoopUseLambda();

//        testLogicOperationWithLambda();

//        testLambdaMethodReference();

        testStreamAPI();
    }



    private static void testLambda1() {
        Runnable runnable = () -> System.out.println("lambda test");
        new Thread(runnable).start();

        MyInterface myInterface = () -> System.out.println("lambda test of my interface");
        myInterface.test1();

        // 推导出变量的类型
        String string = "234";
        int j = 123;
        LambdaDemoInterface lambdaDemoInterface = s -> {
            System.out.println(s);
            System.out.println(string);
            System.out.println(j);
            // 有返回值时需要
//            return 0;
        };
        for (int i=0; i<5; i++) {
//            LambdaDemoInterface ldi = s -> System.out.println(i);
            lambdaDemoInterface.test(i);
        }


        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable 1");
            }
        };
        new Thread(runnable1).start();

    }

    static void myMethod1() {
        MyNum num1 = new MyNum();
        num1.setNum(1);
        MyNum num2 = new MyNum();
        num2.setNum(2);

        Comparator<MyNum> comparator1 = (MyNum::compareTo);
        System.out.println(comparator1.compare(num1, num2));
    }

    private static void testLambdaMethodInvoke() {
        LambdaDemoInterface<String> lambdaDemoInterface = System.out::println;
        lambdaDemoInterface.test("abc");


    }

    private static void testLambdaConstructorInvoke() {
        LambdaDemoInterface<String> lambdaDemoInterface = s -> {
            System.out.println(s);
            System.out.println("method invoke");
        };
        lambdaDemoInterface.test("abc");
        lambdaDemoInterface = LambdaDemoInterfaceImpl::new;
        lambdaDemoInterface.test("abcd");
    }

    static void testLambdaFreeVariable() {
        String s = "test";
        MyNum myNum = new MyNum();
        int i = 2;
        LambdaDemoInterface<String> lambdaDemoInterface = s1 -> {
            // 不能定义已经有的变量
//            String s = "s2";
            // Variable used in lambda expression should be final or effectively final
//            i--;
            System.out.println(s);
            System.out.println(myNum.getNum());
            System.out.println(i);
        };
        // Variable used in lambda expression should be final or effectively final
//        i = 10;
        lambdaDemoInterface.test("abc");
    }

    static void testLoopUseLambda() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        // 两种方法选择一种
        integers.forEach(System.out::println);
//        integers.forEach(n -> System.out.println(n));
    }

    static void testLogicOperationWithLambda() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println("输出所有：");
        evaluate(integers, (n) -> true);

        System.out.println("输出偶数");
        evaluate(integers, n -> n % 2 == 0);

    }

    static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (int i : list) {
            if (predicate.test(i)) {
                System.out.println(i + " ");
            }
        }
    }

    /**
     * lambda方法引用
     * Class::instanceMethod形式
     * 等同x.instanceMethod(y)
     */
    static void testLambdaMethodReference() {
        LambdaCompare lc = String::compareTo;
        int result = lc.test("a", "b");
        System.out.println(result);
        System.out.println(lc.test("c", "b"));
    }

    static void testStreamAPI() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream().map(x -> x * x).forEach(System.out::println);
        int sum = integers.stream().reduce((x, y) -> x + y).get();
        System.out.println(sum);
    }

}
