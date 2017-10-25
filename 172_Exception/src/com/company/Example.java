package com.company;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by M4800 on 17-May-17.
 */
public class Example {
    public static void main(String[] args) {
        try {
            int result = divide();
            System.out.println(result);
        }catch (ArithmeticException | NoSuchElementException e){
            System.out.println(e.toString());
            System.out.println("Unable to perform division, autopilot shutting down");
        }
    }

    private static int divide(){
        /*int x = getInt();
        int y = getInt();
        System.out.println("x is " + x + ", y is " + y);
        return x / y;*/

        int x, y;
//        try {
            x = getInt();
            y = getInt();
            System.out.println("x is " + x + ", y is " + y);
            return x / y;
//        }catch (NoSuchElementException e){
//            throw new ArithmeticException("No suitable input");
//        }catch (ArithmeticException e){
//            throw new ArithmeticException("Attempt to divide zero");
//        }
    }

    private static int getInt(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an integer: ");
        while (true){
            try {
                return scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Please enter a number using only the digits 0 to 9");
            }
        }
    }
}
