package org.lc.se.annotation;

public class UseCaseCall {

    @UseCase(value = "value 1", description = "method 1")
    public void method1() {
        System.out.println("method 1");
    }

    /**
     * 容器注解不能和对应的元素注解同时标注在一起
     * UseCase2 元素注解 编译后变成了容器注解
     * UseCases 容器注解
     */
    @UseCase2(value = "value 1")
    @UseCase2(value = "value 2")
    public void method2() {
        System.out.println("method 2");
    }
    @UseCases({@UseCase2(value = "value 3"), @UseCase2(value = "value 4")})
    public void method3() {
        System.out.println("method 3");
    }
}
