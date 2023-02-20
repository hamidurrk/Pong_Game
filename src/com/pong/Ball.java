package com.pong;

import java.awt.Color;
import java.awt.Graphics;


public class Ball {
	public static final int SIZE = 16;
	
	private int x, y;
	private int xVel, yVel;
	private int speed = 5;
	public boolean pauseState = false;
	
	public Ball() {
		reset();
	}

	public void reset() {
		x = Game.WIDTH/2 - SIZE/2;
		y = Game.HEIGHT/2 - SIZE/2;
		
		xVel = Game.sign(Math.random()*2 -1);
		yVel = Game.sign(Math.random()*2 -1);
	}
	
	public void changeXDir() {
		xVel *= -1;
	}
	
	public void changeYDir() {
		yVel *= -1;
	}

	public void pause(boolean p) {
		if (!p) {
			speed = 5;
		}
		else {
			speed = 0;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, SIZE, SIZE);
	}

	public void update(Paddle paddle1, Paddle paddle2) {

		x += xVel * speed;
		y += yVel * speed;

		if(y >= Game.HEIGHT - SIZE || y<= 0) {
			changeYDir();
		}

		if(x >= Game.WIDTH - SIZE) {
			paddle1.addPoint();
			reset();

			pause(true);
			KeyInput.ballPaused = true;
			paddle1.reset();
			paddle2.reset();
			try {
				Thread.sleep(500); // Pause the thread for 1 second
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(x <= 0) {
			paddle2.addPoint();
			reset();

			pause(true);
			KeyInput.ballPaused = true;
			paddle1.reset();
			paddle2.reset();
			try {
				Thread.sleep(500); // Pause the thread for 1 second
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
