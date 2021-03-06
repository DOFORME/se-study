package org.lc.se.polymorphism;


public class Dog extends Animal {

    public Dog() {
        System.out.println("dog no argument constructor");
    }

    public Dog(String msg) {
        System.out.println("dog constructor with argument:" + msg);
    }

    public Dog(Integer number) {
        super(number);
        System.out.println("dog constructor with number:" + number);

        // super method must be the first row code of constructor if it exist
//        super();
    }

    {
        System.out.println("dog dynamic code");
    }

    static {
        System.out.println("dog static code");
    }

    public int age = 10;

    @Override
    public void run() {
        System.out.println("dog run method");
    }

    // can't use override annotation. this method is not override, it's dog own method.
//    @Override
    public void eat() {
        System.out.println("dog eat method");
    }

    @Override
    protected Dog getSelf() {
        return new Dog();
    }

    @Override
    public String toString() {
        return "dog";
    }
}
