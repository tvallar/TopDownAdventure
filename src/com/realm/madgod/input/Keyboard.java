package com.realm.madgod.input;

import com.realm.madgod.Game;
import com.realm.madgod.graphics.Screen;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Keyboard implements KeyListener, MouseListener, MouseMotionListener{
	//Keyboard class, holds info on all keys used by the game
	private boolean[] keys = new boolean[65536];
	public boolean up, down, left, right, space, one, two, three, four, five, shift, inv, use;
	int k;
	
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		three = keys[KeyEvent.VK_3];
		four = keys[KeyEvent.VK_4];
		five = keys[KeyEvent.VK_5];
		shift = keys[KeyEvent.VK_SHIFT];
		inv = keys[KeyEvent.VK_I];
		use = keys[KeyEvent.VK_E];
		
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		k = e.getKeyCode();
	}


	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}


	public void keyTyped(KeyEvent e) {
		
	}
	
	
	public void mousePressed(MouseEvent e) {
		
	}


	public void mouseReleased(MouseEvent e) {
			
	}


	public void mouseDragged(MouseEvent e) {
		//Screen.mse = new Point(e.getX(), e.getY());
	}


	public void mouseMoved(MouseEvent e) {
		//Screen.mse = new Point(e.getX(), e.getY());
	}

@Override
	public void mouseClicked(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
	
}
