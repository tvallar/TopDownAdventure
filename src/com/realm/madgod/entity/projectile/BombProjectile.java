package com.realm.madgod.entity.projectile;
import com.realm.madgod.Game;
import com.realm.madgod.entity.Entity;
import com.realm.madgod.entity.mob.Mob;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class BombProjectile extends Projectile {
	
	//Bomb projectile type, extends Projectile
	//Takes in damage which is set by the weapon this projectile was fired from
	//Range is also set by the weapon that fired this projectile
	public BombProjectile(int x, int y, double dir, Mob o, int damage, int range) {
		super(x, y, dir, o);
		this.range = range;
		this.damage = damage;
		speed = 4;
		rateOfFire = 1;
		sprite = Sprite.bombProjectile;
		nx = Math.cos(angle) * speed;
		ny = Math.sin(angle) * speed;
	}
	
	//Update method for this type
	public void update() {
		if (Math.sqrt((x-xOrigin)*(x-xOrigin) + (y-yOrigin)*(y-yOrigin)) >= range){
			this.remove();
			spawnChildren(angle); // Creates many smaller projectiles at point where its gone further than range
		}
		move();
		Mob m = (Mob) checkHit(); // checks to see if a mob was hit by this projectile
		if (m != null) {
			m.takeHit(this);
		}
	}
	
	//Spawn children from this projectile
	private void spawnChildren(double dir) {
		for (double i = 0; i < Math.PI*2; i +=  Math.PI/4){
			System.out.println("Test");
			Projectile p = new PickProjectile(x, y, dir+i, this.origin, 30, 5*16);
			this.origin.addProj(p);
			level.add(p);
		}
	}
	
	
	

}
