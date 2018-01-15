package com.realm.madgod.entity.item.weapons;

import com.realm.madgod.graphics.Sprite;

public class StarterSword extends Weapon {

	public StarterSword() {
		super(Sprite.longSword1, 200, 40, "The Trainer", 1, false);
		setDesc("The Sword of a new challenger");
	}

}
