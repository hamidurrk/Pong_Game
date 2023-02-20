package com.pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Paddle p1;
	private boolean up1 = false;
	private boolean down1 = false;
	
	private Paddle p2;
	private boolean up2 = false;
	private boolean down2 = false;

	private Ball b;

	public static boolean isPaused = false;
	public static boolean ballPaused = true;

	public KeyInput(Paddle p1, Paddle p2, Ball b) {
		this.p1 = p1;
		this.p2 = p2;
		this.b = b;

		p1.stop();
		p2.stop();
		b.pause(ballPaused);
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (!isPaused && !ballPaused) {
			if (key == KeyEvent.VK_UP) {
				p2.switchDirection(-1);
				up2 = true;
			}
			if (key == KeyEvent.VK_DOWN) {
				p2.switchDirection(1);
				down2 = true;
			}
			if (key == KeyEvent.VK_W) {
				p1.switchDirection(-1);
				up1 = true;
			}
			if (key == KeyEvent.VK_S) {
				p1.switchDirection(1);
				down1 = true;
			}
		}

		if(key == KeyEvent.VK_ENTER) {
			if(ballPaused) {
				b.pause(false);
				ballPaused = false;
			}
			else {
				if (isPaused) {
					b.pause(false);
					isPaused = false;
				}
				else {
					isPaused = true;
					p1.stop();
					p2.stop();
					b.pause(true);
				}
			}
		}
		if(key == KeyEvent.VK_R) {
			b.pause(ballPaused);

			p1.reset();
			p1.resetPoint();
			p2.reset();
			p2.resetPoint();
			b.reset();
		}
	}

	
public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			up2 = false;
		}
		if(key == KeyEvent.VK_DOWN) {
			down2 = false;
		}
		if(key == KeyEvent.VK_W) {
			up1 = false;
		}
		if(key == KeyEvent.VK_S) {
			down1 = false;
		}
		
		if(!up1 && !down1) {
			p1.stop();
		}
		if(!up2 && !down2) {
			p2.stop();
		}
		
	}
}
