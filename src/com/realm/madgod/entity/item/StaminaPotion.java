package com.realm.madgod.entity.item;

import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.level.Level;

public class StaminaPotion extends Item {

	public StaminaPotion() {
		super(Sprite.staminaPotion);
		// TODO Auto-generated constructor stub
	}
	
	public StaminaPotion(Level level, boolean held) {
		super(Sprite.staminaPotion, level, held);
		// TODO Auto-generated constructor stub
	}
	
	public StaminaPotion(Level level, int x, int y) {
		super(Sprite.staminaPotion, level, x, y);
	}
	
	public void use() {
		
	}

}
