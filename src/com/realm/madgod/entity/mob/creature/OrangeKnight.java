package com.realm.madgod.entity.mob.creature;

import com.realm.madgod.entity.item.weapons.OrangeKnightSword;
import com.realm.madgod.entity.item.weapons.Weapon;
import com.realm.madgod.entity.mob.Creature;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class OrangeKnight extends Creature {
	//Class for the orange knight enemy
	public OrangeKnight(int x, int y) {
		super(x, y, 200, 200);
		this.weapon = new OrangeKnightSword();
		this.p = new Path(1, x, y, 10, this);
		this.pOriginal = p;
	}
	
	//Render method for orange knights
	public void render(Screen screen) {
		if (dir == 0) {
			
			sprite = Sprite.enemy2_up;
			//sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.enemy2_forward_1;
				} else {
					sprite = Sprite.enemy2_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.enemy2_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.enemy2_right_1;
				} else {
					sprite = Sprite.enemy2_right_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.enemy2_down;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.enemy2_back_1;
				} else {
					sprite = Sprite.enemy2_back_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.enemy2_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.enemy2_left_1;
				} else {
					sprite = Sprite.enemy2_left_2;
				}
			}
		}
		screen.renderCreature(x-8, y-8, sprite, health, healthMax);
		if (getHit) {
			screen.renderTile(x-8, y-8, Sprite.hitAnim);
		}
	}

}
