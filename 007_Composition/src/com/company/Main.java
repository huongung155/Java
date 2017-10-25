package com.company;

public class Main {

    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(23, 52, 56);
        Case theCase = new Case("Z123D", "Asus", "CoolerMaster", dimensions);

        Monitor theMonitor = new Monitor("27inch Dell", "Dell", 27, new Resolution(1920, 1080));

        Motherboard theMotherboard = new Motherboard("Z123D", "Asus", 4, 4, "v1.0");

        PC thePC = new PC(theCase, theMonitor, theMotherboard);

        thePC.getMonitor().drawPiixel(2, 3, "Yellow");
        thePC.getMotherboard().loadProgram("Windows 10");
        thePC.getTheCase().pressPowerButton();
    }
}
