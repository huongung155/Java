package com.company;

/**
 * Created by M4800 on 22-Sep-16.
 */
public class Vip_customer {
    private String name;
    private int credit_limit;
    private String email;

    public Vip_customer(){
        this("Default name", 13246, "Default Email Address");
    }

    public Vip_customer(String name, int credit_limit){
        this(name, credit_limit, "Default Email");
    }

    public Vip_customer(String name, int credit_limit, String email) {
        this.name = name;
        this.credit_limit = credit_limit;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getCredit_limit() {
        return credit_limit;
    }

    public String getEmail() {
        return email;
    }
}
