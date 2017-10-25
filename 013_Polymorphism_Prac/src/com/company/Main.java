package com.company;

public class Main {

    public static void main(String[] args) {
        Car car = new Car(8, "Base car");
        System.out.println(car.startEngine());
        System.out.println(car.accelerate());
        System.out.println(car.brake());

        Car mitsubishi = new Mitsubishi(8, "Outlander VRM 4WD");
        System.out.println(mitsubishi.startEngine());
        System.out.println(mitsubishi.accelerate());
        System.out.println(mitsubishi.brake());

        Car ford = new Ford(8, "Ford Falcon");
        System.out.println(ford.startEngine());
        System.out.println(ford.accelerate());
        System.out.println(ford.brake());
    }

    /**
     * Created by M4800 on 06-Nov-16.
     */
    public static class Holden extends Car {
        public Holden(int cylinders, String name) {
            super(cylinders, name);
        }

        @Override
        public String startEngine() {
            return "Mitsubishi -> startEngine()";
        }

        @Override
        public String accelerate() {
            return "Mitsubishi -> accelerate()";
        }

        @Override
        public String brake() {
            return "Mitsubishi -> brake()";
        }
    }

    /**
     * Created by M4800 on 06-Nov-16.
     */
    public static class Ford extends Car {
        public Ford(int cylinders, String name) {
            super(cylinders, name);
        }

        @Override
        public String startEngine() {
            return "Ford -> startEngine()";
        }

        @Override
        public String accelerate() {
            return "Ford -> accelerate()";
        }

        @Override
        public String brake() {
            return "Ford -> brake()";
        }
    }
}
