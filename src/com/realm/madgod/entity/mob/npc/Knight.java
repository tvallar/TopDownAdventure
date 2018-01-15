package com.realm.madgod.entity.mob.npc;

import com.realm.madgod.entity.item.weapons.GreenKnightSword;
import com.realm.madgod.entity.item.weapons.StarterSword;
import com.realm.madgod.entity.mob.NPC;
import com.realm.madgod.entity.mob.creature.Path;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;

public class Knight extends NPC {

	public Knight(int x, int y) {
		super(x, y, 100, 100);
		this.defender = true;
		this.weapon = new GreenKnightSword();
	}
	
	
	
	public void render(Screen screen) {
		if (dir == 0) {
			
			sprite = Sprite.knight_up;
			//sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.knight_forward_1;
				} else {
					sprite = Sprite.knight_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.knight_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.knight_right_1;
				} else {
					sprite = Sprite.knight_right_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.knight_down;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.knight_back_1;
				} else {
					sprite = Sprite.knight_back_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.knight_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.knight_left_1;
				} else {
					sprite = Sprite.knight_left_2;
				}
			}
		}
		screen.renderCreature(x-8, y-8, sprite, health, healthMax);
		if (getHit) {
			screen.renderTile(x-8, y-8, Sprite.hitAnim);
		}
	}

}
