package org.lc.se.polymorphism;

public class Animal {

    public Animal() {
        System.out.println("animal no argument constructor");
        System.out.println(staticString1);
        System.out.println(dynamicString1);
    }

    public Animal(String msg) {
        System.out.println("animal constructor with argument:" + msg);
    }

    public Animal(Integer number) {
        System.out.println("animal with argument number:" + number);
    }

    private String dynamicString1 = "dynamicString1";

    {
        System.out.println("animal dynamic code 1");
        System.out.println(dynamicString1);

        // can't reference
//        System.out.println(dynamicString2);
    }

    private String dynamicString2 = "dynamicString2";

    {
        System.out.println("animal dynamic code 2");
        System.out.println(dynamicString1);
        System.out.println(dynamicString2);
    }

    private static String staticString1 = "staticString1";

    static {
        System.out.println("animal static code 1");
        System.out.println(staticString1);

        // can't reference
//        System.out.println(staticString2);

    }

    private static String staticString2 = "staticString2";

    static {
        System.out.println("animal static code 2");
        System.out.println(staticString1);
        System.out.println(staticString2);
    }

    public int age = 100;

    public int kg = 30;

    public void run() {
        System.out.println("animal run method");
    }

    private void eat() {
        System.out.println("animal eat method");
    }

    protected Animal getSelf() {
        return new Animal();
    }

}
