package com.realm.madgod;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.realm.madgod.entity.mob.Player;
import com.realm.madgod.graphics.Screen;
import com.realm.madgod.graphics.Sprite;
import com.realm.madgod.gui.PlayerGui;
import com.realm.madgod.input.Keyboard;
import com.realm.madgod.input.Mouse;
import com.realm.madgod.level.GrassLevel;
import com.realm.madgod.level.Level;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private static int width = 450;
	private static int height = 252; //width / 16 * 9;
	private static int scale = 3;
	public static String title = "RealmOfTheMadCuban";
	
	
	
	private Thread thread;
	private JFrame frame;
	public static Keyboard key;
	private boolean running = false;
	private Player player;
	private Sprite sprite;
	private PlayerGui gui;
	private Level level;
	
	public static final int TILE_SIZE = 16;
	
	
	public Point playerPoint;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	//Constructor for game, intitializes frame, screen and keylistener
	public Game() {
		
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		Level.levels.add(new GrassLevel("/textures/level1Final.png"));
		Level.currentLev = Level.levels.get(0);
		player = new Player(200, 200, key);
		
		initLevel(0);
		gui = new PlayerGui(player, size.width, size.height);
		
	}
	
	//Initializes level, adds player, and generates items and creatures
	public void initLevel(int id) {
		level = Level.currentLev;
		player.init(level);
		level.getBeings().add(player);
		
		level.add(player);
		level.generateCreatures();
		level.generateItems();
		
		player.setPos(100, 100);
	}
	
	public static int getWindowWidth() {
		return width*scale;
		
	}
	
	public static int getWindowHeight() {
		return height*scale;
	}
	
	
	public synchronized void start() {
		//System.out.println("Test 3");
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
		
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			//System.exit(0);
		}
		
	}
	
	//Main run method, updates all items and renders frame, Updates is limited to 60 times per second
	//Frames render as many times as possible
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(updates + "ups, " + frames + "fps");
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				frames = 0;
				updates = 0;
				
			}
		}
		stop();
	}
	
	//Calls update on all objects in the word, level update updates all entities in world
	//key update checks for keys pressed
	public void update() {
		key.update();
		//player.update();
		level.update();
		
		
		
		
	}
	
	//Calls render on all objects that are part of the game
	//The level render method renders the map and all entities on screen
	public void render() {
		
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width/2;
		int yScroll = player.y - screen.height/2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
			
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		gui.draw(g);
		
		g.dispose();
		bs.show();
	}
	//Create game and start thread
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	

}
