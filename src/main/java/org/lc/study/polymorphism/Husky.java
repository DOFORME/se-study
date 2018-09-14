package org.lc.study.polymorphism;

public class Husky extends Dog {

    public Husky() {
        System.out.println("husky no argument constructor");
    }

    public Husky(String msg) {
        System.out.println("husky constructor with argument:" + msg);
    }

    public Husky(Integer number) {
        // not only call the super class's similar method
        super(number);
        System.out.println("husky constructor with argument:" + number);
    }

    {
        System.out.println("husky dynamic code");
    }

    static {
        System.out.println("husky static code");
    }

    public int age = 1;

    public void run() {
        System.out.println("husky run method");
    }

    public void eat() {
        System.out.println("husky eat method");
    }
}
