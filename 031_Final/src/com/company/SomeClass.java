package com.company;

/**
 * Created by M4800 on 10-Dec-16.
 */
public class SomeClass {
    private static int classCounter = 0;
    public final int instanceNumber;
    private final String name;

    public SomeClass(String name) {
        this.name = name;
        classCounter++;
        instanceNumber = classCounter;
        System.out.println(name + " created, instance is " + instanceNumber);
    }
}
