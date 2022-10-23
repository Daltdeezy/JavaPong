package com.company;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{

    Panel panel;

    Frame(){
        //creates the outlying frame which fills in based on Panel class
        panel = new Panel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //So we can close the game
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); //forces the window into the middle of the screen
    }
}
