package com.company;

import javax.security.auth.login.AccountException;

/**
 * Created by M4800 on 21-Sep-16.
 */
public class Bank_account {
    private String number;
    private double balance;
    private String customer_name;
    private String email;
    private String phone_number;

    public Bank_account() {
        this("45120", 123.65, "Default name", "Default address", "Default phone");
        System.out.println("Empty constructor called");
    }

    public Bank_account(String customer_name, String email, String phone_number) {
        this("99999", 12563.2, customer_name, email, phone_number);
    }

    public Bank_account(String number, double balance, String customer_name, String email, String phone_number){
        System.out.println("Bank_account constructor called");
        this.number = number;
        this.balance = balance;
        this.customer_name = customer_name;
        this.email = email;
        this.phone_number = phone_number;
    }
    public void Deposit(double depositAmount){
        this.balance += depositAmount;
        System.out.println("Deposit of " + depositAmount + " made. New balance is " + this.balance);
    }
    public void withdrawal(double withdrawalAmount){
        if(this.balance - withdrawalAmount <= 0)
            System.out.println("Only " + this.balance + " available. Withdrawal not processed");
        else{
            this.balance -= withdrawalAmount;
            System.out.println("Withdrawal of " + withdrawalAmount + " processed. Remaining balance " + this.balance);
        }
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
