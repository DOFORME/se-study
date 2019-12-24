package org.lc.se.annotation;

import java.lang.annotation.*;

/**
 * jdk8后写法
 * 注意@Target和@Retention保持一样
 */
@Repeatable(UseCases.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase2 {

    String value() default "";
}
