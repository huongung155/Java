package com.company;

/**
 * Created by M4800 on 04-Dec-16.
 */
public abstract class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
