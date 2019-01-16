package org.lc.se.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object o) {
        target = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("当前类是：" + this.getClass().getSimpleName());
        System.out.println("当前调用的是：" + target.getClass().getSimpleName() + "类的：" + method.getName() + "方法");
//        return "xxxxxxxxxxx";
        return method.invoke(target, args);
    }
}
