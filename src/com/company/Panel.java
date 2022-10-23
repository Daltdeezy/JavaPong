package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable{ //runnable is so it can run on a thread

    static final int GAME_WIDTH = 1000; //static so all instances of Panel uses this width, final because it won't change
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); //cast as an int so the dimension isn't unusual
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20; //20px
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddles paddle1, paddle2;
    Ball ball;
    Score score;

    Panel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this); //creates the thread
        gameThread.start(); //starts the thread
    }
    //methods used to create a new game
    public void newBall(){
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }
    public void newPaddles(){
        paddle1 = new Paddles(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddles(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0,this);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move(){
        /* allows the ticks to be updated more smooth from the gameloop creating a smoother Y movement for the paddles
        this also allows for them both to be moved at the same time. */
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void isCollision(){
        //bounce ball off top and bottom window
        if(ball.y <=0)
            ball.setYDirection(-ball.yVelocity);
        if(ball.y >=GAME_HEIGHT-BALL_DIAMETER) //checks the balls top left corner as Y
            ball.setYDirection(-ball.yVelocity);

        //bounce ball off paddles
        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity); //reverses the ball
            ball.xVelocity++; //makes the ball faster as the game progresses
            if (ball.yVelocity > 0)
                ball.yVelocity++;//makes the ball faster as the game progresses
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity); //reverses the ball
            ball.xVelocity++; //makes the ball faster as the game progresses
            if (ball.yVelocity > 0)
                ball.yVelocity++;//makes the ball faster as the game progresses
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //makes sure paddles stay on screen
        if(paddle1.y <=0)
            paddle1.y=0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y <=0)
            paddle2.y=0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;

        //give player points and new ball/paddles
        if(ball.x <=0){
            score.player2++;
            newPaddles();
            newBall();
        }
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
        }
    }

    public void run(){ //gameloop taken from Minecraft
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns=1000000000/amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta >=1){
                move();
                isCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter{ //extends from package java.awt.event / AL stands for Action Listener
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
