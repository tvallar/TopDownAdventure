package com.realm.madgod.entity.mob.creature;

import com.realm.madgod.entity.mob.Mob;

public class PathTarget extends Path {
	
	//Creates path with target
	public PathTarget(Mob m, Mob cur) {
		super(m, cur);
		
	}
	//Overrides parent update method to make mob follow target
	public void update() {
		dx = 0;
		dy = 0;
		int xChange = cur.x - tar.x;
		int yChange = cur.y - tar.y;
		if (xChange > 0) dx = -1;
		if (xChange < 0) dx = 1;
		if (yChange > 0) dy = -1;
		if (yChange < 0) dy = 1;
		
	}

}
