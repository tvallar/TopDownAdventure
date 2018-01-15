package com.realm.madgod.level.tile;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import com.realm.madgod.Game;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.graphics.SpriteSheet;

public class Tile {
	//public static Rectangle box;
	//Attributes for each tile
	public int x, y;
	public Sprite sprite;
	public int id;
	//Door specific attributes
	protected int keyID = -1;
	protected boolean locked;
	
	//Static instantiation of all tiles where sprite is passed
	public static Tile grassFull = new GrassTile(Sprite.grass0);
	public static Tile dirt = new GrassTile(Sprite.dirt);
	public static Tile water = new SlowTile(Sprite.water);
	public static Tile woodFloor = new GrassTile(Sprite.woodFloor);
	public static Tile stoneFloor = new GrassTile(Sprite.stoneFloor);
	public static Tile orangeStairs = new GrassTile(Sprite.orangeStairs);
	
	public static Tile trash0 = new GrassTile(Sprite.trash0);
	public static Tile trash1 = new GrassTile(Sprite.trash1);
	public static Tile trash2 = new GrassTile(Sprite.trash2);
	public static Tile trash3 = new GrassTile(Sprite.trash3);
	
	//Locked door tiles
	public static Tile beginnerBossDoor = new DoorTile(Sprite.steelDoorUpDown, 0, true);
	public static Tile zone1Shortcut1Door = new DoorTile(Sprite.steelDoorUpDown, 1, true);
	//public static Tile trash3 = new GrassTile(Sprite.trash3);
	
	public static Tile stonePath = new GrassTile(Sprite.stonePath);
	public static Tile stonePath1 = new GrassTile(Sprite.stonePath1);
	public static Tile stonePathBroken = new GrassTile(Sprite.stonePathBroken);
	public static Tile marbleTile = new GrassTile(Sprite.marbleTileFloor);
	public static Tile stoneTile = new GrassTile(Sprite.stoneTileFloor);
	public static Tile tree = new TreeTile(Sprite.tree);
	public static Tile tree_top_left = new TreeTile(Sprite.tree_top_left);
	public static Tile tree_top_right = new TreeTile(Sprite.tree_top_right);
	public static Tile tree_bottom_left = new TreeTile(Sprite.tree_bottom_left);
	public static Tile tree_bottom_right = new TreeTile(Sprite.tree_bottom_right);
	public static Tile shrub = new SlowTile(Sprite.shrub);
	public static Tile sand = new SandTile(Sprite.sand);
	public static Tile VoidTile = new VoidTile(Sprite.tree);
	public static Tile stone = new StoneTile(Sprite.stone);
	public static Tile sky = new SkyTile(Sprite.sky);
	public static Tile houseBack = new SkyTile(Sprite.houseBack);
	
	//Wall Tiles
	public static Tile wallEmpty = new WallTile(Sprite.wallEmpty);
	public static Tile wallAll = new WallTile(Sprite.wallAll);
	public static Tile wallUpDown = new WallTile(Sprite.wallUpDown);
	public static Tile wallLeftRight = new WallTile(Sprite.wallLeftRight);
	public static Tile wallDownRight = new WallTile(Sprite.wallDownRight);
	public static Tile wallDownLeft = new WallTile(Sprite.wallDownLeft);
	public static Tile wallUpLeft = new WallTile(Sprite.wallUpLeft);
	public static Tile wallUpRight = new WallTile(Sprite.wallUpRight);
	public static Tile wallLeftRightDown = new WallTile(Sprite.wallLeftRightDown);
	public static Tile wallLeftRightUp = new WallTile(Sprite.wallLeftRightUp);
	public static Tile wallUpDownRight = new WallTile(Sprite.wallUpDownRight);
	public static Tile wallUpDownLeft = new WallTile(Sprite.wallUpDownLeft);
	public static Tile wallEndRight = new WallTile(Sprite.wallEndRight);
	public static Tile wallEndDown = new WallTile(Sprite.wallEndDown);
	public static Tile wallEndLeft = new WallTile(Sprite.wallEndLeft);
	public static Tile wallEndUp = new WallTile(Sprite.wallEndUp);
	
	//Abyss with grass edge tiles
	public static Tile grassAbyssRight= new WallTile(Sprite.grassAbyssRight);
	public static Tile grassAbyssLeft= new WallTile(Sprite.grassAbyssLeft);
	public static Tile grassAbyssUp= new WallTile(Sprite.grassAbyssUp);
	public static Tile grassAbyssDown= new WallTile(Sprite.grassAbyssDown);
	public static Tile grassAbyssFull= new WallTile(Sprite.grassAbyssFull);
	public static Tile grassAbyssClosedUpRight= new WallTile(Sprite.grassAbyssClosedUpRight);
	public static Tile grassAbyssClosedUpLeft= new WallTile(Sprite.grassAbyssClosedUpLeft);
	public static Tile grassAbyssClosedDownLeft= new WallTile(Sprite.grassAbyssClosedDownLeft);
	public static Tile grassAbyssClosedDownRight= new WallTile(Sprite.grassAbyssClosedDownRight);
	public static Tile grassAbyssOpenUpRight= new WallTile(Sprite.grassAbyssOpenUpRight);
	public static Tile grassAbyssOpenUpLeft= new WallTile(Sprite.grassAbyssOpenUpLeft);
	public static Tile grassAbyssOpenDownLeft= new WallTile(Sprite.grassAbyssOpenDownLeft);
	public static Tile grassAbyssOpenDownRight= new WallTile(Sprite.grassAbyssOpenDownRight);
	
	//Couch tiles
	public static Tile couchLeft = new WallTile(Sprite.couchGreenLeft);
	public static Tile couchMid = new WallTile(Sprite.couchGreenMid);
	public static Tile couchRight = new WallTile(Sprite.couchGreenRight);
	
	//Carpet tiles
	public static Tile carpetTopLeft = new GrassTile(Sprite.carpetTopLeft);
	public static Tile carpetTopMid = new GrassTile(Sprite.carpetTopMid);
	public static Tile carpetTopRight = new GrassTile(Sprite.carpetTopRight);
	public static Tile carpetMiddleLeft = new GrassTile(Sprite.carpetMiddleLeft);
	public static Tile carpetMiddleMid = new GrassTile(Sprite.carpetMiddleMid);
	public static Tile carpetMiddleRight = new GrassTile(Sprite.carpetMiddleRight);
	public static Tile carpetBottomLeft = new GrassTile(Sprite.carpetBottomLeft);
	public static Tile carpetBottomMid = new GrassTile(Sprite.carpetBottomMid);
	public static Tile carpetBottomRight = new GrassTile(Sprite.carpetBottomRight);
	
	//Crate tile
	public static Tile crate = new WallTile(Sprite.crate);
	
	//Roof tiles
	public static Tile roofDownAllConnect = new WallTile(Sprite.roofDownAllConnect);
	public static Tile roofUpAllConnect = new WallTile(Sprite.roofUpAllConnect);
	public static Tile roofMidUpAllConnect = new WallTile(Sprite.roofMidUpAllConnect);
	public static Tile roofDownEdgeRight = new WallTile(Sprite.roofDownEdgeRight);
	public static Tile roofDownEdgeLeft = new WallTile(Sprite.roofDownEdgeLeft);
	public static Tile roofDownEdgeDown = new WallTile(Sprite.roofDownEdgeDown);
	public static Tile roofMidEdgeRight = new WallTile(Sprite.roofMidEdgeRight);
	public static Tile roofMidEdgeLeft = new WallTile(Sprite.roofMidEdgeLeft);
	public static Tile roofDownEdgeRightDown = new WallTile(Sprite.roofDownEdgeRightDown);
	public static Tile roofDownEdgeLeftDown = new WallTile(Sprite.roofDownEdgeLeftDown);
	public static Tile roofUpEdgeRight = new WallTile(Sprite.roofUpEdgeRight);
	public static Tile roofUpEdgeLeft = new WallTile(Sprite.roofUpEdgeLeft);
	public static Tile roofUpEdgeUp = new WallTile(Sprite.roofUpEdgeUp);
	public static Tile roofUpEdgeRightUp = new WallTile(Sprite.roofUpEdgeRightUp);
	public static Tile roofUpEdgeLeftUp = new WallTile(Sprite.roofUpEdgeLeftUp);
	
	
	//Tiles that should have the specified tile type rendered beneath them
	//Wood transparents have wood beneath them, etc.
	public static ArrayList<Tile> woodTransparents = new ArrayList<Tile>(Arrays.asList(couchLeft, couchMid, couchRight, crate));
	public static ArrayList<Tile> grassTransparents = new ArrayList<Tile>(Arrays.asList(crate, stone));
	public static ArrayList<Tile> stoneTransparents = new ArrayList<Tile>(Arrays.asList(trash0, trash1, trash2, trash3, beginnerBossDoor, zone1Shortcut1Door));
	
	//Tile class, contains all methods and attributes needed by a tile
	//Returns false by default for all tile type attributes (like solid, savetile, etc.)
	//Everthing is set in the specific tile type classes
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		//this.x = x;
		//this.y = y;
	}
	
	
	public String toString() {
		return "x and y: " + x + " | " + y;
	}
	
	//Does it stop the player?
	public boolean solid() {
		return false;
	}
	//Does it slow down the player?
	public boolean slower() {
		return false;
	}
	
	//is it a tile that has an inventory? (such as crate)
	public boolean inventory() {
		return false;
	}
	//Is it a door tile?
	public boolean door(){
		return false;
	}
	
	//is it a tile that speeds up movement?
	public boolean fast(){
		return false;
	}
	
	//Return tile id
	public int getId() {
		return id;
	}
	
	//Return the tile sprite
	public Sprite getSprite(){
		return sprite;
	}
	
	//is the tile breakable? (unused for now)
	public boolean breakable() {
		return false;
	}
	
	//If it is a door, is the door locked?
	public boolean locked() {
		return false;
	}
	
	//method to set the door locked or unlocked
	public void setLocked(boolean b) {
		this.locked = b;
	}
	
	//Returns the id of the key that opens the door
	public int getKeyID() {
		return keyID;
	}
	
	//Is it a save tile?
	public boolean saveTile() {
		return false;
	}
	
	
}
