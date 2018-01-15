package com.realm.madgod.gui;

import java.util.ArrayList;

import com.realm.madgod.Game;
import com.realm.madgod.entity.item.HealthPotion;
import com.realm.madgod.entity.item.Item;
import com.realm.madgod.entity.item.Key;
import com.realm.madgod.entity.item.StaminaPotion;
import com.realm.madgod.entity.item.Armor.Armor;
import com.realm.madgod.entity.item.weapons.Weapon;
import com.realm.madgod.entity.mob.Mob;
import com.realm.madgod.entity.mob.Player;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.input.Keyboard;
import com.realm.madgod.level.Level;
import com.realm.madgod.level.tile.Tile;

public class Inventory {
	//Class that holds all important inventory attributes
	private ArrayList<Item> items; // what items are in the inventory
	private int[] quickAccessNums; // Defines the amount held of each item on the player quick access menu
	private Item[] quickAccessItems; // what items are on the quick access menu
	private int[] buildTileNums; // what tile types are in the build menu quck access
	private boolean playerInv = false; // Is it the player inventory?
	private Sprite inventoryTile; // Inventory background tile
	private Keyboard input; // Keyboard listener if it is the player inventory
	private int numHealth, numStam, numDamage; //Amount of each type of potion
	private static int maxPotions = 5; // Max amount of potions that can be held
	private Player p; // The player object if this is the player inventory
	private int selected; // What item is selected in the player menu
	private int buildTile; // Which tile should be built
	private int tileOption; // Which tile index is selected
	
	
	//Standard non-player inventory
	public Inventory(){
		items = new ArrayList<Item>();
		inventoryTile = Sprite.inventoryTile;
		numHealth = 0;
		numStam = 0;
		numDamage = 0;
	}
	
	//Player inventory, parameters are keyboard and player object
	public Inventory(Keyboard key, Player p){
		items = new ArrayList<Item>();
		playerInv = true;
		inventoryTile = Sprite.inventoryTile;
		items.add(Weapon.noWeapon);
		p.setWeapon((Weapon) items.get(0));
		this.p = p;
		this.input = key;
		numHealth = 0;
		numStam = 0;
		numDamage = 0;
		quickAccessNums = new int[3];
		this.quickAccessNums[0] = numHealth;
		this.quickAccessNums[1] = numStam;
		this.quickAccessNums[2] = numDamage;
		quickAccessItems = new Item[3];
		this.quickAccessItems[0] = new HealthPotion();
		this.quickAccessItems[1] = new StaminaPotion();
		this.quickAccessItems[2] = new HealthPotion();
		buildTileNums = new int[5];
		this.buildTileNums[0] = Level.getTileColors(Tile.grassFull);
		this.buildTileNums[1] = Level.getTileColors(Tile.woodFloor);
		this.buildTileNums[2] = Level.getTileColors(Tile.wallEmpty);
		this.buildTileNums[3] = Level.getTileColors(Tile.stonePath);
		this.buildTileNums[4] = Level.getTileColors(Tile.water);
		selected = 0;
		buildTile = 0xFF00FF;
		tileOption = 0;
		
		
	}
	//Method to add item to the inventory
	public void add(Item i) {
		
		// if it is a weapon, and the damage is greater than the weapon currently equiped, then equip the new weapon
		if (i instanceof Weapon) {
			Weapon w = (Weapon) i;
			if (w.getDamage() > p.getWeapon().getDamage()){
				items.add(0, i);
				
			} else {
				items.add(i);
			}
			i.setHeld(true); // set the item held so it doesn't render anymore
		}else if (i instanceof HealthPotion && quickAccessNums[0] < 5) {  // if its a health potion and max amount is not reached
			items.add(i);
			quickAccessNums[0]++;
			i.setHeld(true);
		} else if (i instanceof StaminaPotion && quickAccessNums[1] < 5) {  // if it is a stam potion and max is not reached
			items.add(i);
			quickAccessNums[1]++;
			i.setHeld(true);
		} else { // otherwise
			items.add(i);
			i.setHeld(true);
		}
		
	}
	
	//Methody to used the item at index i in the quick access menu
	public void use(int i) {
		if (quickAccessNums[i] > 0) {
			p.useItem(i);
			quickAccessNums[i]--;
		}
		
	}
	
	//Remove item from inventory
	public void remove(Item i) {
		items.remove(i);
	}
	
	//Drop item onto the ground at player position (only player uses this method)
	public void drop(Item i) {
		items.remove(i);
		i.setHeld(false);
		i.setPosition(p.x, p.y);
	}
	
	//Return the index of the selected item
	public int getSelected() {
		return selected;
	}
	
	//Return the tile that is selected
	public int getBuildTile() {
		return buildTile;
	}
	
	//Checks to see if the player has the key with the id passed as a parameter
	public boolean hasKey(int id) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof Key) {
				Key k = (Key) items.get(i);
				if (k.getKeyID() == id) return true;
			}
		}
		
		return false;
	}
	
	
	//Render inventory
	public void render(Screen screen, int x, int y) {
		int gap = 5; // gap between inventory items
		int xi = x - 100 - 205; // Position of the inventory on the screen from player position
		int yi = y-40; // position on of the inventory on the screen from player position
		screen.renderTile(xi, yi, this.inventoryTile); // first one is the weapon
		screen.renderTile(xi, yi, p.getWeapon().getSprite());
		for (int i = 1; i < 6; i++) {
		
			if (i < 4) {
				screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i), yi, this.inventoryTile);
				int temp = quickAccessNums[i-1];
				screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i), yi, this.quickAccessItems[i-1].getSprite());
				screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i), yi, this.number(quickAccessNums[i-1]));
				
			} else {
				screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i) + gap*2, yi, this.inventoryTile);
				if (i == 4) {
					screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i) + gap*2, yi, Sprite.projectile1);
					if (selected == 0) screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i) + gap*2, yi, Sprite.invSelect);
				}
				
				if (i==5) {
					screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i) + gap*2, yi, Sprite.tileEditHammer);
					if (selected == 1) screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i) + gap*2, yi, Sprite.invSelect);
				}
			}
					 
			
			
			
		}
		int colNoSelect = 0xd4d4d4;
		int colSelect = 0xf1f1f1;
		for (int i = 0; i < buildTileNums.length; i++) {
			if (i == tileOption) screen.renderInventoryItemBox(xi+(gap+Game.TILE_SIZE)*(i+7) + gap*2, yi, colSelect);
			else screen.renderInventoryItemBox(xi+(gap+Game.TILE_SIZE)*(i+7) + gap*2, yi, colNoSelect);
			screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i+7) + gap*2, yi, Level.getTileByCol(this.buildTileNums[i]).getSprite());
			screen.renderTile(xi+(gap+Game.TILE_SIZE)*(i+7) + gap*2, yi, this.number(i+1));
			
		}
	}
	//Returns a sprite that holds the number specified as a parameter
	public Sprite number(int num) {
		if (num == 1) {
			return Sprite.invOne;
		} else if (num == 2) {
			return Sprite.invTwo;
		} else if (num == 3) {
			return Sprite.invThree;
		} else if (num == 4) {
			return Sprite.invFour;
		} else if (num == 5) {
			return Sprite.invFive;
		} else {
			return Sprite.invZero;
		}
	}
	
	boolean[] presses = new boolean[5];
	boolean swap = false;
	public void update() {
		if (playerInv){
			
			if (input.one && !presses[0]) {
				if (selected == 0) use(0);
				if (selected == 1) tileOption = 0;
				presses[0] = true;
			}
		
			if (input.two && !presses[1]) {
				if (selected == 0) use(1);
				if (selected == 1) tileOption = 1;
				presses[1] = true;
			}
		
			if (input.three && !presses[2]) {
				if (selected == 0) use(2);
				if (selected == 1) tileOption = 2;
				presses[2] = true;
			}
			if (input.four && !presses[3]) {
				
				if (selected == 1) tileOption = 3;
				presses[3] = true;
			}
		
			if (input.five && !presses[4]) {
				
				if (selected == 1) tileOption = 4;
				presses[4] = true;
			}
			if (!input.one) {
				presses[0] = false;
			}
			if (!input.two) {
				presses[1] = false;
			}
			if (!input.three) {
				presses[2] = false;
			}
			if (!input.four) {
				presses[3] = false;
			}
			if (!input.five) {
				presses[4] = false;
			}
			if (input.use && !swap) {
				swap = true;
				if (selected == 0) selected =1;
				else selected = 0;
			}
			
			if (!input.use) {
				swap = false;
			}
			
			buildTile = buildTileNums[tileOption];
			
		}
		
		p.setWeapon((Weapon) items.get(0));
		
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	


}
