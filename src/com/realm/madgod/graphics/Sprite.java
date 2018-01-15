package com.realm.madgod.graphics;

import com.realm.madgod.Game;

public class Sprite {
	//Holds the pixels of the sprites used by entities and tiles in the game
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	//All sprites statically defined
	public static Sprite boss1Up = new Sprite(Game.TILE_SIZE*2, 29, 17, SpriteSheet.tiles);
	public static Sprite boss1Left = new Sprite(boss1Up, 2);
	public static Sprite boss1Right = new Sprite(boss1Up, 1);
	public static Sprite boss1Down = new Sprite(boss1Up, 3);
	public static Sprite[] boss1Sprites = {boss1Up, boss1Right, boss1Down, boss1Left};
	
	public static Sprite grass0= new Sprite(Game.TILE_SIZE, 0, 0, SpriteSheet.tiles);
	public static Sprite grass1= new Sprite(Game.TILE_SIZE, 1, 0, SpriteSheet.tiles);
	public static Sprite grass2= new Sprite(Game.TILE_SIZE, 2, 0, SpriteSheet.tiles);
	public static Sprite grass3= new Sprite(Game.TILE_SIZE, 3, 0, SpriteSheet.tiles);
	public static Sprite dirt= new Sprite(Game.TILE_SIZE, 4, 0, SpriteSheet.tiles);
	public static Sprite water = new Sprite(Game.TILE_SIZE, 18, 0, SpriteSheet.tiles);
	public static Sprite woodFloor = new Sprite(Game.TILE_SIZE, 14, 3, SpriteSheet.tiles);
	public static Sprite stoneFloor = new Sprite(Game.TILE_SIZE, 15, 2, SpriteSheet.tiles);
	public static Sprite stoneTileFloor = new Sprite(Game.TILE_SIZE, 10, 0, SpriteSheet.tiles);
	public static Sprite marbleTileFloor = new Sprite(Game.TILE_SIZE, 11, 0, SpriteSheet.tiles);
	public static Sprite stonePath = new Sprite(Game.TILE_SIZE, 8, 0, SpriteSheet.tiles);
	public static Sprite stonePath1 = new Sprite(Game.TILE_SIZE, 7, 0, SpriteSheet.tiles);
	public static Sprite stonePathBroken = new Sprite(Game.TILE_SIZE, 6, 0, SpriteSheet.tiles);
	public static Sprite orangeStairs = new Sprite(Game.TILE_SIZE, 7, 19, SpriteSheet.tiles);
	
	public static Sprite wallEmpty = new Sprite(Game.TILE_SIZE, 15, 13, SpriteSheet.tiles);
	public static Sprite wallAll = new Sprite(Game.TILE_SIZE, 16, 12, SpriteSheet.tiles);
	public static Sprite wallUpDown = new Sprite(Game.TILE_SIZE, 11, 11, SpriteSheet.tiles);
	public static Sprite wallLeftRight = new Sprite(Game.TILE_SIZE, 11, 10, SpriteSheet.tiles);
	public static Sprite wallDownRight = new Sprite(Game.TILE_SIZE, 9, 10, SpriteSheet.tiles);
	public static Sprite wallDownLeft = new Sprite(Game.TILE_SIZE, 10, 10, SpriteSheet.tiles);
	public static Sprite wallUpLeft = new Sprite(Game.TILE_SIZE, 10, 11, SpriteSheet.tiles);
	public static Sprite wallUpRight = new Sprite(Game.TILE_SIZE, 9, 11, SpriteSheet.tiles);
	public static Sprite wallLeftRightDown = new Sprite(Game.TILE_SIZE, 12, 10, SpriteSheet.tiles);
	public static Sprite wallLeftRightUp = new Sprite(Game.TILE_SIZE, 13, 10, SpriteSheet.tiles);
	public static Sprite wallUpDownRight = new Sprite(Game.TILE_SIZE, 12, 11, SpriteSheet.tiles);
	public static Sprite wallUpDownLeft = new Sprite(Game.TILE_SIZE, 13, 11, SpriteSheet.tiles);
	public static Sprite wallEndRight = new Sprite(Game.TILE_SIZE, 15, 11, SpriteSheet.tiles);
	public static Sprite wallEndDown = new Sprite(Game.TILE_SIZE, 14, 11, SpriteSheet.tiles);
	public static Sprite wallEndLeft = new Sprite(Game.TILE_SIZE, 14, 10, SpriteSheet.tiles);
	public static Sprite wallEndUp = new Sprite(Game.TILE_SIZE, 15, 10, SpriteSheet.tiles);
	
	public static Sprite grassAbyssRight= new Sprite(Game.TILE_SIZE, 0, 1, SpriteSheet.tiles);
	public static Sprite grassAbyssLeft= new Sprite(Game.TILE_SIZE, 2, 1, SpriteSheet.tiles);
	public static Sprite grassAbyssUp= new Sprite(Game.TILE_SIZE, 1, 2, SpriteSheet.tiles);
	public static Sprite grassAbyssDown= new Sprite(Game.TILE_SIZE, 2, 2, SpriteSheet.tiles);
	public static Sprite grassAbyssFull= new Sprite(Game.TILE_SIZE, 1, 1, SpriteSheet.tiles);
	public static Sprite grassAbyssClosedUpRight= new Sprite(Game.TILE_SIZE, 0, 2, SpriteSheet.tiles);
	public static Sprite grassAbyssClosedUpLeft= new Sprite(Game.TILE_SIZE, 0, 3, SpriteSheet.tiles);
	public static Sprite grassAbyssClosedDownLeft= new Sprite(Game.TILE_SIZE, 3, 2, SpriteSheet.tiles);
	public static Sprite grassAbyssClosedDownRight= new Sprite(Game.TILE_SIZE, 4, 2, SpriteSheet.tiles);
	public static Sprite grassAbyssOpenUpRight= new Sprite(Game.TILE_SIZE, 2, 3, SpriteSheet.tiles);
	public static Sprite grassAbyssOpenUpLeft= new Sprite(Game.TILE_SIZE, 1, 3, SpriteSheet.tiles);
	public static Sprite grassAbyssOpenDownLeft= new Sprite(Game.TILE_SIZE, 3, 3, SpriteSheet.tiles);
	public static Sprite grassAbyssOpenDownRight= new Sprite(Game.TILE_SIZE, 4, 3, SpriteSheet.tiles);
	
	
	
	public static Sprite steelDoorUpDown = new Sprite(Game.TILE_SIZE, 12, 18, SpriteSheet.tiles);
	public static Sprite woodDoorUpDown = new Sprite(Game.TILE_SIZE, 12, 16, SpriteSheet.tiles);
	
	public static Sprite sand = new Sprite(Game.TILE_SIZE, 16, 0, SpriteSheet.tiles);
	public static Sprite VoidSprite = new Sprite(Game.TILE_SIZE, 0x0079F2);
	public static Sprite stone = new Sprite(Game.TILE_SIZE, 27, 16, SpriteSheet.tiles);
	public static Sprite sky = new Sprite(Game.TILE_SIZE, 19, 6, SpriteSheet.tiles);
	public static Sprite houseBack = new Sprite(Game.TILE_SIZE, 12, 6, SpriteSheet.tiles);
	
	public static Sprite tree = new Sprite(Game.TILE_SIZE, 20, 6, SpriteSheet.tiles);
	public static Sprite tree_top_left = new Sprite(Game.TILE_SIZE, 18, 6, SpriteSheet.tiles);
	public static Sprite tree_top_right = new Sprite(Game.TILE_SIZE, 19, 6, SpriteSheet.tiles);
	public static Sprite tree_bottom_left = new Sprite(Game.TILE_SIZE, 18, 7, SpriteSheet.tiles);
	public static Sprite tree_bottom_right = new Sprite(Game.TILE_SIZE, 19, 7, SpriteSheet.tiles);
	public static Sprite shrub = new Sprite(Game.TILE_SIZE, 20, 7, SpriteSheet.tiles);
	
	public static Sprite trash0 = new Sprite(Game.TILE_SIZE, 21, 9, SpriteSheet.tiles);
	public static Sprite trash1 = new Sprite(Game.TILE_SIZE, 22, 9, SpriteSheet.tiles);
	public static Sprite trash2 = new Sprite(Game.TILE_SIZE, 21, 10, SpriteSheet.tiles);
	public static Sprite trash3 = new Sprite(Game.TILE_SIZE, 22, 10, SpriteSheet.tiles);
	
	public static Sprite couchGreenLeft = new Sprite(Game.TILE_SIZE, 14, 16, SpriteSheet.tiles);
	public static Sprite couchGreenMid = new Sprite(Game.TILE_SIZE, 15, 16, SpriteSheet.tiles);
	public static Sprite couchGreenRight = new Sprite(Game.TILE_SIZE, 16, 16, SpriteSheet.tiles);
	
	public static Sprite carpetTopLeft = new Sprite(Game.TILE_SIZE, 21, 13, SpriteSheet.tiles);
	public static Sprite carpetTopMid = new Sprite(Game.TILE_SIZE, 22, 13, SpriteSheet.tiles);
	public static Sprite carpetTopRight = new Sprite(Game.TILE_SIZE, 23, 13, SpriteSheet.tiles);
	public static Sprite carpetMiddleLeft = new Sprite(Game.TILE_SIZE, 21, 14, SpriteSheet.tiles);
	public static Sprite carpetMiddleMid = new Sprite(Game.TILE_SIZE, 22, 14, SpriteSheet.tiles);
	public static Sprite carpetMiddleRight = new Sprite(Game.TILE_SIZE, 23, 14, SpriteSheet.tiles);
	public static Sprite carpetBottomLeft = new Sprite(Game.TILE_SIZE, 21, 15, SpriteSheet.tiles);
	public static Sprite carpetBottomMid = new Sprite(Game.TILE_SIZE, 22, 15, SpriteSheet.tiles);
	public static Sprite carpetBottomRight = new Sprite(Game.TILE_SIZE, 23, 15, SpriteSheet.tiles);
	
	public static Sprite roofDownAllConnect = new Sprite(Game.TILE_SIZE, 25, 16, SpriteSheet.tiles);
	public static Sprite roofUpAllConnect = new Sprite(roofDownAllConnect, 4);
	public static Sprite roofMidUpAllConnect = new Sprite(Game.TILE_SIZE, 26, 16, SpriteSheet.tiles);
	
	public static Sprite roofDownEdgeRight = new Sprite(Game.TILE_SIZE, 24, 16, SpriteSheet.tiles);
	public static Sprite roofDownEdgeLeft = new Sprite(Game.TILE_SIZE, 23, 16, SpriteSheet.tiles);
	public static Sprite roofDownEdgeDown = new Sprite(Game.TILE_SIZE, 25, 17, SpriteSheet.tiles);
	public static Sprite roofMidEdgeRight = new Sprite(Game.TILE_SIZE, 24, 17, SpriteSheet.tiles);
	public static Sprite roofMidEdgeLeft = new Sprite(Game.TILE_SIZE, 23, 17, SpriteSheet.tiles);
	public static Sprite roofDownEdgeRightDown = new Sprite(Game.TILE_SIZE, 24, 18, SpriteSheet.tiles);
	public static Sprite roofDownEdgeLeftDown = new Sprite(Game.TILE_SIZE, 23, 18, SpriteSheet.tiles);
	public static Sprite roofUpEdgeRight = new Sprite(roofDownEdgeLeft, 4);
	public static Sprite roofUpEdgeLeft = new Sprite(roofDownEdgeRight, 4);
	public static Sprite roofUpEdgeUp = new Sprite(roofDownEdgeDown, 4);
	public static Sprite roofUpEdgeRightUp = new Sprite(roofDownEdgeLeftDown, 4);
	public static Sprite roofUpEdgeLeftUp = new Sprite(roofDownEdgeRightDown, 4);
	
	
	public static Sprite crate = new Sprite(Game.TILE_SIZE, 20, 4, SpriteSheet.tiles);
	
	public static Sprite key = new Sprite(Game.TILE_SIZE, 29, 19, SpriteSheet.tiles);
	
	
	
	public static Sprite projectile1 = new Sprite(Game.TILE_SIZE, 24, 7, SpriteSheet.tiles);
	public static Sprite bombProjectile = new Sprite(Game.TILE_SIZE, 24, 6, SpriteSheet.tiles);
	public static Sprite hitAnim = new Sprite(Game.TILE_SIZE, 28, 18, SpriteSheet.tiles);
	
	public static Sprite starterArmorHead = new Sprite(Game.TILE_SIZE, 27, 17, SpriteSheet.tiles);
	public static Sprite starterArmorChest = new Sprite(Game.TILE_SIZE, 27, 18, SpriteSheet.tiles);
	public static Sprite starterArmorLegs = new Sprite(Game.TILE_SIZE, 27, 19, SpriteSheet.tiles);
	public static Sprite starterArmorFeet = new Sprite(Game.TILE_SIZE, 28, 19, SpriteSheet.tiles);
	
	public static Sprite chaosBlade = new Sprite(Game.TILE_SIZE, 28, 17, SpriteSheet.tiles);
	public static Sprite longSword1 = new Sprite(Game.TILE_SIZE, 26, 17, SpriteSheet.tiles);
	public static Sprite brokenBlade = new Sprite(Game.TILE_SIZE, 27, 14, SpriteSheet.tiles);
	public static Sprite orangeKnightSword = new Sprite(Game.TILE_SIZE, 27, 15, SpriteSheet.tiles);
	
	public static Sprite inventoryTile = new Sprite(Game.TILE_SIZE, 25, 18, SpriteSheet.tiles);
	
	public static Sprite healthPotion = new Sprite(Game.TILE_SIZE, 26, 18, SpriteSheet.tiles);
	public static Sprite staminaPotion = new Sprite(Game.TILE_SIZE, 26, 19, SpriteSheet.tiles);
	public static Sprite damagePotion = new Sprite(Game.TILE_SIZE, 25, 19, SpriteSheet.tiles);
	public static Sprite invZero = new Sprite(Game.TILE_SIZE, 26, 0, SpriteSheet.tiles);
	public static Sprite invOne = new Sprite(Game.TILE_SIZE, 26, 1, SpriteSheet.tiles);
	public static Sprite invTwo = new Sprite(Game.TILE_SIZE, 26, 2, SpriteSheet.tiles);
	public static Sprite invThree = new Sprite(Game.TILE_SIZE, 26, 3, SpriteSheet.tiles);
	public static Sprite invFour = new Sprite(Game.TILE_SIZE, 26, 4, SpriteSheet.tiles);
	public static Sprite invFive = new Sprite(Game.TILE_SIZE, 26, 5, SpriteSheet.tiles);
	
	public static Sprite tileEditHammer = new Sprite(Game.TILE_SIZE, 26, 6, SpriteSheet.tiles);
	public static Sprite invSelect = new Sprite(Game.TILE_SIZE, 26, 7, SpriteSheet.tiles);
	
	//Green Enemy
	public static Sprite enemy1_right = new Sprite(Game.TILE_SIZE, 28, 6, SpriteSheet.tiles);
	public static Sprite enemy1_up = new Sprite(enemy1_right, 2);
	public static Sprite enemy1_left = new Sprite(enemy1_right,3);
	public static Sprite enemy1_down = new Sprite(enemy1_right, 1);
	public static Sprite enemy1_right_1 = new Sprite(Game.TILE_SIZE, 28, 6, SpriteSheet.tiles);
	public static Sprite enemy1_right_2 = new Sprite(Game.TILE_SIZE, 29, 6, SpriteSheet.tiles);
	public static Sprite enemy1_forward_1 = new Sprite(enemy1_right_1, 2);
	public static Sprite enemy1_forward_2 = new Sprite(enemy1_right_2, 2);
	public static Sprite enemy1_back_1 = new Sprite(enemy1_right_1, 1);
	public static Sprite enemy1_back_2 = new Sprite(enemy1_right_2, 1);
	public static Sprite enemy1_left_1 = new Sprite(enemy1_right_1, 3);
	public static Sprite enemy1_left_2 = new Sprite(enemy1_right_2, 3);
	
	//Orange Knight
	public static Sprite enemy2_right = new Sprite(Game.TILE_SIZE, 28, 11, SpriteSheet.tiles);
	public static Sprite enemy2_up = new Sprite(enemy2_right, 2);
	public static Sprite enemy2_left = new Sprite(enemy2_right,3);
	public static Sprite enemy2_down = new Sprite(enemy2_right, 1);
	public static Sprite enemy2_right_1 = new Sprite(Game.TILE_SIZE, 28, 11, SpriteSheet.tiles);
	public static Sprite enemy2_right_2 = new Sprite(Game.TILE_SIZE, 29, 11, SpriteSheet.tiles);
	public static Sprite enemy2_forward_1 = new Sprite(enemy2_right_1, 2);
	public static Sprite enemy2_forward_2 = new Sprite(enemy2_right_2, 2);
	public static Sprite enemy2_back_1 = new Sprite(enemy2_right_1, 1);
	public static Sprite enemy2_back_2 = new Sprite(enemy2_right_2, 1);
	public static Sprite enemy2_left_1 = new Sprite(enemy2_right_1, 3);
	public static Sprite enemy2_left_2 = new Sprite(enemy2_right_2, 3);
	
	//Green knight - friendly
	public static Sprite knight_right = new Sprite(Game.TILE_SIZE, 28, 12, SpriteSheet.tiles);
	public static Sprite knight_up = new Sprite(knight_right, 2);
	public static Sprite knight_left = new Sprite(knight_right,3);
	public static Sprite knight_down = new Sprite(knight_right, 1);
	public static Sprite knight_right_1 = new Sprite(Game.TILE_SIZE, 28, 12, SpriteSheet.tiles);
	public static Sprite knight_right_2 = new Sprite(Game.TILE_SIZE, 29, 12, SpriteSheet.tiles);
	public static Sprite knight_forward_1 = new Sprite(knight_right_1, 2);
	public static Sprite knight_forward_2 = new Sprite(knight_right_2, 2);
	public static Sprite knight_back_1 = new Sprite(knight_right_1, 1);
	public static Sprite knight_back_2 = new Sprite(knight_right_2, 1);
	public static Sprite knight_left_1 = new Sprite(knight_right_1, 3);
	public static Sprite knight_left_2 = new Sprite(knight_right_2, 3);
	
	//Player sprites
	public static Sprite player_right = new Sprite(Game.TILE_SIZE, 28, 0, SpriteSheet.tiles);
	public static Sprite player_up = new Sprite(player_right, 2);
	public static Sprite player_left = new Sprite(player_right, 3);
	public static Sprite player_down = new Sprite(player_right, 1);
	
	public static Sprite player_right_1 = new Sprite(Game.TILE_SIZE, 28, 0, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(Game.TILE_SIZE, 29, 0, SpriteSheet.tiles);
	
	public static Sprite player_forward_1 = new Sprite(player_right_1, 2);
	public static Sprite player_forward_2 = new Sprite(player_right_2, 2);
	
	public static Sprite player_back_1 = new Sprite(player_right_1, 1);
	public static Sprite player_back_2 = new Sprite(player_right_2, 1);
	
	public static Sprite player_left_1 = new Sprite(player_right_1, 3);
	public static Sprite player_left_2 = new Sprite(player_right_2, 3);
	
	
	//The spritesheet is the file that contains all of the sprites
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * (16+1);
		this.y = y * (16+1);
		this.sheet = sheet;
		
		load();
	}
	//Define a sprite as a rotation of a different, already defined sprite
	public Sprite(Sprite sprite, int rotation) {
		// 0 == reg, 1 == rotate right 90, 2 == rotate left 90, 3 == reflect, 4 == 180
		SIZE = sprite.SIZE;
		pixels = new int[SIZE*SIZE];
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				if (rotation == 1) pixels[x + y*SIZE] = sprite.pixels[y + (SIZE - x -1) *SIZE];
				if (rotation == 2) pixels[x + y*SIZE] = sprite.pixels[(SIZE-y -1) + x*SIZE];
				if (rotation == 3) pixels[x + y*SIZE] = sprite.pixels[(SIZE-x-1) + y * SIZE];
				if (rotation == 4) pixels[x + y*SIZE] = sprite.pixels[(SIZE-x-1) + (SIZE - y - 1) * SIZE];
			}
		}
	}
	
	
	
	
	public Sprite (int size, int color){
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color){
		for (int i = 0; i < SIZE*SIZE; i++){
			pixels[i] = color;
		}
	}
	//Retreive the pixels from the spritesheet
	public void load() {
		for ( int y = 0; y < SIZE; y++) {
			for ( int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y)* sheet.WIDTH];
			}
		}
	}
}
