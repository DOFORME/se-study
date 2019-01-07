package org.lc.se.annotation;

public class UseCaseCall {

    @UseCase(value = "value 1", description = "method 1")
    public void method1() {
        System.out.println("method 1");
    }

    @UseCase(value = "value 2", description = "method 2")
    public void method2() {
        System.out.println("method 2");
    }
}
