package org.lc.se.api;


import java.util.Comparator;

public class LambdaDemo1 {

    public static void main(String[] args) throws Exception {
//        testLambda1();

//        testLambdaMethodInvoke();

        testLambdaConstructorInvoke();
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
        lambdaDemoInterface.test("abc");
    }

}
