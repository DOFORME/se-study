package org.lc.se.reflect;

import java.lang.reflect.InvocationTargetException;

public class InnerClass {
    static int i;
    static {
        i = 100;
        System.out.println("inner class");
    }

    public void m() {
        System.out.println("m");
    }

    class TestClass {
        {
            System.out.println("test class2");
        }

        public void m() {
            System.out.println("inner class m");
        }
    }

    public static void main(String[] args) {
        Class<TestClass2> clz = TestClass2.class;
        try {
            clz.getDeclaredConstructor().newInstance().m();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

class TestClass2 {
    static final int I = 100;
    static int J = 100;
    static {
        System.out.println("test class2");
    }

    {
        System.out.println("test class2 not static");
    }

    void m() {
        System.out.println("m m");
    }
}
