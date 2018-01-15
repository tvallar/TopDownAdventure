package com.realm.madgod.entity.mob.creature;

import java.util.Random;

import com.realm.madgod.entity.mob.Creature;
import com.realm.madgod.entity.mob.Mob;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.level.Level;

public class GenerateCreatures {
	
	//Class containing all methods for initial enemy generation
	Mob[] creatures;
	int num; // number of enemies to add
	Random rand = new Random();
	Level level; // Level to add the enemies to
	public GenerateCreatures(int num, Level level) {
		creatures = new Creature[num];
		this.num=num;
		this.level = level;
	}
	
	public void create() {
		
		
	}
	
	//Render method for all enemies added through the object
	public void render(Screen screen) {
		for(int i = 0; i < creatures.length; i ++) {
			creatures[i].render(screen);
		}
	}
	
	//Method for updating all enemies added
	public void update() {
		for (int i = 0; i < num; i ++) {
			creatures[i].update();
		}
	}
}
