package com.realm.madgod.level.tile;

import com.realm.madgod.Game;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class DoorTile extends Tile {
	
	public DoorTile(Sprite sprite, int keyID, boolean locked) {
		super(sprite);
		this.keyID = keyID;
		this.locked = locked;
		// TODO Auto-generated constructor stub
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, this);
	}
	
	public boolean door() {
		return true;
	}
	
	public boolean locked() {
		return locked;
	}
	
	public void setLocked(boolean b) {
		this.locked = b;
	}
	
	public int getKeyID() {
		return keyID;
	}

}
