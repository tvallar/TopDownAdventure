package com.realm.madgod.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.realm.madgod.Game;
import com.realm.madgod.entity.mob.NPC;
import com.realm.madgod.entity.mob.npc.Knight;

public class Building {
	private int width, height;
	private int[] tiles;
	private ArrayList<NPC> people;
	
	
	
	
	public static Building castleSmall = new Building("/textures/Castle.png", new ArrayList<NPC>(Arrays.asList(new Knight(3*Game.TILE_SIZE, 5*Game.TILE_SIZE), new Knight(7*Game.TILE_SIZE, 5*Game.TILE_SIZE)))); // 10 by 10
	public static Building townSmall = new Building("/textures/SmallTown.png", new ArrayList<NPC>(Arrays.asList(new Knight(12*Game.TILE_SIZE, 11*Game.TILE_SIZE), new Knight(16*Game.TILE_SIZE, 11*Game.TILE_SIZE)))); // 30 by 15

	public Building(String path, ArrayList<NPC> people) {
		loadLevel(path);
		this.people = people;
	}
	
	protected void loadLevel(String path) {
		BufferedImage image;
		try {
			image = ImageIO.read(Building.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[] getTiles(){
		return tiles;
	}
	
	public ArrayList<NPC> getPeople() {
		return people;
	}

}
