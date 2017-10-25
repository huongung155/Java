package com.company;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank("National Vietnam Bank");

        if(bank.addBranch("Ho Chi Minh")){
            System.out.println("Ho Chi Minh Branch created");
        }


    }
}