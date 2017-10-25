package com.company;

public class Main {

    public static void main(String[] args) {
        Animal animal = new Animal("Animal", 1, 1, 23, 123);
        Dog dog = new Dog("CB", 1, 234, 2, 2, 1, 234, "long silky");
        dog.eat();
        dog.run();
    }
}
