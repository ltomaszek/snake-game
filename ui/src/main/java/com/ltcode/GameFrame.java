package com.ltcode;

import com.ltcode.game.Game;

import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame(Game game) {
        this.add(new GamePanel(game));
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);   // set in the middle of the screen
    }
}
