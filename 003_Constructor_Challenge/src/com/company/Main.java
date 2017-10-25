package com.company;

public class Main {

    public static void main(String[] args) {
        Vip_customer Person1 = new Vip_customer();
        System.out.println(Person1.getName());

        Vip_customer Person2 = new Vip_customer("Minh Quan", 123545);
        System.out.println(Person2.getName());

        Vip_customer Person3 = new Vip_customer("Minh Quan", 123478, "Hyuong#gmail.com");
        System.out.println(Person3.getName());
        System.out.println(Person3.getEmail());
    }
}
