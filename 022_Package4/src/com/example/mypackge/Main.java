package com.example.mypackge;

import com.example.game.ISaveable;
import com.example.game.Monster;
import com.example.game.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> readValues(){
        ArrayList<String> values = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int index = 0;
        System.out.println("Choose 1 to enter a string or 0 to quit");

        while (!quit){
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.print("Enter a string: ");
                    String stringInput = scanner.nextLine();
                    values.add(index, stringInput);
                    index++;
                    break;
            }
        }
        return values;
    }

    public static void saveObject(ISaveable objectToSave){
        for(int i = 0; i < objectToSave.write().size(); i++)
            System.out.println("Saving " + objectToSave.write().get(i) + " to storage device");
    }

    public static void loadObject(ISaveable objectToLoad){
        ArrayList<String> values = readValues();
        objectToLoad.read(values);

    }

    public static void main(String[] args) {
        Player player = new Player("Quan", 10, 15);
        System.out.println(player);
        saveObject(player);

        player.setHitPoints(8);
        System.out.println(player);
        player.setWeapon("Stormbringer");
        saveObject(player);
        loadObject(player);
        System.out.println(player);

        ISaveable monster = new Monster("Werewolf", 20, 40);
        System.out.println(monster);
        saveObject(monster);
    }
}
