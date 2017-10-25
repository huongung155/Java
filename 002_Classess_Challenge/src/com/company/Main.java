package com.company;

import javax.security.auth.login.AccountException;

public class Main {

    public static void main(String[] args) {
        Bank_account QuanAccount = new Bank_account();//("123465", 123.3, "Luu Minh Quan", "huyong@sdf.com", "0908234");
        System.out.println(QuanAccount.getNumber());
        System.out.println(QuanAccount.getBalance());
        QuanAccount.Deposit(102);
        QuanAccount.withdrawal(50);
        Bank_account LuuAccount = new Bank_account("Luu", "huong@gmail.com", "0908");
        LuuAccount.Deposit(23);;
        LuuAccount.withdrawal(102);
    }
}
