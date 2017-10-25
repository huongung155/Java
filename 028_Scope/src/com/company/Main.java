package com.company;

public class Main {

    public static void main(String[] args) {
        String privateVar = "This is private to main()";

        ScopeCheck scopeInstance = new ScopeCheck();
        //System.out.println("scopeInstance privateVar is " + scopeInstance.getVarOne());
        /*System.out.println(privateVar);
        scopeInstance.timesTwo();*/
        ScopeCheck.InnerClass innerclass = scopeInstance.new InnerClass();
        System.out.println(innerclass.varThree);
    }
}
