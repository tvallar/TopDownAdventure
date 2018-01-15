package com.realm.madgod.level.tile;

import com.realm.madgod.Game;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class SkyTile extends Tile {

	public SkyTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, this);
	}
}


