package org.lc.se.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class UseCaseTrackerTest {

    @Test
    public void trackerUseCase() {
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
}
