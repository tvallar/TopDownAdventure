package com.realm.madgod.entity.mob;

import java.util.Random;

import com.realm.madgod.Game;
import com.realm.madgod.entity.item.Effect;
import com.realm.madgod.entity.item.HealthPotion;
import com.realm.madgod.entity.item.StaminaPotion;
import com.realm.madgod.entity.item.weapons.Weapon;
import com.realm.madgod.entity.mob.creature.GreenCreature;
import com.realm.madgod.entity.mob.creature.OrangeKnight;
import com.realm.madgod.entity.mob.creature.Path;
import com.realm.madgod.entity.mob.creature.PathTarget;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.gui.Inventory;
import com.realm.madgod.input.Mouse;
import com.realm.madgod.level.Level;

public class Creature extends Mob {
	//Framework for all enemies in the game, most important methods are implemented in this class
	//that apply to all enemies, such as path finding and sight radius.
	//Can be overridden in children classes if need be but in most cases these implementations are enough
	int count = 0;
	protected Path p; // Current path being taken, switches between pOriginal and a path targeting an enemy
	protected Path pOriginal; // path the enemy will take if no enemy is nearby
	//int id;
	Mob target = null;
	
	protected static Random rand = new Random();
	int radius = 15;
	boolean hasTarget = false;
	protected int anim = 0;
	
	
	public Creature(int x, int y, int health, int energy) {
		this.x=x;
		this.y=y;
		this.health=health;
		this.energy=energy;
		this.healthMax = health;
		this.hostile = true;
		this.inventory = new Inventory();
		this.id = curID;
		armorRating = 10;
		curID++;
		experienceP = 15;
		
		//System.out.println(id + " | " + curID);
	}
	
	//Render is implemented in children classes
	public void render(Screen screen) {
		
		
	}
	
	//Update method for all enemies, includes looking for enemies, moving, and shooting
	public void update(){
		//System.out.println("Start Creature Update");
		
		checkSurroundings();
		updateShooting(target);
		p.update();
		if (energy < 100) {
			energy +=1;
		}
		if (anim < 7500) anim++;
		else anim = 0;
		int xa = p.getDx();
		int ya = p.getDy();
		if (hasTarget) {
			//System.out.println(xa + " | " + ya);
		}
		if (xa != 0 || ya != 0) {
			walking = true;
			move(xa,ya);
			
		}
		
		if (getHit) {
			hitCount++;
			if (hitCount == 20) {
				getHit = false;
				hitCount = 0;
			}
		}
		
	}
	
	//Method for checking if an enemy is nearby
	public boolean checkSurroundings() {
		int dist = radius*16;
		int tar = -1;
		for(int i = 0; i < level.getBeings().size(); i ++) {
			int xChange = this.x - level.getBeings().get(i).x;
			int yChange = this.y - level.getBeings().get(i).y;
			int temp = (int) Math.sqrt(xChange*xChange + yChange*yChange);
			//System.out.println(level.getBeings().get(i).getID() + " " + temp);
			if (temp < dist && !level.getBeings().get(i).isHostile()) {
				tar = i;
				//System.out.println(tar);
				//System.out.println(dist);
				dist = temp;
			}
		}
		if (tar > -1 && !hasTarget) {
			hasTarget = true;
			pOriginal.setTarget(true);
			//System.out.println(level.getBeings().get(tar) + " | " + this);
			target = level.getBeings().get(tar);
			p = new PathTarget(target, this);
			
			//System.out.println("yes");
			return true;
		} else if (tar < 0 && hasTarget) {
			if (hasTarget) {
				p = pOriginal;
				p.setTarget(true);
				//System.out.println("Test");
			}
			hasTarget = false;
			target = null;
		}
		
		return false;
		
	}
	
	//Generic drop items for all enemies
	//Is overridden if the enemy needs to drop specific items
	public void dropItems() {
		weapon.setPosition(x, y);
		weapon.setHeld(false);
		level.add(weapon);
		int a =this.rand.nextInt(10);
		if (a > 8) {
			level.add(new HealthPotion(level, this.x, this.y));
			level.add(new StaminaPotion(level, this.x, this.y));
		} else if (a > 5) {
			level.add(new HealthPotion(level, this.x, this.y));
		}
	}
	
	//Shoots at the given target
	private void updateShooting(Mob tar) {
		if (hasTarget && target !=null) {
			if (count == 0) {
				double dx = tar.x - this.x;
				double dy = tar.y - this.y;
				double dir = Math.atan2(dy, dx);
				shoot(x, y, dir);
				count++;
			} else {
				count ++;
				if (count == 50) count = 0;
			}
		}
	}
	
	public void init(Level level) {
		this.level = level;
	}
	//Static method that will create an enemy of given type
	public static Creature spawn(int type, int x, int y) {
		if (type == 0) return new GreenCreature(x, y);
		if (type == 1) return new OrangeKnight(x, y);
		else return new GreenCreature(x, y);
	}
	
	public String toString() {
		return this.getClass().toString() + " | " + id;
	}
}
