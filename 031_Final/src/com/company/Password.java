package com.company;

/**
 * Created by M4800 on 10-Dec-16.
 */
public class Password {
    private static final int key = 784521;
    private final int encryptedPassword;

    public Password(int encryptedPassword) {
        this.encryptedPassword = encryptDecrypt(password);
    }

    private int encryptDecrypt(int password){
        return password ^ key;
    }

    public void storePassword(){
        System.out.println("Saving password as " + this.encryptedPassword);
    }


}
