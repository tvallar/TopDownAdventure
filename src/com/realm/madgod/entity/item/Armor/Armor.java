package com.realm.madgod.entity.item.Armor;

import com.realm.madgod.entity.item.Item;
import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.level.Level;

public class Armor extends Item {
	//Framework for armor class, still mostly unimplemented
	//In the future will break down into armor types and increase the range
	//of stats armor can have
	
	//Static defined armor, starter gear.
	public static Armor startHead = new Armor(Sprite.starterArmorHead, level, true, 10);
	public static Armor startChest = new Armor(Sprite.starterArmorChest, level, true, 20);
	public static Armor startLegs = new Armor(Sprite.starterArmorLegs, level, true, 15);
	public static Armor startFeet = new Armor(Sprite.starterArmorFeet, level, true, 5);
	
	int armor;
	
	public Armor(Sprite sprite, Level level, boolean held, int armor) {
		super(sprite, level, held);
		this.armor = armor;
	}
	
	public int getArmor() {
		return armor;
	}

}
