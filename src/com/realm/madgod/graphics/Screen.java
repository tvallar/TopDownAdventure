package com.realm.madgod.graphics;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import com.realm.madgod.Game;
import com.realm.madgod.entity.mob.Player;
import com.realm.madgod.level.Level;
import com.realm.madgod.level.tile.Tile;

public class Screen {
	// Screen class does all the pixel setting and player movement offsetting
	// contains all the render methods for the specific types of rendering
	
	public int width, height; // Width and height of the pixel array
	public int[] pixels; // array of all the pixels
	public final int MAP_SIZE = 100; // size of the ingame map
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public static int xOffset; // pixel render offset, used to follow the player and determine what part of the map should be rendered
	public static int yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // array of the tiles of the map
	
	
	private Random random = new Random();
	
	//Constructor
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		
		for (int i = 0; i < MAP_SIZE*MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	//Sets all pixels to 0
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	
	//Method for rendering a tile based on the sprite passed in
	public void renderTile(int xp, int yp, Sprite sprite) {
		xp-= xOffset;
		yp -= yOffset;

		
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya>= height) break;
				if (xa < 0) {
					xa = 0;
				}
				
				int col = sprite.pixels[x+y*sprite.SIZE];
				if (col != 0xffff00FF) {
					pixels[xa+ya*width] = col;
				}
				
				//System.out.println(xa + " : " + ya);
				
				
				
			}
		}
		
	}
	
	
	//Renders tile based on the Tile object passed in
	public void renderTile(int xp, int yp, Tile tile) {
		xp-= xOffset;
		yp -= yOffset;

		
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya>= height) break;
				if (xa < 0) {
					xa = 0;
				}
				
				//box = new Rectangle(x, y, 16, 16);
				int col = tile.sprite.pixels[x+y*tile.sprite.SIZE];
				if (Tile.woodTransparents.contains(tile)) {
					if (col == 0xffff00ff) {
						pixels[xa+ya*width] = Tile.woodFloor.sprite.pixels[x+y*tile.sprite.SIZE];
					}
					else pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
				} else if (Tile.grassTransparents.contains(tile)) {
					if (col == 0xffff00ff) {
						pixels[xa+ya*width] = Tile.grassFull.sprite.pixels[x+y*tile.sprite.SIZE];
					}
					else pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
				} else if (Tile.stoneTransparents.contains(tile)) {
					if (col == 0xffff00ff) {
						pixels[xa+ya*width] = Tile.stonePath.sprite.pixels[x+y*tile.sprite.SIZE];
					}
					else pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
				}
				else {
					pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
				}
				
				
				//System.out.println(xa + " : " + ya);
				
				
				
			}
		}
		
	}
	
	//Method for rendering the player
	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp-= xOffset;
		yp -= yOffset;
		pixels[xp + yp*width] = 0xffffff;
		
		for (int y = 0; y < Game.TILE_SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < Game.TILE_SIZE; x++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < 0 || ya>= height) break;
				if (xa < 0) {
					xa = 0;
				}
				int col = sprite.pixels[x+y*Game.TILE_SIZE];
				if (col != 0xFFff00ff) {
				pixels[xa+ya*width] = col;
				}
				
			}
		}
	}
	
	//Renders the Inventory Item boxes
	public void renderInventoryItemBox(int x, int y, int col) {
		x -= xOffset+1;
		y -= yOffset+1;
		for (int yp = 0; yp < Game.TILE_SIZE+2; yp++) {
			int ya = y + yp;
			for (int xp = 0; xp < Game.TILE_SIZE+2; xp++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < 0 || ya>= height) break;
				pixels[xa+ya*width] = col;
			}
		}
	}
	
	
	
	
	
	public void renderCreature(int xp, int yp, Sprite sprite, int health, int maxHealth) {
		xp-= xOffset;
		yp -= yOffset;

		
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya>= height) break;
				if (xa < 0) {
					xa = 0;
				}
				
				//box = new Rectangle(x, y, 16, 16);
				int col = sprite.pixels[x+y*sprite.SIZE];
				if (col != 0xffff00ff) {
				pixels[xa+ya*width] = col;
				}
				//System.out.println(xa + " : " + ya);
				
				
				
			}
		}
		for (int y = 0; y < 5; y++) {
			int ya = y + yp - 10;
			for (int x= 0; x < health*sprite.SIZE/maxHealth; x++) {
				int xa = x + xp - 1;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya>= height) break;
				if (xa < 0) {
					xa = 0;
				}
				pixels[xa + ya*width] = 0xFF42f45f;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		
	}
	
	
	
	
	
	
	
	
	
}
