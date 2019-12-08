package Me;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player implements Runnable {

	public Rectangle player = new Rectangle(MainCereal.screenDimension.width/2, MainCereal.screenDimension.height - 50, 20, 20);
	
	boolean up, down, left, right;
	int level = 0;
	boolean jump = false;
	boolean fall = true;
	
	final int G = 1;
	
	int xDir = 0;
	int yDir = 0;
	
	public Player() {}
	
	public void move() {
		
		boundaries();
		player.x += xDir;
		player.y += yDir;
		if(Level.interects(player)) {
			fall = false;
			player.x -= xDir;
			player.y -= yDir;
			jump = true;
		} else {
			fall = true;
		}
		
	}
	
	public int customRectx, customRecty;
	
	public void setFirstCorner(int x, int y) {
		customRectx = x;
		customRecty = y;
	}
	
	public void drawRect(int x, int y) {
		Level.addRect(customRectx, customRecty, x, y);
	}
	
	public void boundaries() {
		if(player.x <= 441)
			player.x = 441;
		if(player.y <= 0)
			player.y = 0;
		if(player.x >= MainCereal.screenDimension.width - player.width)
			player.x = MainCereal.screenDimension.width - player.width;
		if(player.y >= MainCereal.screenDimension.height - player.height - 5) {
			player.y = MainCereal.screenDimension.height - player.height - 5;
			jump = true;
		}
		if(fall)
			if(player.y != MainCereal.screenDimension.height - player.height - 5)
				if(yDir > 6)
					yDir = 6;
				else
					yDir += G;
	}
	
	public void jump() {
		
		
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(player.x, player.y, player.width, player.height);
		Level.draw(g);
	}
	
	public void run() {
		try {
			while(true) {
				move();
				Thread.sleep(10);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}
