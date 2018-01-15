package com.realm.madgod.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.realm.madgod.Game;
import com.realm.madgod.entity.Entity;
import com.realm.madgod.entity.item.Item;
import com.realm.madgod.entity.item.Key;
import com.realm.madgod.entity.item.weapons.Weapon;
import com.realm.madgod.entity.mob.Creature;
import com.realm.madgod.entity.mob.Mob;
import com.realm.madgod.entity.mob.NPC;
import com.realm.madgod.entity.mob.creature.GenerateCreatures;
import com.realm.madgod.entity.mob.creature.Path;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.input.Mouse;
import com.realm.madgod.level.tile.GrassTile;
import com.realm.madgod.level.tile.SaveTile;
import com.realm.madgod.level.tile.Tile;
import com.realm.madgod.level.tile.WallTile;

public class Level {
	// Class that contains all the info about the level and all methods related to random map generation
	
	
	protected int width, height; //width and height of the map
	protected int[] tiles; // Contains all the tiles on the map
	protected int[] usedTiles; // contains a list of tiles used in the random map generation process that should not be written over
	protected int[] tilesInt; // Used in random level generation

	private List<Entity> entities = new ArrayList<Entity>(); // Array list containing all entities in the level
	protected List<Mob> beings = new ArrayList<Mob>(); // arraylist containing all beings (mobs, NPC's etc.)
	protected ArrayList<Item> items = new ArrayList<Item>(); // Contains all items in the level, held or otherwise
	
	public int id; // Level id, will be used when multiple levels are implemented
	
	public static int idCount = 0; //static in that keeps track of the next available id
	
	public static Level currentLev; // Holds the level currently being shown
	public static ArrayList<Level> levels = new ArrayList<Level>(); // Arraylist of all levels in the game
	
	public static ArrayList<SaveTile> saveTiles = new ArrayList<SaveTile>(); // Arraylist of all the save tiles in the map
	public int saveTileID = 0;
	
	private int xTile; //x-pos of the tile the mouse pointer is over
	private int yTile; //y-pos of the tile the mouse pointer is over
	private int buildTile; //Which tile type to replace with
	boolean levelEdit = false; //Is the player in level editing mode
	
	Random r;
	//Constructor with no file to load map from
	public Level(int width, int height) {
		this.height = height;
		this.width = width;
		this.id = idCount;
		idCount++;
	}
	
	public List<Mob> getBeings() {
		return beings;
	}

	public void setBeings(List<Mob> beings) {
		this.beings = beings;
	}

	
	//Constructor that takes in a filename to a level file
	public Level(String path) {
		r = new Random();
		loadLevel(path);
		this.id = idCount;
		idCount++;
		
		
		
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	protected void generateLevel() {
		
	}
	
	public void generateCreatures() {
		
	}
	
	public void generateItems() {
		
	}
	
	protected void loadLevel(String path) {
		
	}
	
	//Places a building on the map, retrieves the tile array and NPCs / mobs that should be placed inside the building
	protected void constructBuilding(int index, Building b) {
		for (int y = 0; y < b.getHeight(); y++) {
			for (int x =0; x < b.getWidth(); x++) {
				this.tiles[index + x + y*this.width] = b.getTiles()[x+y*b.getWidth()];
			
			}
		}
		
		for (int i = 0; i < b.getPeople().size(); i++) {
			int pX = b.getPeople().get(i).x;
			int pY = b.getPeople().get(i).y;
			int wX = (index%width)*Game.TILE_SIZE;
			int wY = (index/width)*Game.TILE_SIZE;
			b.getPeople().get(i).setPos(wX+pX+8, wY+pY+8);
			add(b.getPeople().get(i));
		}
	}
	
	boolean init = false;
	//Update method for the level - updates all entities currently on the level if they are not removed
	public void update() {
		if (!init) {
			for (int i = 0; i < Key.keys.size(); i++) {
				add(Key.keys.get(i));
			}
		}
		for(int i = 0; i < entities.size(); i++){
			if (!entities.get(i).isRemoved())entities.get(i).update();
		
		}
		//Gets the xtile and ytile positions of the mouse
		xTile = getMouseXTilePos();
		yTile = getMouseYTilePos();
		//if pointer is on screen, update tiles
		if (xTile+yTile*width >= 0 && xTile+yTile*width < tiles.length)
			updateTiles();
		
		
	}
	
	public void setSavePoint(int id){
		this.saveTileID = id;
	}
	
	public SaveTile getSaveTile() {
		return saveTiles.get(this.saveTileID);
	}
	
	//Calculates x-tile position of mouse
	public int getMouseXTilePos() {
		return (Screen.xOffset + Mouse.getX()/3)/Game.TILE_SIZE;
	}
	
	//calculates y-tile position of mouse
	public int getMouseYTilePos() {
		return (Screen.yOffset + Mouse.getY()/3)/Game.TILE_SIZE;
	}
	
	//Method for changing tiles
	//These variables are used in the case the tile is not placed, so it can revert back to the tile there previously
	int oldTileX = 0; // oldTile x position
	int oldTileY = 0; // oldTile y position
	int oldTileSet; // old tile type
	int oldBuildTile = buildTile;
	boolean set = false; // should the tile be placed?
	public void updateTiles() {
		//Called in player class when the selected action is set to build
		//oldTileSet = tiles[oldTileX+oldTileY*width];
		if (levelEdit) {
			//System.out.println(oldTileX + " | " +oldTileY + " | " + xTile + " | " + yTile + " || " +oldTileSet + " : " + tiles[xTile+yTile*width]) ;
			// check to see if the pointer has moved from the tile
			if (oldTileX != xTile || oldTileY != yTile || oldBuildTile !=buildTile) {
				oldBuildTile = buildTile;
				// if the tile should be placed, set oldTileSet to the build tile type
				if (set) {
					oldTileSet = buildTile;
					set = false;
				}
				//Either replace tile with previous or new if set was true
				tiles[oldTileX+oldTileY*width] = oldTileSet;
				oldTileSet = tiles[xTile + yTile*width];
				oldTileX = xTile;
				oldTileY = yTile;
				tiles[xTile+yTile*width] = buildTile;
				
				
			}
		} else {
			//Otherwise, if level edit is not true, make sure tiles are set back to previous
			if (tiles[oldTileX+oldTileY*width] != oldTileSet) tiles[oldTileX + oldTileY*width] = oldTileSet;
		}
	}
	
	//Method that can be used by the player class to set the tile in places
	public void setBlock() {
		set = true;
	}
	
	//Set level edit, used when player switches to build inventory
	public void setLevelEdit(boolean b) {
		levelEdit = b;
	}
	
	//Set the tile that will be built, color corresponds to the tile type
	public void setBuildTile(int color) {
		buildTile = color;
	}
	
	private void time(){
		
	}
	//check if the tile at the position is solid
	public boolean tileCollision(int x, int y, int xa, int ya, int size) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x +xa)+ c % 2 *size) / Game.TILE_SIZE;
			int yt = ((y+ya)+ c / 2 * size) /Game.TILE_SIZE;
			if (getTile(xt, yt).solid()) {
				solid = true;
			}
		}
		return solid;
	}
	
	//Render the map based on the offsets
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = (xScroll-Game.TILE_SIZE) /Game.TILE_SIZE;
		int x1 = (xScroll + screen.width + Game.TILE_SIZE) /Game.TILE_SIZE;
		int y0 = (yScroll-Game.TILE_SIZE) /Game.TILE_SIZE;
		int y1 = (yScroll + screen.height + Game.TILE_SIZE) /Game.TILE_SIZE;
		//First render the tiles
		for (int y = y0; y<y1; y++){
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
				
				
			}
		}
		//Then render the entities (if they are not removed)
		for(int i = 0; i < entities.size(); i++){
			if (!entities.get(i).isRemoved()) entities.get(i).render(screen);
			
		}
	}
	
	//Add an entitiy to the map
	//Check if the entity should be added to the specific lists in the level
	public boolean add(Entity e) {
		if (entities.contains(e)) return false;
		entities.add(e);
		if (e instanceof Item) {
			if (e instanceof Key) System.out.println("Test");
			items.add((Item) e);
		}
		if (e instanceof Creature || e instanceof NPC) {
			beings.add((Mob) e);
		}
		return true;
	}
	
	//Returns the savetile at that position, if not added yet, add to the list of savetiles
	public SaveTile getSaveTile(int x, int y) {
		for (int i = 0; i < saveTiles.size(); i++) {
			SaveTile temp = saveTiles.get(i);
			if (temp.getX() == x*16 && temp.getY() == y*16){
				return temp;
			}
		}
		SaveTile temp = new SaveTile(x*16, y*16);
		saveTiles.add(temp);
		System.out.println(saveTiles.size());
		return temp;
		
	}
	
	public int[] getTiles() {
		return tiles;
	}
	//0xFF6D6D6D
	//Returns the tile type based on the value in the tiles array at the position x and y
	public Tile getTile(int x, int y) {
		//System.out.println(x + " | " + y + " | " + width + " | " + height);
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.VoidTile;
		
		if (tiles[x + y * width] == 0xFF00FF00) return Tile.grassFull;
		if (tiles[x + y * width] == 0xFF00FF01) return Tile.dirt;
		if (tiles[x + y * width] == 0xFF9bff00) return Tile.water;
		if (tiles[x + y * width] == 0xFFe1fb00) return Tile.woodFloor;
		if (tiles[x + y * width] == 0xFFe1fbab) return Tile.stone;
		if (tiles[x + y * width] == 0xFF00FFFF) return Tile.stonePath;
		if (tiles[x + y * width] == 0xFF00FFF1) return Tile.stonePath1;
		if (tiles[x + y * width] == 0xFF00FFF2) return Tile.stonePathBroken;
		if (tiles[x + y * width] == 0xFF00FFF3) return Tile.trash0;
		if (tiles[x + y * width] == 0xFF00FFF4) return Tile.trash1;
		if (tiles[x + y * width] == 0xFF00FFF5) return Tile.trash2;
		if (tiles[x + y * width] == 0xFF00FFF6) return Tile.trash3;
		if (tiles[x + y * width] == 0xFFffb619) return Tile.orangeStairs;
		if (tiles[x + y * width] == 0xFFb1b1b1) return Tile.stoneTile;
		if (tiles[x + y * width] == 0xFFb1b1c1) return Tile.marbleTile;
		if (tiles[x + y * width] == 0xFFb4dbe8) return Tile.stoneFloor;
		
		if (tiles[x + y * width] == 0xFFb1b1c2) {
			return getSaveTile(x, y);
		}
		
		if (tiles[x + y * width] == 0xFF5aa78f) return Tile.beginnerBossDoor;
		if (tiles[x + y * width] == 0xFF5aa78c) return Tile.zone1Shortcut1Door;
		
		if (tiles[x + y * width] == 0xFF00FFaa) return Tile.wallEmpty;
		if (tiles[x + y * width] == 0xFF00FFab) return Tile.wallEndRight;
		if (tiles[x + y * width] == 0xFF00FFac) return Tile.wallEndLeft;
		if (tiles[x + y * width] == 0xFF00FFad) return Tile.wallEndUp;
		if (tiles[x + y * width] == 0xFF00FFae) return Tile.wallEndDown;
		if (tiles[x + y * width] == 0xFF00FFaf) return Tile.wallLeftRight;
		if (tiles[x + y * width] == 0xFF00FFba) return Tile.wallUpDown;
		if (tiles[x + y * width] == 0xFF00FFbb) return Tile.wallDownRight;
		if (tiles[x + y * width] == 0xFF00FFbc) return Tile.wallDownLeft;
		if (tiles[x + y * width] == 0xFF00FFbd) return Tile.wallUpLeft;
		if (tiles[x + y * width] == 0xFF00FFbe) return Tile.wallUpRight;
		if (tiles[x + y * width] == 0xFF00FFbf) return Tile.wallLeftRightDown;
		if (tiles[x + y * width] == 0xFF00FFca) return Tile.wallLeftRightUp;
		if (tiles[x + y * width] == 0xFF00FFcb) return Tile.wallUpDownRight;
		if (tiles[x + y * width] == 0xFF00FFcd) return Tile.wallUpDownLeft;
		if (tiles[x + y * width] == 0xFF00FFce) return Tile.wallAll;
		
		if (tiles[x + y * width] == 0xFF7f7f00) return Tile.sand;
		if (tiles[x + y * width] == 0xFF927807) return Tile.tree;
		if (tiles[x + y * width] == 0xFF35413a) return Tile.shrub;
		
		if (tiles[x + y * width] == 0xFF35413b) return Tile.tree_top_left;
		if (tiles[x + y * width] == 0xFF35413c) return Tile.tree_top_right;
		if (tiles[x + y * width] == 0xFF35413d) return Tile.tree_bottom_left;
		if (tiles[x + y * width] == 0xFF35413e) return Tile.tree_bottom_right;
		
		if (tiles[x + y * width] == 0xFF35414b) return Tile.couchLeft;
		if (tiles[x + y * width] == 0xFF35414c) return Tile.couchMid;
		if (tiles[x + y * width] == 0xFF35414d) return Tile.couchRight;
		
		if (tiles[x + y * width] == 0xFFabcd01) return Tile.carpetTopLeft;
		if (tiles[x + y * width] == 0xFFabcd02) return Tile.carpetTopMid;
		if (tiles[x + y * width] == 0xFFabcd03) return Tile.carpetTopRight;
		if (tiles[x + y * width] == 0xFFabcd04) return Tile.carpetMiddleLeft;
		if (tiles[x + y * width] == 0xFFabcd05) return Tile.carpetMiddleMid;
		if (tiles[x + y * width] == 0xFFabcd06) return Tile.carpetMiddleRight;
		if (tiles[x + y * width] == 0xFFabcd07) return Tile.carpetBottomLeft;
		if (tiles[x + y * width] == 0xFFabcd08) return Tile.carpetBottomMid;
		if (tiles[x + y * width] == 0xFFabcd09) return Tile.carpetBottomRight;
		
		if (tiles[x + y * width] == 0xFFaaaa01) return Tile.grassAbyssRight;
		if (tiles[x + y * width] == 0xFFaaaa02) return Tile.grassAbyssLeft;
		if (tiles[x + y * width] == 0xFFaaaa03) return Tile.grassAbyssUp;
		if (tiles[x + y * width] == 0xFFaaaa04) return Tile.grassAbyssDown;
		if (tiles[x + y * width] == 0xFFaaaa05) return Tile.grassAbyssFull;
		if (tiles[x + y * width] == 0xFFaaaa06) return Tile.grassAbyssClosedUpRight;
		if (tiles[x + y * width] == 0xFFaaaa07) return Tile.grassAbyssClosedUpLeft;
		if (tiles[x + y * width] == 0xFFaaaa08) return Tile.grassAbyssClosedDownLeft;
		if (tiles[x + y * width] == 0xFFaaaa09) return Tile.grassAbyssClosedDownRight;
		if (tiles[x + y * width] == 0xFFaaaa11) return Tile.grassAbyssOpenUpRight;
		if (tiles[x + y * width] == 0xFFaaaa12) return Tile.grassAbyssOpenUpLeft;
		if (tiles[x + y * width] == 0xFFaaaa13) return Tile.grassAbyssOpenDownLeft;
		if (tiles[x + y * width] == 0xFFaaaa14) return Tile.grassAbyssOpenDownRight;
		
		if (tiles[x + y * width] == 0xFF3541aa) return Tile.roofDownAllConnect;
		if (tiles[x + y * width] == 0xFF3541ab) return Tile.roofUpAllConnect;
		if (tiles[x + y * width] == 0xFF3541ac) return Tile.roofMidUpAllConnect;
		if (tiles[x + y * width] == 0xFF3541ce) return Tile.roofDownEdgeRight;
		if (tiles[x + y * width] == 0xFF3541ad) return Tile.roofDownEdgeLeft;
		if (tiles[x + y * width] == 0xFF3541ae) return Tile.roofDownEdgeDown;
		if (tiles[x + y * width] == 0xFF3541af) return Tile.roofMidEdgeRight;
		if (tiles[x + y * width] == 0xFF3541ba) return Tile.roofMidEdgeLeft;
		if (tiles[x + y * width] == 0xFF3541bb) return Tile.roofDownEdgeRightDown;
		if (tiles[x + y * width] == 0xFF3541bc) return Tile.roofDownEdgeLeftDown;
		if (tiles[x + y * width] == 0xFF3541bd) return Tile.roofUpEdgeRight;
		if (tiles[x + y * width] == 0xFF3541be) return Tile.roofUpEdgeLeft;
		if (tiles[x + y * width] == 0xFF3541bf) return Tile.roofUpEdgeRightUp;
		if (tiles[x + y * width] == 0xFF3541ca) return Tile.roofUpEdgeLeftUp;
		if (tiles[x + y * width] == 0xFF3541cb) return Tile.roofUpEdgeUp;
		
		return Tile.VoidTile;
	}
	
	//Return tile based on which color is passed in
	public static Tile getTileByCol(int col) {
		//System.out.println(x + " | " + y + " | " + width + " | " + height);
		
		if (col == 0xFF00FF00) return Tile.grassFull;
		if (col == 0xFF9bff00) return Tile.water;
		if (col == 0xFFe1fb00) return Tile.woodFloor;
		if (col == 0xFF00FFFF) return Tile.stonePath;
		if (col == 0xFF00FFaa) return Tile.wallEmpty;
		if (col == 0xFF7f7f00) return Tile.sand;
		if (col == 0xFF927807) return Tile.tree;
		if (col == 0xFF35413a) return Tile.shrub;
		
		if (col == 0xFF35413b) return Tile.tree_top_left;
		if (col == 0xFF35413c) return Tile.tree_top_right;
		if (col == 0xFF35413d) return Tile.tree_bottom_left;
		if (col == 0xFF35413e) return Tile.tree_bottom_right;
		
		if (col == 0xFF35414b) return Tile.couchLeft;
		if (col == 0xFF35414c) return Tile.couchMid;
		if (col == 0xFF35414d) return Tile.couchRight;
		
		if (col == 0xFFabcd01) return Tile.carpetTopLeft;
		if (col == 0xFFabcd02) return Tile.carpetTopMid;
		if (col == 0xFFabcd03) return Tile.carpetTopRight;
		if (col == 0xFFabcd04) return Tile.carpetMiddleLeft;
		if (col == 0xFFabcd05) return Tile.carpetMiddleMid;
		if (col == 0xFFabcd06) return Tile.carpetMiddleRight;
		if (col == 0xFFabcd07) return Tile.carpetBottomLeft;
		if (col == 0xFFabcd08) return Tile.carpetBottomMid;
		if (col == 0xFFabcd09) return Tile.carpetBottomRight;
		
		
		
		return Tile.VoidTile;
	}
	
	//Return the color associated with the tile passed in
	public static int getTileColors(Tile t) {
		if (t.equals(Tile.grassFull)) return 0xFF00FF00;
		else if (t.equals(Tile.woodFloor)) return 0xFFe1fb00;
		else if (t.equals(Tile.wallEmpty)) return 0xFF00FFaa;
		else if (t.equals(Tile.stonePath)) return 0xFF00FFFF;
		else if (t.equals(Tile.water)) return 0xFF9bff00;
		else return 0xFF00FF;
	}
	
	public String toString() {
		return "Generic Level";
	}
	
	//Set the current level based on which level id is passed
	public void setCurrentLevel(int id) {
		entities.removeAll(entities);
		//if id is greater than num levels, choose a random level and add to the levels list
		while(id > levels.size()-1) {
			int temp = r.nextInt(4);
			levels.add(new GrassLevel("/textures/grassLevel"+temp+".png"));
		}
		currentLev = levels.get(id);
	}
	
	//equals method for level, compares id
	public boolean equals(Level lev) {
		return this.id == lev.id;
	}
	
	int num = 0;
	//Checks to see if the area has any used tiles before placing a structure
	protected boolean checkArea(int width, int height, int startIndex, int[] pix) {
		boolean clear = true;
		for (int y =0; y < height; y++) {
			for (int x = 0; x < width; x ++) {
				if (usedTiles[startIndex+x+y*this.width] == 1) {
					
					clear = false;
				}
			}
		}
		//System.out.println(clear + " " + num);
		return clear;
	}
	
	//generates a tree at the index in the pixel array passed in
	protected void generateTree(int[] pix, int index, int type){
		//dimensions 3 by 4, width by height
		//0xFF00FF00) return Tile.grassFull;
		//0xFF9bff00) return Tile.water;
		//0xFFe1fb00) return Tile.wood;
		//0xFF00FFFF) return Tile.wall;
		//0xFF7f7f00) return Tile.sand;
		//0xFF927807) return Tile.tree;
		// 0xFF35413a shrub
		int[] col = new int[4];
		if (type == 0) {
			col[0] = 0xFF35413b;
			col[1] = 0xFF35413c;
			col[2] = 0xFF35413d;
			col[3] = 0xFF35413e;
		}
		
		if (index + 2 +2*this.width > pix.length) {
			
		}
		else if (checkArea(2, 2, index, pix)) { // ensures area is clear
			//System.out.println("Tree  " + num);
			
			num++;
			for (int y =0; y < 2; y++) {
				for (int x = 0; x < 2; x ++) {
					usedTiles[index+x+y*width] = 1;
					if (y==0) {
						if (x == 0) pix[index+x+y*this.width] = col[0];
						if (x == 1) pix[index+x+y*this.width] = col[1];
					}
					if (y==1) {
						if (x == 0) pix[index+x+y*this.width] = col[2];
						if (x == 1) pix[index+x+y*this.width] = col[3];
					}
				}
			}
		}
	}
	
	//Generates a forest of width and height passed as parameters
	//places upper left corner at the index passed in
	protected void generateForest(int[] pix, int index, int width, int height) {
		if (index + width +height*this.width > pix.length) {
			
		}
		else if (checkArea(width, height, index, pix) == true) { // ensures area is clear
			//System.out.println("Forest " + num);
			
			num++;
			int start = this.r.nextInt(width/2) + 2;
			int end = this.r.nextInt(width-start) + start;
			int begChange = 0;
			int endChange = 0;
			for (int y =0; y < height; y++) {
				if (y != 0) {
					begChange = r.nextInt(3) - 1;
					endChange = r.nextInt(3) - 1;
				}
				for (int x = start; x < end; x ++) {
					usedTiles[index+x+y*this.width] = 1;
					if (x < start+begChange) pix[index + x+y*this.width] = 0xFF00FF00;
					if (x >= start +begChange && x <= end + endChange) pix[index + x+y*this.width] = 0xFF927807;
					if (x > end+ endChange) pix[index+x+y*this.width] = 0xFF00FF00;
					
				}
				start = start+begChange;
				end = end +endChange;
				
			}
		}
	}
	
	//Generates a patch of the given tile type based on the color passed in
	protected void generatePatch(int[] pix, int index, int width, int height, int col) {
		if (index + width +height*this.width > pix.length) { // ensures all points are within the bounds of the array
			//System.out.println("out of bounds");
		}
		else if (checkArea(width, height, index, pix) == true) { // ensures area is clear
			//System.out.println("Patch " + num);
			
			num++;
			int start = this.r.nextInt(width/2) + 2;
			int end = this.r.nextInt(width-start) + start;
			int begChange = 0;
			int endChange = 0;
			for (int y =0; y < height; y++) {
				if (y != 0) {
					begChange = r.nextInt(3) - 1;
					endChange = r.nextInt(3) - 1;
				}
				for (int x = start; x < end; x ++) {
					usedTiles[index+x+y*this.width] = 1;
					if (x < start+begChange) pix[index + x+y*this.width] = 0xFF00FF00;
					if (x >= start +begChange && x <= end + endChange) pix[index + x+y*this.width] = col;
					if (x > end+ endChange) pix[index+x+y*this.width] = 0xFF00FF00;
					
				}
				start = start+begChange;
				end = end +endChange;
				
			}
		}
	}
	
	//generates a lake at the index specified with the given width and height
	protected void generateLake (int[] pix, int index, int width, int height) {
		//smallest possible lake 6 by 6
				//0xFF00FF00) return Tile.grassFull;
				//0xFF9bff00) return Tile.water;
				//0xFFe1fb00) return Tile.wood;
				//0xFF00FFFF) return Tile.wall;
				//0xFF7f7f00) return Tile.sand;
				//0xFF927807) return Tile.tree;
		if (index + width +height*this.width > pix.length) { // ensures not out of bounds
			System.out.println("out of bounds");
		}
		else if (checkArea(width, height, index, pix) == true) { // ensures all tiles are unused
			//System.out.println("Lake " + num);
			
			num++;
			//System.out.println("Area not clear");
			int start = this.r.nextInt(width/2) + width/4;
			int end = this.r.nextInt(width/2) + start;
			int begChange = 0;
			int endChange = 0;
			for (int y =0; y < height; y++) {
				if (y != 0) {
					begChange = r.nextInt(3) - 1;
					endChange = r.nextInt(3) - 1;
				}
				
				if (end - start <= 3) {
					start-=1;
					end+=1;
				}
				for (int x = start; x < end; x ++) {
					usedTiles[index+x+y*this.width] = 1;
					if (y == 0 || y == height-1)pix[index + x+y*this.width] = 0xFF7f7f00;
					else {
						if (x == start) pix[index + x+y*this.width] = 0xFF7f7f00;
						if (x > start&& x < end-1) pix[index + x+y*this.width] = 0xFF9bff00;
					if (x == end-1) pix[index + x+y*this.width] = 0xFF7f7f00;
					}
					
					
				}
				start = start+begChange;
				end = end +endChange;
				
				
			}
		}
	}
	
	//Generates a 5x5 house at the index specified
	protected void generateHouse(int[] pix, int index, int type) {
		// 5 by 5
		int[] col = new int[7];
		if (type == 0) {
			col[0] = 0xFF35415a; // left roof
			col[1] = 0xFF35415b; //right  roof
			col[2] = 0xFF35415c; //middle top part
			col[3] = 0xFF00FF00; // grass
			col[4] = 0xFF35415d; // door
			col[5] = 0xFF35415e; //right  roof
			col[6] = 0xFF35415f; //middle top part
		}
		
		if (index + 5 +5*this.width > pix.length) { // checks to see if within bounds
			//System.out.println("out of bounds");
			
		}
		else if (!checkArea(5, 5, index, pix)) { // checks to see if area is unused
			//System.out.println("Area not clear");
		} else {
			for (int y =0; y < 5; y++) {
				for (int x = 0; x < 5; x ++) {
					usedTiles[index+x+y*width] = 1;
					if (y>=0 && y <= 4) {
						if (x ==0)  pix[index+x+y*this.width] = col[5];
						if (x== 1) pix[index+x+y*this.width] = col[0];
						if (x ==3) pix[index+x+y*this.width] = col[1];
						if (x == 2) pix[index+x+y*this.width] = col[2];
						if (x ==4)  pix[index+x+y*this.width] = col[6];
						//else pix[index+x+y*this.width] = col[0];
					}
					if (y==5) {
						if (x == 0 || x==1 || x == 3 || x == 4) pix[index+x+y*this.width] = col[3];
						if (x == 2) pix[index+x+y*this.width] = col[4];
					}
				}
			}
		}
	}
	
}
