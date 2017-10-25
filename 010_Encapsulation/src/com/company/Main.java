package com.company;

public class Main {

    public static void main(String[] args) {
        /*Player player = new Player();
        player.name = "Quan";
        player.health = 20;
        player.weapon = "Sword";

        int damage = 10;
        player.loseHealth(damage);
        System.out.println("Remaining health = " + player.healthRemaining());

        damage = 11;
        player.loseHealth(damage);
        System.out.println("Remaining health = " + player.healthRemaining());*/

        EnhancedPlayer player = new EnhancedPlayer("Quan", 220, "Sword");
        int damage = 10;
        player.loseHealth(damage);
        System.out.println("Remaining health = " + player.getHealth());

        damage = 11;
        player.loseHealth(damage);
        System.out.println("Initial health is " + player.getHealth());

        EnhancedPlayer player1 = new EnhancedPlayer("Tim", 20, "Sword");
        damage = 10;
        player1.loseHealth(damage);
        System.out.println("Remaining health = " + player1.getHealth());

    }
}
