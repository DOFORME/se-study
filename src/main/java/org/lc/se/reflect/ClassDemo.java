package org.lc.se.reflect;

/**
 * @author lph
 */
public class ClassDemo {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        obtainClass();
        createInstance();
    }

    private static void obtainClass() throws ClassNotFoundException {
        Class cls = ClassDemo.class;
        Class cls2 = Class.forName("org.lc.se.reflect.ClassDemo");
        Class cls3 = new ClassDemo().getClass();
        System.out.printf("%s\n%s\n%s\n", cls, cls2, cls3);
        System.out.println(cls == cls2);
        System.out.println(cls == cls3);
    }

    private static void createInstance() throws IllegalAccessException, InstantiationException {
        Class cls = ClassDemo.class;
        ClassDemo cd = (ClassDemo) cls.newInstance();
        System.out.println(cd);
    }
}
