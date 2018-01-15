package com.realm.madgod.entity.item.weapons;

import com.realm.madgod.graphics.Sprite;

public class BrokenSword extends Weapon {

	public BrokenSword() {
		super(Sprite.brokenBlade, 200, 40, "Broken Down Sword", 1, false);
		setDesc("The sword of a hero passed");
	}

}
