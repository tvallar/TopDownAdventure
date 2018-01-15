package com.realm.madgod.entity.item;

import com.realm.madgod.entity.Entity;
import com.realm.madgod.entity.mob.Mob;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.level.Level;

//The implementation of the Item type of entity, parent class to specific item types
public class Item extends Entity {
	protected Sprite sprite;
	protected boolean held = false;
	
	//Basic constructor
	public Item(Sprite sprite) {
		this.sprite = sprite;
	}
	
	//Constructor for item with no set position, meant for giving items to players
	public Item(Sprite sprite, Level level, boolean held) {
		this.sprite = sprite;
		this.level = level;
		this.held = held;
	}
	
	//Constructor for placing items on the map
	public Item(Sprite sprite, Level level, int x, int y) {
		this.sprite = sprite;
		this.level = level;
		held = false;
		setPosition(x,y);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void render(Screen screen) {
		if (!held) {
			screen.renderTile(x-8, y-8, sprite);
		}
		
		
	}
	
	public void update() {
		
	}
	
	public void setHeld(boolean b) {
		held = b;
	}
	
	public boolean getHeld() {
		return held;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void useItem() {
		
	}
	
	
}
