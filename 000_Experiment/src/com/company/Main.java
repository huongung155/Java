package com.company;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setTitle("Game 2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(340, 400);
        game.setResizable(false);

        game.add(new Game2048());

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
