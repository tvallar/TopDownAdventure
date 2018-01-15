package com.realm.madgod.level.tile;

import com.realm.madgod.Game;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class SlowTile extends Tile{

		public SlowTile(Sprite sprite) {
			super(sprite);
			
		}
		
		public void render(int x, int y, Screen screen) {
			screen.renderTile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, this);
		}
		
		public boolean slower() {
			return true;
		}
		
}
