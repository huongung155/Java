package com.company;

/**
 * Created by M4800 on 29-Dec-16.
 */
public class Labrador extends Dog {
    public Labrador(String name) {
        super(name);
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(this == obj){
//            return true;
//        }
//
//        if(obj instanceof Labrador){
//            String objName = ((Labrador) obj).getName();
//            return this.getName().equals(objName);
//        }
//
//        return false;
//    }
}