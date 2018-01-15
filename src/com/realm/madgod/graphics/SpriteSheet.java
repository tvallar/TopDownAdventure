package com.realm.madgod.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	//Spritesheet holds the pixels of the spritesheet file
	private String path; // filepath
	public final int WIDTH; // file width
	public final int HEIGHT; // file height
	public int[] pixels; // file pixels
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/tilesheet_final.png", 577, 339);
	
	//Constructor for spritesheet
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		System.out.println(pixels.length);
		load();
	}
	//Loads the pixels from the file
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			System.out.println(w +" | " + h);
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
