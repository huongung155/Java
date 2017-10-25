package com.company;

public class Main {

    public static void main(String[] args) {
        ITelephone quanPhone = new DeskPhone(123456);
        quanPhone.powerOn();
        quanPhone.callPhone(123456);
        quanPhone.answer();

        quanPhone = new MobilePhone(23456);
        quanPhone.powerOn();
        quanPhone.callPhone(23456);
        quanPhone.answer();
    }
}
