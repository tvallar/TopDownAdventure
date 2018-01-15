package com.realm.madgod.entity.item;

import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.level.Level;

public class HealthPotion extends Item {

	
	public HealthPotion() {
		super(Sprite.healthPotion);
	}
	public HealthPotion(Level level, boolean held) {
		super(Sprite.healthPotion, level, held);
		// TODO Auto-generated constructor stub
	}
	
	public HealthPotion(Level level, int x, int y) {
		super(Sprite.healthPotion, level, x, y);
	}
	
	public void use() {
		
	}

}
