package com.company;

import java.util.ArrayList;

/**
 * Created by M4800 on 18-Jan-17.
 */
public class Branch {
    private String name;
    private ArrayList<Customers> customers;

    public Branch(String name) {
        this.name = name;
        this.customers = new ArrayList<Customers>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customers> getCustomers() {
        return customers;
    }

    public Customers findCustomer(String customer){
        for(Customers i : customers){
            if(i.getName().equals(customer)){
                return i;
            }
        }
        return null;
    }

    public boolean newCustomer(String name, double amount){
        if(findCustomer(name) == null){
            customers.add(new Customers(name, amount));
            return true;
        }
        return false;
    }

    public boolean addCustomer(String customer, double amount){
        Customers foundCustomer = findCustomer(customer);
        if(foundCustomer != null){
            foundCustomer.addTransaction(amount);
            return true;
        }
        return false;
    }
}
