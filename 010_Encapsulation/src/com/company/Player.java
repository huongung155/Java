package com.company;

/**
 * Created by M4800 on 05-Nov-16.
 */
public class Player{
    public String name;
    public int health;
    public String weapon;

    public void loseHealth(int damage){
        health = health - damage;
        if(this.health <= 0){
            System.out.println("Player knocked out");
        }
    }

    public int healthRemaining(){
        return this.health;
    }
}
