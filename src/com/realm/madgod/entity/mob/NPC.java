package com.realm.madgod.entity.mob;

import com.realm.madgod.entity.mob.creature.Path;
import com.realm.madgod.entity.mob.creature.PathTarget;
import com.realm.madgod.entity.mob.npc.Knight;

public class NPC extends Mob {
	//Framework class for NPC's in the game
	//Most important methods implemented in this class just as in the creature class for enemies
	protected boolean defender; //Determines if this NPC will defend when near an enemy
	protected Path p; //Current path
	protected Path pOriginal; // standard path if no enemy is nearby
	Mob target = null; // Target if one is nearby
	boolean hasTarget = false;
	int count = 0;
	//Constructor, determines stats and position
	public NPC(int x, int y, int health, int energy) {
		this.x=x;
		this.y=y;
		this.health=health;
		this.energy=energy;
		this.healthMax = health;
		this.hostile = false;
		this.id = curID;
		armorRating = 10;
		curID++;
		experienceP = 15;
		this.pOriginal = new Path(1, x, y, 0, this);
		p = pOriginal;
		
		//System.out.println(id + " | " + curID);
	}
	
	//Static spawn method for all NPCS given type
	public static NPC spawn(int type, int x, int y) {
		if (type == 0) return new Knight(x, y);
		else return new Knight(x, y);
		
	}
	
	//Update method that covers most NPC's in the game
	public void update(){
		//System.out.println("Start Creature Update");
		if (defender) {
			target= checkSurroundings();
			updateDirection(target);
			updateShooting(target);
		}
		p.update();
		if (energy < 100) {
			energy +=1;
		}
		if (anim < 7500) anim++;
		else anim = 0;
		int xa = p.getDx();
		int ya = p.getDy();
		
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
	
	private void updateDirection(Mob tar) {
		if (tar != null){
			int dx = tar.x - this.x;
			int dy = tar.y - this.y;
			if (Math.abs(dx) > Math.abs(dy)) {
				if (dx > 0) dir = 1;
				else dir = 3;
			} else {
				if (dy > 0) dir = 2;
				else dir = 0;
			}
		} else {
			dir = 2;
		}
	}
	
	//Method for shooting at the current target
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
	
	//Check surroundings method similar to that found in the Creature class
	public Mob checkSurroundings() {
		int dist = 5*16;
		int tar = -1;
		for(int i = 0; i < level.getBeings().size(); i ++) {
			int xChange = this.x - level.getBeings().get(i).x;
			int yChange = this.y - level.getBeings().get(i).y;
			int temp = (int) Math.sqrt(xChange*xChange + yChange*yChange);
			//System.out.println(level.getBeings().get(i).getID() + " " + temp);
			if (temp < dist && level.getBeings().get(i).isHostile() && level.getBeings().get(i).getHealth()>0) {
				hasTarget = true;
				return level.getBeings().get(i);
			}
		}
		hasTarget = false;
		
		return null;
	}
}
