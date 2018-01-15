package com.realm.madgod.level.tile;

import com.realm.madgod.Game;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class SaveTile extends Tile {
	private static int curSaveID = 0;
	private int saveID;
	private int x, y;
	public SaveTile(int x, int y) {
		super(Sprite.marbleTileFloor);
		this.saveID = curSaveID;
		curSaveID++;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSaveID() {
		return saveID;
	}
	
	public boolean saveTile() {
		return true;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, this);
	}

}
