package com.realm.madgod.level.tile;

import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.Game;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;
public class StoneTile extends Tile {

	public StoneTile(Sprite sprite) {
		super(sprite);
		
	}
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, this);
		}
	public boolean fast() {
		return false;
	}
	
	public boolean breakable() {
		return true;
	}
	
	public boolean solid() {
		return true;
	}

}
