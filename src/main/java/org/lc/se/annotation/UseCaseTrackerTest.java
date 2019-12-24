package org.lc.se.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class UseCaseTrackerTest {

    @Test
    public void testUseCase() {
        Class<UseCaseCall> clz = UseCaseCall.class;
        for (Method method : clz.getDeclaredMethods()) {
            UseCase uc = method.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("method" + method.getName() + "拥有@UseCase注解");
                System.out.println("value为" + uc.value());
                System.out.println("description为" + uc.description());
            }
        }
    }

    @Test
    public void testUseCase2() {
        Class<UseCaseCall> clz = UseCaseCall.class;
        for (Method method : clz.getDeclaredMethods()) {
            UseCase2 uc2 = method.getAnnotation(UseCase2.class);
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation.getClass().getSimpleName());
            }
            if (uc2 != null) {
                System.out.println("method" + method.getName() + "拥有@UseCases注解");
                System.out.println("value为" + uc2.value());
            }
        }
    }

    @Test
    public void testUseCase3() {
        Class<UseCaseCall> clz = UseCaseCall.class;
        for (Method method : clz.getDeclaredMethods()) {
            UseCases uc = method.getAnnotation(UseCases.class);
            if (uc != null) {
                System.out.println("method" + method.getName() + "拥有@UseCases注解");
                System.out.println("value为" + Arrays.toString(uc.value()));
            }
        }
    }
}
