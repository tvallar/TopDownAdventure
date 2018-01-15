package com.realm.madgod.entity.item;

import java.util.ArrayList;
import java.util.Arrays;

import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.level.Level;

public class Key extends Item {
	//Number that determines which door the key will open
	private int keyID;
	
	//Static keys that are added to the game for specific doors
	public static Item key0 = new Key(Sprite.key, Level.currentLev, true, 0);
	public static Item zone1Shortcut1Key = new Key(Sprite.key, Level.currentLev, 132*16, 106*16 , 1);
	
	//Arraylist of all keys
	public static ArrayList<Item> keys = new ArrayList<Item>(Arrays.asList(key0, zone1Shortcut1Key));
	
	//Key constructor, includes keyID which determines what door it will open, constructor for giving characters/enemies keys
	public Key(Sprite sprite, Level level, boolean held, int keyID) {
		super(sprite, level, held);
		this.keyID = keyID;
		
	}
	
	//Constructor for placing keys on the map
	public Key(Sprite sprite, Level level, int x, int y, int keyID) {
		super(sprite, level, x, y);
		this.keyID = keyID;
	}
	
	public int getKeyID() {
		return keyID;
	}

}
