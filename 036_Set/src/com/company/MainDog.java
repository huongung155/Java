package com.company;

/**
 * Created by M4800 on 29-Dec-16.
 */
public class MainDog {
    public static void main(String[] args) {
        Dog rover = new Labrador("Rover");
        Dog rover2 = new Dog("Rover");

        System.out.println(rover2.equals(rover));
        System.out.println(rover.equals(rover2));
    }
}
