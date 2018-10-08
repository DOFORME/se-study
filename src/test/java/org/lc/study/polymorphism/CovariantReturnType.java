package org.lc.study.polymorphism;

/**
 * @since jdk 1.5
 */
public class CovariantReturnType {

    public static void main(String[] args) {
        Animal animal = new Animal();
        System.err.println(animal.getSelf());
        animal = new Dog();
        System.err.println(animal.getSelf());
    }
}
