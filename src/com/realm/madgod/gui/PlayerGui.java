package com.realm.madgod.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.realm.madgod.entity.mob.Player;

public class PlayerGui {
	//Class for drawing the player GUI
	Player p;
	int healthFull;
	int energyFull;
	int width, height;
	Rectangle health, energy;
	//Constructor
	public PlayerGui(Player p, int width, int height) {
		this.p = p;
		healthFull = p.getHealth();
		energyFull = p.getEnergy();
		this.width = width;
		this.height = height;
	}
	//Draw method for the GUI
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.BOLD, 24));
		g.drawString("Health", 50, height-80);
		g.drawString("Energy", 50, height-30);
		g.setColor(Color.GREEN);
		g.fillRect(150, height-120, p.getHealth(), 30);
		g.setColor(Color.YELLOW);
		g.fillRect(150, height-70, p.getEnergy(), 30);
		g.setColor(Color.BLACK);
		g.drawRect(150, height-20, 800, 10);
		g.setColor(Color.MAGENTA);
		
		double a = p.experience*800/p.experienceToLevel;
		//System.out.println(a);
		g.fillRect(150, height-20, (int) a, 10);
	}
	
	
}
