package com.realm.madgod.entity.projectile;

import com.realm.madgod.Game;
import com.realm.madgod.entity.Entity;
import com.realm.madgod.entity.mob.Mob;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public abstract class Projectile extends Entity {
	//Framework for projectile entities
	//Contains all necessary attributes for projectiles

	protected final int xOrigin, yOrigin; // origin point
	protected final Mob origin; // origin mob
	protected double angle; // angle fired at, direction to go
	protected Sprite sprite; // sprite for projectile
	protected double nx, ny; // new x and y after update
	protected double speed, rateOfFire, range, damage; // attributes of the projectile

	//Constructor, sets origin, direction, and mob that fired the projectile
	public Projectile(int x, int y, double dir, Mob o) {
		xOrigin = x;
		yOrigin = y;
		origin = o;
		angle = dir;
		this.x=x;
		this.y=y;
	}

	public double getDamage() {
		return damage;
	}
	
	//Default Move method for the projectile
	//Checks to see if it hit a wall, if it has the projectile is removed
	//Can be overridden if a projectile needs a more specific move style
	protected void move() {
		if (!collision(nx, ny)){
			x += nx;
			y += ny;
		} else {
			this.remove();
		}
	}
	
	//Method for checking to see if a mob was hit by the projectile
	protected Entity checkHit() {
		Mob a = null;
		for (Mob m: level.getBeings()) {
			if (this.x > m.x-12 && this.x < m.x+12 && this.y > m.y-12 && this.y < m.y +12 && !m.equals(origin) && !m.isRemoved()) {
				a = m;
			}
						
		}
		return a;
	}
			

	//Method for checking to see if a wall has been hit
	protected boolean collision(double xa, double ya){
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (int) (((x +xa)) / Game.TILE_SIZE);
			int yt = (int) (((y+ya))/Game.TILE_SIZE);
			//System.out.println(xt + " | " + yt);
			//System.out.println(level);
			if (level.getTile(xt, yt).solid()) {
				solid = true;
			} 
		}

		return solid;
	}


	//Returns the mob that fired the projectile
	public Mob getOrigin() {
		return origin;
	}
	
	//Render method, can be overridden if something different is needed
	public void render(Screen screen) {
		screen.renderTile(x-8, y-8, sprite);
	}




}
