package com.company;

/**
 * Created by M4800 on 06-Nov-16.
 */
public class StarWars extends Movie {
    public StarWars() {
        super("Star Wars");
    }

    @Override
    public String plot() {
        return "Imperial Forces try to take over the universe";
    }
}
