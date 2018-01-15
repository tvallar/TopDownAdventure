package com.realm.madgod.entity.item;

public class Effect {
	//Integer that determines how much the stats of an item are multiplied by
	private int multiplier;
	
	//Framework for enchantments and weapon upgrades
	//Still mostly unimplemented, will be worked on more in the future
	public Effect(int multiplier) {
		this.multiplier = multiplier;
	}
	
	//Get number to multiply stats by
	public int getMultiplier() {
		return this.multiplier;
	}
}
