package com.company;

import java.awt.*;
import java.awt.event.*;

public class Paddles extends Rectangle{

    int id; //1 for player 1, 2 for player 2
    int yVelocity; //how fast the paddle moves up and down
    int speed=10;

    Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;
    }

    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){ //w to move up
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){ //s to move down
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){ //Arrow pad up for up
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){ //Arrow pad down for down
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){ //w to move up
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){ //s to move down
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){ //Arrow pad up for up
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){ //Arrow pad down for down
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    public void setYDirection(int yDirection){ //paddles will only move along the Y axis never the X axis
        yVelocity = yDirection;
    }
    public void move(){
        y=y+yVelocity;
    }
    public void draw(Graphics g){
        if(id==1)
            g.setColor(Color.blue); //player1 color
        else
            g.setColor(Color.red); //player2 color
        g.fillRect(x,y,width,height);
    }
}
