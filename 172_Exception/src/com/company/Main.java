package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*int x = 98, y = 0;
        System.out.println(divideLBYL(x, y));
        System.out.println(divideEAFP(x, y));*/

        int x =getIntLBYL();
        int y =getIntEAFP();
        System.out.println("x is " + x);
    }

    private static int getIntLBYL(){
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;
        System.out.print("Please enter an integer: ");
        String input = scanner.next();
        for(int i = 0; i < input.length(); i++){
            if(!Character.isDigit(input.charAt(i))){
                isValid = false;
                break;
            }
        }if(isValid){
            return Integer.parseInt(input);
        }
        return 0;
    }

    private static int getIntEAFP(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter an integer: ");
        try {
            return scanner.nextInt();
        }catch (InputMismatchException e){
            return 0;
        }
    }

    private static int divideLBYL(int x, int y){//Look before you leap
        if(y != 0){
            return x / y;
        }else {
            return 0;
        }
    }

    private static int divideEAFP(int x, int y){
        try {
            return x / y;
        }catch (ArithmeticException e){
            return 0;
        }
    }
}
