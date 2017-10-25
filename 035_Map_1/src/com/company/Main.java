package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

import static sun.java2d.cmm.ColorTransform.In;

public class Main {

    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        locations.put(0, new Location(0, "You are sitting in front of computer learning Java",tempExit));

        Map<String, Integer> tempExit = new HashMap<String, Integer>();
        tempExit.put("W", 2);
        tempExit.put("E", 3);
        tempExit.put("S", 4);
        tempExit.put("N", 5);
        tempExit.put("Q", 0);
        locations.put(1, new Location(1, "You are standing at the end of the road",tempExit));


        tempExit.put("N", 5);
        tempExit.put("Q", 0);
        locations.put(2, new Location(3, "You are at the top of the hill",tempExit));
        
        tempExit.put("W", 1);
        tempExit.put("Q", 0);
        locations.put(3, new Location(3, "You are inside of the building",tempExit));
        
        tempExit.put("N", 1);
        tempExit.put("W", 2);
        tempExit.put("Q", 0);
        locations.put(4, new Location(4, "You are in valley beside a stream",tempExit));
        
        tempExit.put("S", 1);
        tempExit.put("W", 2);
        tempExit.put("Q", 0);
        locations.put(5, new Location(5, "You are in the forest",tempExit));
        
        int loc = 1;
        while (true){
            System.out.println(locations.get(loc).getDescription(,tempExit));
            if(loc == 0)
                break;

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are: ");
            for(String exit: exits.keySet()){
                System.out.println(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();

            if(exits.containsKey(direction)){
                loc = exits.get(direction);
            }else {
                System.out.println("You cannot go into that way");
            }
        }
    }
}
