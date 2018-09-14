package org.lc.study.polymorphism;

public class AnimalClassInitSequence {

    public static void main(String[] args) {
        Animal animal1 = new Animal();

        System.out.println("+++++++++++++++++++++");

        Animal animal2 = new Animal("animal2");

        // conclusion1: animal class init sequence is
        // -> animal static code (contain static code and variable)
        // -> dynamic code (contain dynamic code and variable)
        // -> constructor

        // conclusion2: static code only run once
    }
}
