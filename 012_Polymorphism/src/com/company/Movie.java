package com.company;

/**
 * Created by M4800 on 06-Nov-16.
 */
public class Movie {
    private String name;

    public Movie(String name) {
        this.name = name;
    }

    public String plot(){
        return "No plot here";
    }

    public String getName() {
        return name;
    }
}
