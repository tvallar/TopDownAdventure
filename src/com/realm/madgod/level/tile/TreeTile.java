package com.realm.madgod.level.tile;

import com.realm.madgod.Game;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class TreeTile extends Tile {
	public TreeTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, this);
	}
	
	public boolean slower() {
		return true;
	}
	
	public boolean solid() {
		return true;
	}
	public boolean ground() {
		return true;
	}
	
	public boolean inventory() {
		return false;
	}
}
