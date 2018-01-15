package com.realm.madgod.entity.mob;

import java.awt.Point;

import com.realm.madgod.Game;
import com.realm.madgod.entity.item.Item;
import com.realm.madgod.entity.item.Key;
import com.realm.madgod.entity.item.Armor.Armor;
import com.realm.madgod.entity.item.weapons.Weapon;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.gui.Inventory;
import com.realm.madgod.input.Keyboard;
import com.realm.madgod.input.Mouse;
import com.realm.madgod.level.Level;
import com.realm.madgod.level.tile.Tile;

public class Player extends Mob{
	//Class for the player, includes the key listener
	private Keyboard input;
	private Sprite sprite;
	
	public static boolean activation = false;
	
	public int count = 0;
	
	
	//Init the player with no position
	public Player (Keyboard input) {
		this.input = input;
		rank = 0;
		experience = 1;
		experienceToLevel = 0;
		checkLevel();
		inventory = new Inventory(input, this);
		init();
	}
	
	public void init() {
		
	}
	//Constructor with position, takes in input which is the keyListener
	public Player (int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		rank = 0;
		checkLevel();
		id = 0;
		inventory = new Inventory(input, this);
		this.hostile = false;
		init();
	}
	
	public Level getLevel() {
		return level;
	}
	
	
	//Player update method
	public void update() {
		checkLevel();
		
		if (getHit) {
			hitCount++;
			if (hitCount == 20) {
				getHit = false;
				hitCount = 0;
			}
		}
		int ya = 0;
		int xa = 0;
		
		if (anim < 7500) anim++;
		else anim = 0;
		int speed = 5;
	
		
		if (input.up) ya-=speed;
		if (input.down) ya+=speed;
		if (input.left) xa-=speed;
		if(input.right) xa+=speed;
		
		//armorRating = inventory.getArmor(0).getArmor() + inventory.getArmor(1).getArmor() + inventory.getArmor(2).getArmor() + inventory.getArmor(3).getArmor();
		
		
		if (xa != 0 || ya != 0) {
			//System.out.println(x + " " + y);
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
			if (count%2 == 0) {
				if (energy < energyMax) energy +=1;
				//System.out.println(count);
				if (count%4 ==0 && health < healthMax) {
					health += 1;
					//System.out.println("Health");
					count = 0;
				}
				count ++;
			} else {
				count ++;
			}
			
		
		//Updates all functions of the player
		updateBuild();
		updateAttacks();
		updatePickup();
		inventory.update();
		
		
	}
	
	//Places the player back at the save spot last reached
	//Resets health and energy
	private void respawn(){
		int newX = level.getSaveTile().getX();
		int newY = level.getSaveTile().getY();
		this.x = newX;
		this.y = newY;
		health = healthMax;
		energy = energyMax;
	}
	
	//Determines if there is an item nearby to be picked up by the player
	private void updatePickup() {
		for (int i = 0; i < level.getItems().size(); i++) {
			if (level.getItems().get(i).x > this.x-16 && level.getItems().get(i).x < this.x + 8 && level.getItems().get(i).y > this.y-16 && level.getItems().get(i).y < this.y+8 && level.getItems().get(i).getHeld() == false) {
				inventory.add(level.getItems().get(i));
				if (level.getItems().get(i).equals(Key.zone1Shortcut1Key)) System.out.println("Yes");
			}
		}
	}
	int attackCount = 0; // Counter until can attack again
	boolean canShoot = true; // boolean for whether or not the cooldown has been reached
	private void updateAttacks() {
		
		
		if (Mouse.getButton() == 1 && canShoot && inventory.getSelected()==0) {
			//System.out.println("Test");
			double dx = Mouse.getX() - Game.getWindowWidth()/2;
			double dy = Mouse.getY() - Game.getWindowHeight()/2;
			double dir = Math.atan2(dy, dx);
			//System.out.println(dir);
			canShoot = false;
			attackCount = 0;
			shoot(x, y, dir);
			
		}
		
		attackCount++;
		if (attackCount == 20) {
			attackCount = 0;
			canShoot = true;
		}
		
	}
	
	//Checks to see if the player is dead, respawns at save point if so
	public boolean checkStatus() {
		if (health < 0) {
			this.respawn();
			dropItems();
			return true;
		}
		return false;
	}
	
	
	//Checks to see if the player is building
	public void updateBuild() {
		if (inventory.getSelected() == 1) {
			level.setBuildTile(inventory.getBuildTile());
			level.setLevelEdit(true);
			if (Mouse.getButton() == 1) {
				level.setBlock();
			}
		} else {
			level.setLevelEdit(false);
		}
	}
	
	
	//Method for using item, such as health/stamina potions - Should be in the item class
	public void useItem(int i) {
		if (i == 0) health+= 50;
		if (i == 1) energy+=20;
		//if (i == 2) damage+=30;
	}
	
	
	//Render method for player, picks sprite based on direction and whether they are walking
	public void render(Screen screen) {
		if (dir == 0) {
			
			sprite = Sprite.player_up;
			//sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_forward_1;
				} else {
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_right_1;
				} else {
					sprite = Sprite.player_right_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_down;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_back_1;
				} else {
					sprite = Sprite.player_back_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_left_1;
				} else {
					sprite = Sprite.player_left_2;
				}
			}
		}
		inventory.render(screen, x+100, y+100);
		screen.renderPlayer(x-8, y-8, sprite);
		if (getHit) {
			screen.renderTile(x-8, y-8, Sprite.hitAnim); // draws hit sprite over player if hit
		}
		
		
		//screen.renderPlayer(x+16, y-16, sprite2);
		
		
	}
	
	public String toString() {
		return x + " | " + y + " | Player | " + id;
	}
	
	
	
	public boolean checkLevel() {
		if (experience >= experienceToLevel) {
			
			experience = experience-experienceToLevel;
			
			rank++;
			experienceToLevel = rank*25;
			healthMax = health = 400 + 50*rank;
			energyMax = energy = 100 + 75* rank;
			System.out.println("Level Up " + healthMax);
			return true;
		}
		return false;
	}
	
	
	
	
}
