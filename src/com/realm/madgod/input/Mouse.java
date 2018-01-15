package com.realm.madgod.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.realm.madgod.Game;
import com.realm.madgod.graphics.Screen;

public class Mouse implements MouseMotionListener, MouseListener {
	//Holds mouse position and the value of the button currently pressed
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	private static boolean click = false;
	
	public static int getX() {
		return mouseX;
	}
	public static int getY() {
		return mouseY;
	}
	public static int getButton() {
		return mouseB;
	}
	//Has the mouse been pressed?
	public static boolean getClick() {
		if (click) {
			click = false;
			return true;
		} else {
			return false;
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		click = true;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
		//System.out.println(e.getButton());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

}
