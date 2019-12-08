import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainClass extends JFrame {

	//first things first
	//dimensions of the computer screen
	public final static Dimension SCREEN_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
	
	//lets make it fun too so users like it, stars in the background :)
	ArrayList<Point> stars = new ArrayList<Point>();
	//lets turn them off by default so it looks professional ish
	boolean starsOn = false;
	
	//lets make the window
	public MainClass() {
		//making it fullscreen and pretty
		this.setSize(SCREEN_SIZE);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setBackground(Color.DARK_GRAY);
		//now lets add some listeners
		this.addKeyListener(new KeyListener());
		this.addMouseListener(new MouseListener());
		this.addMouseMotionListener(new MouseListener());
	}
	
	//program starts here
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//lets start the window, and therefore the program
		MainClass mainClass = new MainClass();
	}
	//public debugging variable to switch on or off so easy to go from debugging to actual program
	public static boolean debugging = true;
	//variables just so easier to change state without being all like state = this int or this int and 4 and 5 which is complicated
	public static final int MAINMENU = 0, ARRAYS = 1, LINKEDLISTS = 2, QUEUES = 3, STACKS = 4, TREES = 5, HASHES = 6;
	//the state of the program so we know what to show user
	public static int programState = MAINMENU;
	//font size
	public static final int fontSize = SCREEN_SIZE.height/50;
	//the main font for the program
	Font mainFont = new Font("Times New Roman", Font.BOLD, fontSize);
	//Don't know how many of each they will want so ArrayList for storing every object instance they could want in case comparing multiple at the
	//same time
	ArrayList<Arrays> arraysList = new ArrayList<Arrays>();
	ArrayList<Hashes> hashesList = new ArrayList<Hashes>();
	ArrayList<LinkedLists> linkedListsList = new ArrayList<LinkedLists>();
	ArrayList<Queues> queueList = new ArrayList<Queues>();
	ArrayList<Stacks> stacksList = new ArrayList<Stacks>();
	ArrayList<Trees> treeList = new ArrayList<Trees>();
	//button rectangles
	public static final Rectangle arrayMenu = new Rectangle(SCREEN_SIZE.width/20-SCREEN_SIZE.width/30, SCREEN_SIZE.height/15, SCREEN_SIZE.width/15, SCREEN_SIZE.height/25);
	
	//drawing things in this method
	public void draw(Graphics g) {
		if(debugging) {
			g.setFont(mainFont);
			g.setColor(Color.BLACK);
			g.drawString(mouse.x + ":" + mouse.y, SCREEN_SIZE.width-200, SCREEN_SIZE.height-50);
		}
		switch(programState) {
		case ARRAYS:
			g.setColor(Color.GRAY);
			g.fillRect(SCREEN_SIZE.width/10+SCREEN_SIZE.width/50, arrayMenu.y, SCREEN_SIZE.width/10, SCREEN_SIZE.height-SCREEN_SIZE.height/10);
			break;
		}
		//don't forget to use SCREEN_SIZE so optimized for any screen size
		for(Arrays array : arraysList)
			array.draw(g);
		for(Hashes hash : hashesList)
			hash.draw(g);
		for(LinkedLists linkedList : linkedListsList)
			linkedList.draw(g);
		for(Queues queue : queueList)
			queue.draw(g);
		for(Stacks stack : stacksList)
			stack.draw(g);
		for(Trees tree : treeList)
			tree.draw(g);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, SCREEN_SIZE.width/10, SCREEN_SIZE.height);
		if(!arrayMenu.intersects(mouse))
			g.setColor(Color.CYAN);
		else
			g.setColor(Color.MAGENTA);
		g.fillRect(arrayMenu.x, arrayMenu.y, arrayMenu.width, arrayMenu.height);
		g.setFont(mainFont);
		g.setColor(Color.BLACK);
		g.drawString("Array Menu", arrayMenu.x+arrayMenu.width/20, arrayMenu.y+3*arrayMenu.height/4);
		
	}
	//mouse variable for all to use
	//rectangle because rectangle have built in intersect method
	public static Rectangle mouse = new Rectangle(0, 0, 4, 4);
	public class MouseListener extends MouseAdapter {
		//when the mouse is pressed down
		public void mousePressed(MouseEvent m) {
			if(arrayMenu.intersects(mouse))
				if(programState == ARRAYS)
					programState = MAINMENU;
				else
					programState = ARRAYS;
			for(Arrays array : arraysList)
				array.MousePressed(m, mouse);
			for(Hashes hash : hashesList)
				hash.MousePressed(m, mouse);
			for(LinkedLists linkedList : linkedListsList)
				linkedList.MousePressed(m, mouse);
			for(Queues queue : queueList)
				queue.MousePressed(m, mouse);
			for(Stacks stack : stacksList)
				stack.MousePressed(m, mouse);
			for(Trees tree : treeList)
				tree.MousePressed(m, mouse);
		}
		//when the mouse is released
		public void mouseReleased(MouseEvent m) {
			for(Arrays array : arraysList)
				array.MouseReleased(m, mouse);
			for(Hashes hash : hashesList)
				hash.MouseReleased(m, mouse);
			for(LinkedLists linkedList : linkedListsList)
				linkedList.MouseReleased(m, mouse);
			for(Queues queue : queueList)
				queue.MouseReleased(m, mouse);
			for(Stacks stack : stacksList)
				stack.MouseReleased(m, mouse);
			for(Trees tree : treeList)
				tree.MouseReleased(m, mouse);
		}
		//if the mouse moves update position of mouse
		public void mouseMoved(MouseEvent m) {
			mouse.x = m.getX()-2;
			mouse.y = m.getY()-2;
			for(Arrays array : arraysList)
				array.MouseMoved(m, mouse);
			for(Hashes hash : hashesList)
				hash.MouseMoved(m, mouse);
			for(LinkedLists linkedList : linkedListsList)
				linkedList.MouseMoved(m, mouse);
			for(Queues queue : queueList)
				queue.MouseMoved(m, mouse);
			for(Stacks stack : stacksList)
				stack.MouseMoved(m, mouse);
			for(Trees tree : treeList)
				tree.MouseMoved(m, mouse);
		}
		//when the mouse is pressed and released in quick succesion
		public void MouseClicked(MouseEvent m) {}
	}
	
	public class KeyListener extends KeyAdapter {
		//when a key is pressed lets do this
		@SuppressWarnings("static-access")
		public void keyPressed(KeyEvent e) {
			//exits program if esc typed
			if(e.getKeyCode() == e.VK_ESCAPE) {
				System.exit(0);
			}
		}
		//not gonna use probably but just in case when a key is released or typed call respectively
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}

	//double buffering graphics variables and method, don't touch these ever
	Image dbImage;
	Graphics dbg;
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		draw(dbg);
		g.drawImage(dbImage, 0, 0, this);
		repaint();
	}
}
