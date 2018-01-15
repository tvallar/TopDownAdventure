package com.realm.madgod.entity.item.weapons;

import com.realm.madgod.Game;
import com.realm.madgod.entity.item.Effect;
import com.realm.madgod.entity.item.Item;
import com.realm.madgod.entity.mob.Mob;
import com.realm.madgod.entity.projectile.PickProjectile;
import com.realm.madgod.entity.projectile.Projectile;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.level.Level;

public class Weapon extends Item {
	//Framework for all weapons in the game, as of now limited to swords
	//Includes effects, projectile type, and more
	
	Effect e; // What effect the weapon has
	int reach, damage, rarity, attackSpeed; //Stats for the sword and attack rate
	double dir; // contains ratio that determines the angle to fire the projectile in
	String name; // Name of the weapon, meant for things like boss weapons more than standard
	Projectile p;
	protected boolean swing;
	protected int count = 0;
	protected int nx = 0;
	protected int ny = 0;
	protected int projType; // Contains the number corresponding to the projectile type
	
	protected String description = ""; // Unused at the moment, will include description/lore about the
	//weapon as the game is more developed in this area
	
	public static Weapon noWeapon = new Weapon(Sprite.inventoryTile, 0, 0, "No Weapon", 0, true);
	public static Weapon chaosBlade = new Weapon(Sprite.chaosBlade, 150, 200, "The Blade of Fire", 2, true);
	
	public Weapon(Sprite sprite, int reach, int damage, String name, int projType, boolean held) {
		super(sprite, level, held);
		this.damage = damage;
		this.reach = reach;
		this.e = e;
		this.name = name;
		this.rarity = rarity;
		this.projType = projType;
		
	}
	public void swingWeapon(int x, int y, double dir) {
		
	}
	
	public void setDesc(String d) {
		description = d;
	}
	
	public String getDesc() {
		return description;
	}

	public int getProjType() {
		return this.projType;
	}


	public Effect getE() {
		return e;
	}

	public void setE(Effect e) {
		this.e = e;
	}

	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getRarity() {
		return rarity;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
