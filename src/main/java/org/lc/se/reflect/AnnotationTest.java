package org.lc.se.reflect;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

/**
 * 获取注解信息
 */
public class AnnotationTest {

    @Test
    void testGetAnnotation() {
        Class<Override> clz = Override.class;
        Annotation[] annotations = clz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
