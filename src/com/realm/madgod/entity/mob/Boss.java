package com.realm.madgod.entity.mob;

import com.realm.madgod.entity.item.Item;
import com.realm.madgod.entity.item.Key;
import com.realm.madgod.entity.item.weapons.Weapon;
import com.realm.madgod.entity.mob.creature.Path;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class Boss extends Creature {
	private String name;
	private Sprite[] sprites;
	private Key keyDrop;
	
	public static Boss flameGuy = new Boss(145*16, 8*16, 500, 500, "Flame the Destroyer", Weapon.chaosBlade, Sprite.boss1Sprites, Key.key0);
	public static Boss iceGuy = new Boss(145*16, 8*16, 500, 500, "Flame the Destroyer", Weapon.chaosBlade, Sprite.boss1Sprites, Key.key0);
	
	
	public Boss(int x, int y, int health, int energy, String name, Weapon w, Sprite[] sprites, Item keyDrop) {
		super(x, y, health, energy);
		this.name = name;
		this.weapon = w;
		this.sprites = sprites;
		experienceP = 100;
		pOriginal = new Path(0, x, y, 20, this);
		p = pOriginal;
		radius = 20;
		this.keyDrop = (Key) keyDrop;
		//System.out.println(id + " | " + curID);
	}
	
	public void dropItems() {
		level.add(weapon);
		this.weapon.setHeld(false);
		this.weapon.setPosition(x, y);
		level.add(keyDrop);
		keyDrop.setPosition(x + 16, y);
		keyDrop.setHeld(false);
	}
	
	public void render(Screen screen) {
		if (dir == 0) {
			sprite = sprites[0];
		} else if (dir == 1) {
			sprite = sprites[1];
		} else if (dir == 2) {
			sprite = sprites[2];
		} else if (dir == 3) {
			sprite = sprites[3];
		}
		
		
		screen.renderCreature(x-16, y-16, sprite, health, healthMax);
		
	}

}
