package mainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainSatan extends JFrame {
	String drawstate = "default";
	static int FWidth = 1200;
	static int FHeight = 900;
	Image dbImage;
	Graphics dbg;
	static Player player = new Player(FWidth/2, FHeight/2, 1);
	Thread p1 = new Thread(player);
	Thread b1 = new Thread(player.b1);
	Thread e1 = new Thread(player.e1);
	Thread e2 = new Thread(player.e2);
	Thread b2 = new Thread(player.b2);
	Thread b3 = new Thread(player.b3);
	Thread b4 = new Thread(player.b4);
	Rectangle options = new Rectangle(FWidth/2-50, FHeight/2+50, 100, 25);
	boolean oHover;
	Rectangle home = new Rectangle(FWidth/2-50, FHeight/2+50, 100, 25);
	boolean hHover;
	Rectangle start = new Rectangle(FWidth/2-50, FHeight/2-50, 100, 25);
	boolean sHover;
	public MainSatan() {
		this.setTitle("the game name here");
		this.setSize(FWidth, FHeight);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
		this.addKeyListener(new AL());
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseHandler());
	}
	public static void main(String[] args) {
		MainSatan m = new MainSatan();
	}
	
	public void addImage()
	{
		this.add(new JLabel(new ImageIcon("C:/Users/michael/Pictures/My Screen Shots/star.jpg")));
		
		
	}
	public void startGame() {
		drawstate = "game";
		p1.start();
		b1.start();
		e1.start();
		e2.start();
		b2.start();
		b3.start();
		b4.start();
	}
	public void paint(Graphics g){
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		draw(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void drawLocImg(String filePath, int xLoc, int yLoc, Graphics g)
	{
		File file1 = new File(filePath);
		
		BufferedImage img1 = null;
		try {
			img1 = ImageIO.read(file1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(img1, xLoc, yLoc, this);		
	}
	
	
	public void draw(Graphics g) {
		
		for(int i=0; i<100; i+=20)
		{
			drawLocImg("C:/Users/michael/Pictures/My Screen Shots/star.png", 180+i,100+i,g);
		}
		
		switch (drawstate) { 
		case "game":
			player.draw(g);
			player.b1.draw(g);
			player.e1.draw(g);
			player.e2.draw(g);
			player.b2.draw(g);
			player.b3.draw(g);
			player.b4.draw(g);
			break;
		case "options":
			g.setFont(new Font("Arial", Font.BOLD, 26));
			g.setColor(Color.BLACK);
			g.drawString("No options yet :P", FWidth/2, FHeight/2);
			homebutton(g);
			break;
		default:
			if(sHover)
				g.setColor(Color.PINK);
			else
				g.setColor(Color.CYAN);
			g.fillRect(start.x, start.y, start.width, start.height);
			g.setFont(new Font("Arial", Font.BOLD, 12));
			g.setColor(Color.BLACK);
			g.drawString("Start Game", start.x+17, start.y +17);
			if(oHover)
				g.setColor(Color.PINK);
			else
				g.setColor(Color.CYAN);
			g.fillRect(options.x, options.y, options.width, options.height);
			g.setFont(new Font("Arial", Font.BOLD, 12));
			g.setColor(Color.BLACK);
			g.drawString("options", options.x+30, options.y+17);
			break;
		}
		repaint();
	}
	public Graphics homebutton(Graphics g) {
		if(hHover)
			g.setColor(Color.PINK);
		else
			g.setColor(Color.CYAN);
		g.fillRect(home.x, home.y, home.width, home.height);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.BLACK);
		g.drawString("back", home.x+39, home.y+17);
		return g;
	}
	public void setScreenSize(int fw, int fh) {	
		FWidth = fw;
		FHeight = fh;
	}
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			main: switch(drawstate) {
			case "game":
				player.keyPressed(e);
				if(!Bullets.f1) {
					player.b1.keyPressed(e);
					break main;
				}
				if(!Bullets.f2) {
					player.b2.keyPressed(e);
					break main;
				}
				if(!Bullets.f3) {
					player.b3.keyPressed(e);
					break main;
				}
				if(!Bullets.f4) {
					player.b4.keyPressed(e);
					break main;
				}
				default:
					break;
			}
			}
		public void keyReleased(KeyEvent e) {
			switch(drawstate) {
			case "game":
				player.b1.keyReleased(e);
				player.b2.keyReleased(e);
				player.b3.keyReleased(e);
				player.b4.keyReleased(e);
				player.keyReleased(e);
				break;
				default:
					break;
			}
		}
	}
	public class MouseHandler extends MouseAdapter {
		public void mouseMoved(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			if(mx > start.x && mx < start.x+start.width && my > start.y && my < start.y+start.height)
				sHover = true;
			else
				sHover = false;
			if(mx > options.x && mx < options.x+options.width && my > options.y && my < options.y+options.height)
				oHover = true;
			else
				oHover = false;
			if(mx > home.x && mx < home.x+home.width && my > home.y && my < home.y+home.height)
				hHover = true;
			else
				hHover = false;
		}
		public void mousePressed(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			switch(drawstate) {
			case "game":
				break;
			case "options":
				if(mx > home.x && mx < home.x+home.width && my > home.y && my < home.y+home.height)
					drawstate = "default";
				break;
			case "default":
				if(mx > start.x && mx < start.x+start.width && my > start.y && my < start.y+start.height)
					startGame();
				if(mx > options.x && mx < options.x+options.width && my > options.y && my < options.y+options.height)
					drawstate = "options";
				break;
			default:
				System.out.println("wtf in mouse pressed bc drawstate");
			}
		}
		public void mouseReleased(MouseEvent e) {
			
		}
	}
}
