package com.realm.madgod.entity;

import java.util.Random;

import com.realm.madgod.graphics.Screen;
import com.realm.madgod.level.Level;

public abstract class Entity {
	//The entity class is a framework for all things in the game that are dynamic
	//Such as enemies, npcs, projectiles, and items
	//Includes everything that entities in the game need
	public int x;
	public int y;
	public int id;
	private boolean removed = false;
	protected static Level level;
	protected final static Random random = new Random();
	
	
	//Blank update method, is implemented in children classes
	public void update() {
		
	}
	
	//Bank render method, implemented in children classes
	public void render(Screen screen){
		
	}
	
	public void remove() {
		//Remove From Level
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	public int getID() {
		return id;
	}

	
}
