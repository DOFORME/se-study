package org.lc.se.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        testCreateProxyClass();
    }

    /**
     * https://m635674608.iteye.com/blog/1313890
     * https://www.ibm.com/developerworks/cn/java/j-lo-proxy1/index.html
     */
    static void testCreateProxyClass() {
        Object target = new StringBuilder("abc");
        InvocationHandler handler = new TraceHandler(target);
        Class[] interfaces = new Class[]{Comparable.class};
        Object proxy = Proxy.newProxyInstance(null, interfaces, handler);
        System.out.println(proxy.toString());
        System.out.println(proxy.getClass().getSimpleName());
        System.out.println(Proxy.isProxyClass(proxy.getClass()));

        Class clazz = Proxy.getProxyClass(null, interfaces);
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
        System.out.println(proxy instanceof Comparable);
//        System.out.println(proxy instanceof Comparable<StringBuilder>);
        if (proxy instanceof Comparable) {
//            System.out.println("===>" + ((Comparable) proxy).compareTo(null));
        }

    }
}
