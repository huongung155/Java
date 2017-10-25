package com.company;

import java.util.ArrayList;

/**
 * Created by M4800 on 18-Jan-17.
 */
public class Customers {
    private String name;
    private ArrayList<Double> transaction;

    public Customers(String name, double amount) {
        this.name = name;
        this.transaction = new ArrayList<Double>();
        addTransaction(amount);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransaction() {
        return transaction;
    }

    public void addTransaction(double amount){
        transaction.add(amount);
    }
}
