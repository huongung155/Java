package com.company;

import java.util.ArrayList;

/**
 * Created by M4800 on 18-Jan-17.
 */
public class Bank {
    private String name;
    private ArrayList<Branch> branch;

    public Bank(String name) {
        this.name = name;
        this.branch = new ArrayList<Branch>();
    }

    public Branch findBranch(String name){
        for(Branch i : branch){
            if(i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }

    public boolean addBranch(String name){
        if(findBranch(name) == null){
            branch.add(new Branch(name));
            return true;
        }
        return false;
    }

    public boolean addCustomer(Branch branch_1, String nameCustomer, double amount){
        Branch foundBranch = findBranch(branch_1.getName());
        if(foundBranch != null){
            return foundBranch.newCustomer(nameCustomer, amount);
        }
        return false;
    }

    public boolean addTransaction(Branch branch_1, String nameCustomer, double amount){
        Branch foundBranch = findBranch(branch_1.getName());
        if(foundBranch != null){
            return foundBranch.addCustomer(nameCustomer, amount);
        }
        return false;
    }

    public void showList(Branch branch_1){
        Branch foundBranch = findBranch(branch_1.getName());
        if(foundBranch != null){
            for(Customers i : foundBranch.getCustomers()){
                System.out.println(i.getName() + ". His/Her transactions: ");
                for(Double it : i.getTransaction()){
                    System.out.println("\t" + it);
                }
            }
        }
    }
}
