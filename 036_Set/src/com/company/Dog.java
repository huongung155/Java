package com.company;

/**
 * Created by M4800 on 29-Dec-16.
 */
public class Dog {
    private final String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj instanceof Dog){
            String objName = ((Dog) obj).getName();
            return this.name.equals(objName);
        }

        return false;
    }
}
