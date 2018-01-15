package com.realm.madgod.entity.mob.creature;

import com.realm.madgod.entity.item.weapons.BrokenSword;
import com.realm.madgod.entity.mob.Creature;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class GreenCreature extends Creature {
	//Enemy type, passes specified health and energy for this enemy into superconstructor
	//sets weapon and path
	public GreenCreature(int x, int y) {
		super(x, y, 100, 100);
		this.weapon = new BrokenSword();
		this.p = new Path(rand, this);
		this.pOriginal = p;
		
	}
	
	//Render method for the greenCreature
	public void render(Screen screen) {
		if (dir == 0) {
			
			sprite = Sprite.enemy1_up;
			//sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.enemy1_forward_1;
				} else {
					sprite = Sprite.enemy1_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.enemy1_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.enemy1_right_1;
				} else {
					sprite = Sprite.enemy1_right_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.enemy1_down;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.enemy1_back_1;
				} else {
					sprite = Sprite.enemy1_back_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.enemy1_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.enemy1_left_1;
				} else {
					sprite = Sprite.enemy1_left_2;
				}
			}
		}
		screen.renderCreature(x-8, y-8, sprite, health, healthMax);
		if (getHit) {
			screen.renderTile(x-8, y-8, Sprite.hitAnim);
		}
	}
	
	

}
