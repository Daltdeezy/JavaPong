package com.company;

import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

    Random random;
    //how fast the ball moves on the axis'
    int xVelocity;
    int yVelocity;
    int initSpeed = 3;

    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXDirection = random.nextInt(2); //either 0 or 1 if 0 left, 1 right
        if (randomXDirection==0)
            randomXDirection--;
        setXDirection(randomXDirection*initSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection==0)
            randomYDirection--;
        setYDirection(randomYDirection*initSpeed);
    }

    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }
    public void move(){
        x+= xVelocity;
        y+= yVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.white); //ball color
        g.fillOval(x,y,height,width); //makes the graphic or ball a circle
    }
}
