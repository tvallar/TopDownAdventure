package com.realm.madgod.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.realm.madgod.Game;
import com.realm.madgod.entity.item.HealthPotion;
import com.realm.madgod.entity.item.weapons.StarterSword;
import com.realm.madgod.entity.item.weapons.Weapon;
import com.realm.madgod.entity.mob.Boss;
import com.realm.madgod.entity.mob.Creature;
import com.realm.madgod.level.tile.Tile;

public class GrassLevel extends Level {
	// GrassLevel type of level
	public GrassLevel(String path) {
		super(path);
		
		// TODO Auto-generated constructor stub
	}
	
	//Loads level and specifies what type of tile should be placed based on surrounding tiles (corners vs straight sections)
	//Generates the random parts of the levels
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(GrassLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			usedTiles = new int[w*h];
			//System.out.println(Arrays.toString(tiles));
			image.getRGB(0, 0, w, h, tiles, 0, w);
			for (int i = 0; i < tiles.length; i ++) {
				if (tiles[i] != -1){
					if (tiles[i] == 0xFF098701) constructBuilding(i, Building.castleSmall);
					if (tiles[i] == 0xFF098702) constructBuilding(i, Building.townSmall);
					usedTiles[i] = 1;
				}
			}
			//checks for tiles that have a specific sprite for surrounding tiles (walls and abysses - corners and intersections)
			for (int i = 0; i < tiles.length; i ++) {
				//ensures the walls corners and intersections are set to the correct sprite
				if(tiles[i] == 0xFF00FFaa){
					int y = i/width;
					int x = i%width;
					boolean up = false;
					boolean right = false;
					boolean down = false;
					boolean left = false;
					if (tiles[x-1+y*width] == 0xFF00FFaa || tiles[x-1+y*width] == 0xFF00FFab || tiles[x-1+y*width] == 0xFF00FFac || tiles[x-1+y*width] == 0xFF00FFad || tiles[x-1+y*width] == 0xFF00FFae || tiles[x-1+y*width] == 0xFF00FFaf || tiles[x-1+y*width] == 0xFF00FFba || tiles[x-1+y*width] == 0xFF00FFbb || tiles[x-1+y*width] == 0xFF00FFbc || tiles[x-1+y*width] == 0xFF00FFbd || tiles[x-1+y*width] == 0xFF00FFbe || tiles[x-1+y*width] == 0xFF00FFbf || tiles[x-1+y*width] == 0xFF00FFca || tiles[x-1+y*width] == 0xFF00FFcb || tiles[x-1+y*width] == 0xFF00FFcc || tiles[x-1+y*width] == 0xFF00FFcd || tiles[x-1+y*width] == 0xFF00FFce) left = true;
					if (tiles[x+1+y*width] == 0xFF00FFaa || tiles[x+1+y*width] == 0xFF00FFab || tiles[x+1+y*width] == 0xFF00FFac || tiles[x+1+y*width] == 0xFF00FFad || tiles[x+1+y*width] == 0xFF00FFae || tiles[x+1+y*width] == 0xFF00FFaf || tiles[x+1+y*width] == 0xFF00FFba || tiles[x+1+y*width] == 0xFF00FFbb || tiles[x+1+y*width] == 0xFF00FFbc || tiles[x+1+y*width] == 0xFF00FFbd || tiles[x+1+y*width] == 0xFF00FFbe || tiles[x+1+y*width] == 0xFF00FFbf || tiles[x+1+y*width] == 0xFF00FFca || tiles[x+1+y*width] == 0xFF00FFcb || tiles[x+1+y*width] == 0xFF00FFcc || tiles[x+1+y*width] == 0xFF00FFcd || tiles[x+1+y*width] == 0xFF00FFce) right = true;
					if ( y > 0 && (tiles[x+(y-1)*width] == 0xFF00FFaa || tiles[x+(y-1)*width] == 0xFF00FFab || tiles[x+(y-1)*width] == 0xFF00FFac || tiles[x+(y-1)*width] == 0xFF00FFad || tiles[x+(y-1)*width] == 0xFF00FFae || tiles[x+(y-1)*width] == 0xFF00FFaf || tiles[x+(y-1)*width] == 0xFF00FFba || tiles[x+(y-1)*width] == 0xFF00FFbb || tiles[x+(y-1)*width] == 0xFF00FFbc || tiles[x+(y-1)*width] == 0xFF00FFbd || tiles[x+(y-1)*width] == 0xFF00FFbe || tiles[x+(y-1)*width] == 0xFF00FFbf || tiles[x+(y-1)*width] == 0xFF00FFca || tiles[x+(y-1)*width] == 0xFF00FFcb || tiles[x+(y-1)*width] == 0xFF00FFcc || tiles[x+(y-1)*width] == 0xFF00FFcd || tiles[x+(y-1)*width] == 0xFF00FFce)) up = true;
					if (tiles[x+(y+1)*width] == 0xFF00FFaa || tiles[x+(y+1)*width] == 0xFF00FFab || tiles[x+(y+1)*width] == 0xFF00FFac || tiles[x+(y+1)*width] == 0xFF00FFad || tiles[x+(y+1)*width] == 0xFF00FFae || tiles[x+(y+1)*width] == 0xFF00FFaf || tiles[x+(y+1)*width] == 0xFF00FFba || tiles[x+(y+1)*width] == 0xFF00FFbb || tiles[x+(y+1)*width] == 0xFF00FFbc || tiles[x+(y+1)*width] == 0xFF00FFbd || tiles[x+(y+1)*width] == 0xFF00FFbe || tiles[x+(y+1)*width] == 0xFF00FFbf || tiles[x+(y+1)*width] == 0xFF00FFca || tiles[x+(y+1)*width] == 0xFF00FFcb || tiles[x+(y+1)*width] == 0xFF00FFcc || tiles[x+(y+1)*width] == 0xFF00FFcd || tiles[x+(y+1)*width] == 0xFF00FFce) down = true;
					//ends on left side, connects on right
					if (right && !left && !up && !down) tiles[i] = 0xFF00FFab;
					//ends on right side, connects on left
					if (!right && left && !up && !down) tiles[i] = 0xFF00FFac;
					// ends on bottom, connects on top
					if (!right && !left && up && !down) tiles[i] = 0xFF00FFad;
					// ends on top, connects on bottom
					if (!right && !left && !up && down) tiles[i] = 0xFF00FFae;
					//left right
					if (right && left && !up && !down) tiles[i] = 0xFF00FFaf;
					// up down
					if (!right && !left && up && down) tiles[i] = 0xFF00FFba;
					
					//  connect top and right
					if (right && !left && up && !down) tiles[i] = 0xFF00FFbe;
					// connect bottom and right
					if (right && !left && !up && down) tiles[i] = 0xFF00FFbb;
					// connect left and up
					if (!right && left && up && !down) tiles[i] = 0xFF00FFbd;
					// connect left and down
					if (!right && left && !up && down) tiles[i] = 0xFF00FFbc;
					
					// connects on left right and up
					if (right && left && up && !down) tiles[i] = 0xFF00FFca;
					// connects on left right and down
					if (right && left && !up && down) tiles[i] = 0xFF00FFbf;
					// connects on up down and left
					if (!right && left && up && down) tiles[i] = 0xFF00FFcd;
					// connects on up down and rigth
					if (right && !left && up && down) tiles[i] = 0xFF00FFcb;
					// connects on all
					if (right && left && up && down) tiles[i] = 0xFF00FFce;
				}
				//Makes sure the corners of the pit are correct
				if(tiles[i] == 0xFFaaaa01){
					int y = i/width;
					int x = i%width;
					boolean up = false;
					boolean right = false;
					boolean down = false;
					boolean left = false;
					if (tiles[x-1+y*width] == 0xFFaaaa01 || tiles[x-1+y*width] == 0xFFaaaa02 || tiles[x-1+y*width] == 0xFFaaaa03 || tiles[x-1+y*width] == 0xFFaaaa04 || tiles[x-1+y*width] == 0xFFaaaa06 || tiles[x-1+y*width] == 0xFFaaaa07 || tiles[x-1+y*width] == 0xFFaaaa08 || tiles[x-1+y*width] == 0xFFaaaa09 || tiles[x-1+y*width] == 0xFFaaaa10 || tiles[x-1+y*width] == 0xFFaaaa11 || tiles[x-1+y*width] == 0xFFaaaa12 || tiles[x-1+y*width] == 0xFFaaaa13 || tiles[x-1+y*width] == 0xFFaaaa14) left = true;
					if (tiles[x+1+y*width] == 0xFFaaaa01 || tiles[x+1+y*width] == 0xFFaaaa02 || tiles[x+1+y*width] == 0xFFaaaa03 || tiles[x+1+y*width] == 0xFFaaaa04 || tiles[x+1+y*width] == 0xFFaaaa06 || tiles[x+1+y*width] == 0xFFaaaa07 || tiles[x+1+y*width] == 0xFFaaaa08 || tiles[x+1+y*width] == 0xFFaaaa09 || tiles[x+1+y*width] == 0xFFaaaa10 || tiles[x+1+y*width] == 0xFFaaaa11 || tiles[x+1+y*width] == 0xFFaaaa12 || tiles[x+1+y*width] == 0xFFaaaa13 || tiles[x+1+y*width] == 0xFFaaaa14) right = true;
					if ( y > 0 && (tiles[x+(y-1)*width] == 0xFFaaaa01 || tiles[x+(y-1)*width] == 0xFFaaaa02 || tiles[x+(y-1)*width] == 0xFFaaaa03 || tiles[x+(y-1)*width] == 0xFFaaaa04 || tiles[x+(y-1)*width] == 0xFFaaaa06 || tiles[x+(y-1)*width] == 0xFFaaaa07 || tiles[x+(y-1)*width] == 0xFFaaaa08 || tiles[x+(y-1)*width] == 0xFFaaaa09 || tiles[x+(y-1)*width] == 0xFFaaaa10 || tiles[x+(y-1)*width] == 0xFFaaaa11 || tiles[x+(y-1)*width] == 0xFFaaaa12 || tiles[x+(y-1)*width] == 0xFFaaaa13 || tiles[x+(y-1)*width] == 0xFFaaaa14)) up = true;
					if (tiles[x+(y+1)*width] == 0xFFaaaa01 || tiles[x+(y+1)*width] == 0xFFaaaa02 || tiles[x+(y+1)*width] == 0xFFaaaa03 || tiles[x+(y+1)*width] == 0xFFaaaa04 || tiles[x+(y+1)*width] == 0xFFaaaa06 || tiles[x+(y+1)*width] == 0xFFaaaa07 || tiles[x+(y+1)*width] == 0xFFaaaa08 || tiles[x+(y+1)*width] == 0xFFaaaa09 || tiles[x+(y+1)*width] == 0xFFaaaa10 || tiles[x+(y+1)*width] == 0xFFaaaa11 || tiles[x+(y+1)*width] == 0xFFaaaa12 || tiles[x+(y+1)*width] == 0xFFaaaa13 || tiles[x+(y+1)*width] == 0xFFaaaa14) down = true;
					if (up || down) {
						if (tiles[x-1+y*width] == 0xFFaaaa05)tiles[i] = 0xFFaaaa02;
						else tiles[i] = 0xFFaaaa01;
					}
					
					if (right || left) {
						if (tiles[x+(y+1)*width] == 0xFFaaaa05)tiles[i] = 0xFFaaaa04;
						else tiles[i] = 0xFFaaaa03;;
					}
					
					
					if (right && left && !up && !down) {
						if (tiles[x + (y-1)*width] == 0xFFaaaa05)tiles[i] = 0xFFaaaa03;
						else tiles[i] = 0xFFaaaa04;
					}
					if (!right && !left && up && down) {
						if (tiles[x+1 + y*width] == 0xFFaaaa05)tiles[i] = 0xFFaaaa01;
						else tiles[i] = 0xFFaaaa02;
					}
					// up down
					if (right && !left && up && !down) {
						if (tiles[x+(y+1)*width] == 0xFFaaaa05)tiles[i] = 0xFFaaaa11;
						else tiles[i] = 0xFFaaaa06;
					}
					if (!right && left && up && !down) {
						if (tiles[x+(y+1)*width] == 0xFFaaaa05)tiles[i] = 0xFFaaaa12;
						else tiles[i] = 0xFFaaaa07;
					}
					if (right && !left && !up && down) {
						if (tiles[x+(y-1)*width] == 0xFFaaaa05)tiles[i] = 0xFFaaaa14;
						else tiles[i] = 0xFFaaaa09;
					} if (!right && left && !up && down) {
						if (tiles[x+(y-1)*width] == 0xFFaaaa05)tiles[i] = 0xFFaaaa13;
						else tiles[i] = 0xFFaaaa08;
					}
					
				}
				// if there is no set tiles, generate random structures
				//lakes, trees, forrests, patches etc.
				if(tiles[i] == -1) {
					
					int a = r.nextInt(1000);
					if (a == 0) {
						generateLake (tiles, i, r.nextInt(25) + 10, r.nextInt(25) + 10);
					} else if (a >= 1 && a <=10) {
						generateTree(tiles, i, 0);
					} else if (a > 18 && a < 28) {
						tiles[i] = 0xFF35413a;
					} else if (a == 11 || a == 12) {
						generateForest(tiles, i, r.nextInt(20) + 10, r.nextInt(20) + 5);
					} else if (a == 13 || a == 14 || a == 15) {
						generateForest(tiles, i, r.nextInt(10) + 5, r.nextInt(10) + 5);
					} else if (a == 16 || a == 17 || a == 18) {
						tiles[i] = 0xFFe1fbab;
					}else if (a == 28 || a == 29) {
						generatePatch(tiles, i, r.nextInt(10) + 5, r.nextInt(10) + 5, 0xFF00FFFF);
					} else {
						tiles[i] = 0xFF00FF00;
					}
					
				} else if (tiles[i] == 0xFFa4fcff){
					//Sets the tiles for a specific zone that is randomized differently from the rest
					int a = r.nextInt(1000);
					if (a >= 0 && a <= 100) tiles[i] = 0xFF00FFF1;
					else if (a >= 100 && a < 120) tiles[i] = 0xFF00FFF2;
					else if (a >= 120 && a < 125) tiles[i] = 0xFF00FFF3;
					else if (a >= 125 && a < 130) tiles[i] = 0xFF00FFF4;
					else if (a >= 130 && a < 135) tiles[i] = 0xFF00FFF5;
					else if (a >= 135 && a < 140) tiles[i] = 0xFF00FFF6;
					
					else tiles[i] = 0xFF00FFFF;
				}
			}
			//System.out.println(Arrays.toString(tiles));
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could Not load level file");
		}
	}
	
	
	
	
		
		
	
	
	//Zone 1 = 0,17 to 200, 240
	//Zone 2 = 
	//Generates creatures for each zone
	public void generateCreatures() {
		// zone one bounds
		int widthZone1 = 200; 
		int heightZone1 = 240-17;
		// zone final bounds
		int widthFinalZone = 400-291; 
		int heightFinalZone = 85-7;
		int x, y;
		
		//Adds enemies to zone one, 50 enemies total in that zone
		//Random location, enemy type 0
		for (int i = 0; i < 50; i ++) {
			do {
			x = this.r.nextInt(widthZone1*Game.TILE_SIZE) + 0;
			y = this.r.nextInt(heightZone1*Game.TILE_SIZE) + 23*Game.TILE_SIZE;
			} while (getTile(x/Game.TILE_SIZE, y/Game.TILE_SIZE).solid());
			Creature temp = Creature.spawn(0, x, y);
			add(temp);
		}
		//Adds enemies to final zone, 20 enemies in total, random location
		//enemy type 1
		for (int i = 0; i < 20; i ++) {
			do {
				x = this.r.nextInt(widthFinalZone*Game.TILE_SIZE) + 293*Game.TILE_SIZE;
				y = this.r.nextInt(heightFinalZone*Game.TILE_SIZE) + 7*Game.TILE_SIZE;
			} while (getTile(x/Game.TILE_SIZE, y/Game.TILE_SIZE).solid());
			Creature temp = Creature.spawn(1, x, y);
			add(temp);
			
		}
		
		add(Boss.flameGuy); //Adds the flame boss (first boss) to the game, position set in boss class
	}
	
	//Generates items to the game, no randomization yet
	public void generateItems() {
		Weapon starter = new StarterSword();
		starter.setPosition(140, 100);
		//Add starter gear
		add(starter);
		add(new HealthPotion(this, 160, 100));
		add(new HealthPotion(this, 180, 100));
	}

}
