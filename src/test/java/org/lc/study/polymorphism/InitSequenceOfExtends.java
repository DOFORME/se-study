package org.lc.study.polymorphism;

public class InitSequenceOfExtends {

    public static void main(String[] args) {
        Dog dog1 = new Dog();
        Husky husky1 = new Husky();

        System.out.println("+++++++++++++++++++++");

        // conclusion1: if extends is exist, the sequence of code run is
        // -> super class's(if super class has super class, the super class's super class run first) static code
        // -> export class's static code
        // -> super class's dynamic code
        // -> super class's constructor
        // -> export class's dynamic code
        // -> export class's constructor


        Husky husky2 = new Husky(2);

        System.out.println("+++++++++++++++++++++");

        // conclusion2: the export class can use super class constructor in it's constructor


        Husky husky3 = new Husky("husky3");

        System.out.println("+++++++++++++++++++++");

        // conclusion3: export class can call super class's constructor not limit the similar constructor
        // default: call super class's no argument constructor
        // super(Type type): call super class's right type constructor

        System.out.println("+++++++++++++++++++++");

        // up-casting
        Animal animal1 = new Husky();
    }
}
