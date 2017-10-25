package com.company;

import javax.xml.bind.ValidationEventLocator;

/**
 * Created by M4800 on 27-Sep-16.
 */
public class Vehicle {
    private String name;
    private String size;
    private int currentVelocity;
    private int currentDirection;

    public Vehicle(String name, String size) {
        this.name = name;
        this.size = size;
        this.currentDirection = 0;
        this.currentVelocity = 0;
    }

    public void steer(int currentDirection){
        this.currentDirection += currentDirection;
        System.out.println("Vehicle.steer() called: Steering at " + this.currentDirection + " degrees");
    }

    public void move(int currentDirection, int currentVelocity){
        this.currentDirection = currentDirection;
        this.currentVelocity = currentVelocity;
        System.out.println("Vehicle.move(): Moving at " + this.currentVelocity + " in direction " + this.currentDirection);
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public int getCurrentVelocity() {
        return currentVelocity;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public void stop(){
        this.currentVelocity = 0;
    }
}
