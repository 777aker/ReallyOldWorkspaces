import java.awt.Color;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Turtle extends JFrame implements Runnable {
	
	private int speed;
	private double angle;
	public double x;
	public double y;
	public Color tColor;
	public int penWidth;
	ArrayList<Integer> moveAmounts = new ArrayList<Integer>();
	ArrayList<Double> angles = new ArrayList<Double>();
	ArrayList<Color> colors = new ArrayList<Color>();
	
	
	//array of angles and move amounts that gets rid of the old one after it has finished bc need that
	
	public Turtle() {
		x = Toolkit.getDefaultToolkit().getScreenSize().width/2;
		y = Toolkit.getDefaultToolkit().getScreenSize().height/2;
		angle = 0;
		speed = 1;
		tColor = Color.WHITE;
		penWidth = 1;
	}
	
	public int getAngle() {
		int angle = (int) Math.toDegrees(this.angle);
		return angle;
	}
	
	public Point getPos() {
		return new Point((int)x, (int)y);
	}
	
	public void move() {
		if(!moveAmounts.isEmpty()) {
			if(moveAmounts.get(0) < 0) {
				moveAmounts.set(0, moveAmounts.get(0) + 1);
				x += Math.cos(angle);
				y += Math.sin(angle);
			}
			if(moveAmounts.get(0) > 0) {
				moveAmounts.set(0, moveAmounts.get(0) - 1);
				x += Math.cos(angle);
				y += Math.sin(angle);
			}
			if(moveAmounts.get(0) == 0) {
				moveAmounts.remove(0);
				if(!angles.isEmpty()) {
					angle += angles.get(0);
					angles.remove(0);	
				}
				if(!colors.isEmpty()) {
					tColor = colors.get(0);
					colors.remove(0);
				}
			}
		}
	}
	
	public void forward(int x) {
		moveAmounts.add(x);
	}
	
	public void backward(int x) {
		moveAmounts.add(x);
	}
	
	public void tColor(Color color) {
		colors.add(color);
	}
	
	public void right(int x) {
		angles.add(Math.toRadians(x));
	}
	
	public void left(int x) {
		angles.add(Math.toRadians(-x));
	}
	
	public void moveto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void resetAngle() {
		angle = 0;
	}
	
	public void penWidth(int x) {
		penWidth = x;
	}
	
	public void speed(int x) {
		speed = x;
	}
	
	public void draw(Graphics g) {
		g.setColor(tColor);
		g.fillRect((int)x, (int)y, penWidth, penWidth);
	}

	@Override
	public void run() {
		try {
			while(true) {
				move();
				Thread.sleep(speed);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
}
