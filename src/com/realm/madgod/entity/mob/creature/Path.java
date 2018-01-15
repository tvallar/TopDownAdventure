package com.realm.madgod.entity.mob.creature;

import java.util.Random;

import com.realm.madgod.Game;
import com.realm.madgod.entity.mob.Boss;
import com.realm.madgod.entity.mob.Mob;

public class Path {
	//Class for paths for NPC's and Creatures
	int fir,sec,thir,four; // Length of each turn of the square path it will go in
	protected int dx, dy; // variables used for updating the position of the mob based on path
	protected int count; // Counting how far through the path the mob is
	protected Mob tar, cur; // Mob target, and the mob this the path of
	boolean target = false; // Has target, default false
	private boolean knight = false; // Is this a knight, determines update type
	private int xOrigin, yOrigin, length, dir, curX, curY; //xOrigin and yOrigin are original location of the mob,
	//Returns to xOrigin and yOrigin when target is out of range
	public Path(Random r, Mob cur) {
		this.fir=r.nextInt(100) + 10;
		this.sec=r.nextInt(100) + 10 + fir;
		this.thir=fir + sec;
		this.four = sec-fir + thir;
		this.cur = cur;
	}
	
	//Stand Still, pace back and forth
	// dir 0 go up and down, dir 1 go left and right
	// length is how far the path is, cur is the mob this is the path of
	// x and y are the origin for this mob
	public Path(int dir, int x, int y, int length, Mob cur) {
		dx = 0;
		dy = 0;
		this.dir = dir; 
		this.xOrigin = x;
		this.yOrigin = y;
		knight = true;
		this.cur = cur;
		this.length = length*Game.TILE_SIZE;
	}
	
	//Sets whether or not has target
	public void setTarget(boolean t) {
		target = t;
	}
	
	//Constructor for creating a path with a target
	public Path(Mob m, Mob cur) {
		this.cur = cur;
		this.tar = m;
	}
	public int getFir() {
		return fir;
	}
	public void setFir(int fir) {
		this.fir = fir;
	}
	public int getSec() {
		return sec;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	public int getThir() {
		return thir;
	}
	public void setThir(int thir) {
		this.thir = thir;
	}
	public int getFour() {
		return four;
	}
	public void setFour(int four) {
		this.four = four;
	}
	
	//Returns the change in x based on path
	public int getDx() {
		return dx;
	}
	
	//Returns the change in y based on path
	public int getDy() {
		return dy;
	}
	
	//Update method for the path, sets the change in x and y that will be used by the entity
	//in their update method
	public void update() {
		dx = 0;
		dy = 0;
		if (knight){
			if (cur.x == xOrigin && cur.y == yOrigin && target) {
				target = false;
				count = 0;
			}
			if (target) {
				int xChange = cur.x - xOrigin;
				int yChange = cur.y - yOrigin;
				if (xChange > 0) dx = -1;
				if (xChange < 0) dx = 1;
				if (yChange > 0) dy = -1;
				if (yChange < 0) dy = 1;
			} else {
				if (dir == 0) {
					//if (cur instanceof Boss) System.out.println(count + " " + length);
					if (count <= length) dx = -1;
					if (count <= length*2 && count  > length) {
						dx = 1;
						
					}
					
				}
				if (dir == 1) {
					if (count < length) dy = -1;
					if (count < length*2 && count  >= length) dy = 1;
					
				}
				count++;
				if (count == length*2) count = 0;
				
			}
		} else {
			if (count<=getFir()) dx = 1;
			if (count>getFir() && count <= getSec() ) dy =1;
			if (count>getSec() && count <= getThir() ) dx=-1;
			if (count>getThir() && count <= getFour() ) dy =-1;
			count ++;
			if (count == getFour()) count = 0;
		}
	}
	
}
