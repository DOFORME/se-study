package org.lc.se.annotation;

import java.lang.reflect.Method;

public class UseCaseTracker {

    public static void trackerUseCase(Class clz) {
        for (Method method : clz.getDeclaredMethods()) {
            UseCase uc = method.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("method" + method.getName() + "拥有@UseCase注解");
                System.out.println("value为" + uc.value());
                System.out.println("description为" + uc.description());
            }
        }
    }

    public static void main(String[] args) {
        trackerUseCase(UseCaseCall.class);
    }
}
