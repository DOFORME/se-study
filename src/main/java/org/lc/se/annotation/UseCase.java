package org.lc.se.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解：
 * 1.通过 @interface 来声明这是一个注解
 * 2.@Target来声明注解作用范围（没指定的话则可以到处使用）
 * 3.@Retention来声明保留策略
 * 4.像方法声明一样的实例属性声明
 *
 * 当一个注解没有任何属性时表明是一个标志注解（类似Serializable接口）
 * 自定义注解框架中使用的多，平时一般不怎么使用，通常配合反射实现特定功能
 * 只有RetentionPolicy.RUNTIME才能通过反射获得
 *
 * 自定义注解元素的类型：
 * 8大基本类型（包装类型不行）
 * String
 * Class
 * enum
 * Annotation
 * 上述类型的数组
 *
 * @author lc
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    /**
     * 当一个注解只有一个属性时，通常使用value
     * 没有声明默认值时，该注解在使用时需要显示指明
     */
    String value();

    /**
     * 有默认值的属性，在使用时可以不用再显示指明了
     */
    String description() default "";
}
