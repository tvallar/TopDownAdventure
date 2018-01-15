package com.realm.madgod.entity.mob;
import com.realm.madgod.gui.*;
import com.realm.madgod.level.tile.SaveTile;

import java.util.*;

import com.realm.madgod.Game;
import com.realm.madgod.entity.Entity;
import com.realm.madgod.entity.item.HealthPotion;
import com.realm.madgod.entity.item.Item;
import com.realm.madgod.entity.item.Key;
import com.realm.madgod.entity.item.weapons.Weapon;
import com.realm.madgod.entity.projectile.BombProjectile;
import com.realm.madgod.entity.projectile.PickProjectile;
import com.realm.madgod.entity.projectile.Projectile;
import com.realm.madgod.graphics.Sprite;

public class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	public int health;
	public int energy;
	public int healthMax;
	public int energyMax;
	public int experience;
	public int experienceP;
	public int experienceToLevel;
	protected Inventory inventory;
	protected int anim = 0;
	protected boolean walking = false;
	protected Weapon weapon;
	static int curID = 1; // Player has ID 0
	protected boolean getHit = false;
	protected int hitCount = 0;
	protected boolean hostile;
	
	protected int armorRating;
	
	
	
	public int rank;
	
	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	
	
	public void move(int xa, int ya) {
		if (xa !=0 && ya != 0){
			move(xa, 0);
			move(0,ya);
			return;
		}
		
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		int col = collision(xa,ya);
		
		if (col != 1){
			x += xa;
			y += ya;
		}
		
		
		
	}
	
	public boolean checkStatus() {
		if (health < 0) {
			this.remove();
			dropItems();
			return true;
		}
		return false;
	}
	
	public void update() {
		
	}
	
	public void dropItems() {
		
	}
	
	public void takeHit(Projectile p) {
		health -= p.getDamage();
		energy -= 5;
		getHit = true;
		if (checkStatus() && p.getOrigin().id == 0){
			System.out.println(p.getOrigin().experience + " " + rank);
			p.getOrigin().experience += this.experienceP;
		}
		projectiles.remove(p);
		p.remove();
	}
	
	
	
	protected void shoot (int x, int y, double dir) {
		//System.out.println("Angle: " + dir);
		if (energy >= 10) {
			Projectile p;
			int pType = weapon.getProjType();
			if (pType==0) {
				p = new PickProjectile(x, y, dir, this, weapon.getDamage(), weapon.getReach());
			} else if (pType == 1) {
				p = new BombProjectile(x, y, dir, this, weapon.getDamage(), weapon.getReach());
			} else {
				p = new PickProjectile(x, y, dir, this, weapon.getDamage(), weapon.getReach());
			}
			
			this.energy -= 10;
			projectiles.add(p);
			level.add(p);
		}
	}
	
	
	
	
	
	private int collision(int xa, int ya) {
		//Collision types:
		//wall = 1;
		// door '= 2;
		//
		int solid = 0;
		for (int c = 0; c < 4; c++) {
			int xt = ((x +xa)+ c % 2 *6 - 3) / Game.TILE_SIZE;
			int yt = ((y+ya)+ c / 2 * 2 -1) /Game.TILE_SIZE;
			//System.out.println(xt + " | " + yt);
			//System.out.println(level);
			if (level.getTile(xt, yt).solid()) {
				solid = 1;
			} 
			if (level.getTile(xt, yt).door()) {
				if (this.inventory.hasKey(level.getTile(xt, yt).getKeyID())) level.getTile(xt, yt).setLocked(false);
				if (level.getTile(xt, yt).locked()) solid = 1;
				else solid = 0;
				
			}
			if (level.getTile(xt, yt).saveTile()) {
				SaveTile temp = (SaveTile) level.getTile(xt, yt);
				if (temp.getSaveID() != level.getSaveTile().getId()){
					level.setSavePoint(temp.getSaveID());
				}
				System.out.println(level.getSaveTile().getId());
			}
		}
		
		return solid;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render() {
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public boolean equals(Mob m) {
		
		return this.id == m.id;
	}
	
	public Weapon getWeapon(){
		return weapon;
	}
	
	public void setWeapon(Weapon w) {
		weapon = w;
	}
	
	public boolean isHostile() {
		return hostile;
	}
	
	public void setHostile(boolean hostile) {
		this.hostile = hostile;
	}
	
	public void addProj(Projectile p) {
		projectiles.add(p);
	}
	

	
	
}
