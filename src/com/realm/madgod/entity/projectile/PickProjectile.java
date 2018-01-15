package com.realm.madgod.entity.projectile;

import com.realm.madgod.Game;
import com.realm.madgod.entity.Entity;
import com.realm.madgod.entity.mob.Mob;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class PickProjectile extends Projectile {
	
	
	//Class for the PickProjectile type
	//Attributes are set based on weapon this was fired from
	public PickProjectile(int x, int y, double dir, Mob o, int damage, int range) {
		super(x, y, dir, o);
		this.range = range;
		this.damage = damage;
		speed = 4;
		rateOfFire = 1;
		sprite = Sprite.projectile1;
		nx = Math.cos(angle) * speed;
		ny = Math.sin(angle) * speed;
	}
	
	
	//Checks to see if the projectile should be removed
	//otherwise moves and checks to see if a mob was hit by the projectile
	public void update() {
		if (Math.sqrt((x-xOrigin)*(x-xOrigin) + (y-yOrigin)*(y-yOrigin)) >= range) this.remove();
		move();
		Mob m = (Mob) checkHit();
		if (m != null) {
			m.takeHit(this);
		}
	}
	

}
