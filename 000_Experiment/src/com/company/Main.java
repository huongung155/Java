package com.company;

public class Main{

    public static void main(String[] args) {
        //int has width of 32
        int maxIntValue = 2_147_483_647;
        int minIntValue = -2_147_483_648;
        System.out.println(maxIntValue);

        //byte has width of 8
        byte maxByteValue = 127;
        byte minByteValue = -128;

        //short has width of 16
        short maxSHortValue = 32_767;
        short minShortValue = -32_768;

        //long has width of 64
        long longValue = 100L;

        //float has width of 32
        float floatValue = 5f/3f;

        //double has width of 64
        double doubleValue = 5d/3d;

        System.out.println("Float value is " + floatValue);
        System.out.println("Double value is " + doubleValue);

        char charValue = '\u00AE';
        System.out.println("Unicode output is " + charValue);

        boolean boolValue = true;

        String stringValue = "First String";
        stringValue += "\u00AE 2016";
        System.out.println(stringValue);

        int myInt = 50;
        stringValue += myInt;
        System.out.println(stringValue);
    }

    public static void calculate() {
        boolean gameOver = false;
        if(gameOver){
            System.out.println("GameOver");
        }
    }
}
