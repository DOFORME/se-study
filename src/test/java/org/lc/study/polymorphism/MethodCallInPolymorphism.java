package org.lc.study.polymorphism;

public class MethodCallInPolymorphism {

    public static void main(String[] args) {
        Husky husky = new Husky();
        husky.eat();
        husky.run();

        System.out.println("----------------------");

        Animal animal1 = new Husky();

        // eat method is private in animal class
        ((Husky) animal1).eat();
        animal1.run();

        Animal animal2 = new Animal();
        animal2.run();

        // private method can't be call outside of that class
//        animal2.eat();

        System.out.println("----------------------");
        Dog dog1 = new Dog();
        dog1.eat();
        dog1.run();

        Dog dog2 = new Husky();
        dog2.run();
        dog2.eat();
    }
}
