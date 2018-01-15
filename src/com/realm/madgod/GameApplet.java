package com.realm.madgod;

import java.applet.Applet;
import java.awt.BorderLayout;



public class GameApplet extends Applet{
	private static final long serialVersionUID = 1L;
	
	private Game display = new Game();
	
	public void init(){
		setLayout(new BorderLayout());
		add(display);
	}
	
	public void start() {
		display.start();
	}
	
	public void stop() {
		display.stop();
	}
}


