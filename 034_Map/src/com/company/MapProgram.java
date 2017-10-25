package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by M4800 on 19-Dec-16.
 */
public class MapProgram {
    public static void main(String[] args) {
        Map<String, String> languages = new HashMap<>();
        if(languages.containsKey("Java")){
            System.out.println("Java already exists");
        }else {
            languages.put("Java", "a compiled high level, objected-oriented");
        }

        languages.put("Python", "an interpreted program");
        languages.put("Algos", "an algorithmic language");

        if(languages.containsKey("Java")){
            System.out.println("Java is already in the map");
        }else {
            languages.put("Java", "this course is about Java");
        }

        System.out.println("============================================================");

        for(String key: languages.keySet()){
            System.out.println(key + " " + languages.get(key));
        }
    }
}
