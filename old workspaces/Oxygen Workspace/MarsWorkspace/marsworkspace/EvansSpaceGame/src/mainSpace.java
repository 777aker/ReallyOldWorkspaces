import java.awt.Color;
import java.awt.Cursor;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class mainSpace extends JFrame {

	//screen size
	public static int FHeight = 800;
	public static int FWidth = 1280;
	
	public final boolean debugging = false;
	
	//mouse moved tracking
	public static int Mx, My;
	
	//ints for loading screen
	public int time1, time2;
	
	//the nothing int
	public static int nothing;
	
	//double buffering
	Image dbImage;
	Graphics dbg;
	
	//menu fonts
	public static final Font menu = new Font("Arial", Font.BOLD, 12);
	
	//cursors
	Image mcursor = createImg("C:/Users/michael/Desktop/resources/mcursor.png");
	Image gcursor = createImg("C:/Users/michael/Desktop/resources/gcursor.png");
	Cursor mc = Toolkit.getDefaultToolkit().createCustomCursor(mcursor, new Point(10,7), "custom cursor");
	Cursor gc = Toolkit.getDefaultToolkit().createCustomCursor(gcursor, new Point(16,16), "custom cursor2");
	
	//game start stuff
	boolean gamestart = true;
	int runtimes = 0;
	long start = System.currentTimeMillis() / 1000;
	long stop;
	int percent = 0;
	
	//the player
	static player Player = new player(1);
	Thread p1 = new Thread(Player);
	Image ship1 = createImg("C:/Users/michael/Desktop/resources/ship1.png");
	Image ship2 = createImg("C:/Users/michael/Desktop/resources/ship2.png");
	Image ship3 = createImg("C:/Users/michael/Desktop/resources/ship3.png");
	Image ship4 = createImg("C:/Users/michael/Desktop/resources/ship4.png");
	Image ship5 = createImg("C:/Users/michael/Desktop/resources/ship5.png");
	Image ship6 = createImg("C:/Users/michael/Desktop/resources/ship6.png");
	Image ship7 = createImg("C:/Users/michael/Desktop/resources/ship7.png");
	Image ship8 = createImg("C:/Users/michael/Desktop/resources/ship8.png");
	public static int ship = 1;
	
	//images
	public Image backg;
	public Image button = createImg("C:/Users/michael/Desktop/resources/button1.png");
	public Image hbutton = createImg("C:/Users/michael/Desktop/resources/button1h.png");
	public final static Image combeet = createImg("C:/Users/michael/Desktop/resources/dicemenu.png");
	public final static Image dice1 = createImg("C:/Users/michael/Desktop/resources/adie1.png");
	public final static Image dice2 = createImg("C:/Users/michael/Desktop/resources/adie2.png");
	public final static Image dice3 = createImg("C:/Users/michael/Desktop/resources/adie3.png");
	public final static Image dice4 = createImg("C:/Users/michael/Desktop/resources/adie4.png");
	public final static Image dice5 = createImg("C:/Users/michael/Desktop/resources/adie5.png");
	public final static Image dice6 = createImg("C:/Users/michael/Desktop/resources/adie6.png");
	
	//background spot
	public static int gx = 0;
	public static int gy = 0;
	
	//menu button rectangles
	Rectangle options = new Rectangle(FWidth/2-50, FHeight/2+50, 100, 25);
	Rectangle back = new Rectangle(FWidth/2-50, FHeight/2+50, 100, 25);
	Rectangle screensize = new Rectangle(FWidth/2-75, FHeight/2, 150, 25);
	Rectangle startrect = new Rectangle(FWidth/2-50, FHeight/2-25, 150, 25);
	Rectangle attack = new Rectangle( 100, 420, 150, 25);
	
	//background color
	Color background = new Color(4, 0, 18);
	
	//menu hover rectangles
	boolean OHover;
	boolean BHover;
	boolean SHover;
	boolean updatescreen;
	boolean SsHover;
	
	//idk
	public Graphics mg;
	
	//game state for switching stuffs
	static String gameState = "menu";
	
	//main game window
	public mainSpace() {
		
		this.setTitle("Space Game");
		this.setSize(FWidth, FHeight);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setBackground(background);
		this.setLocationRelativeTo(null);
		this.addKeyListener(new AL());
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseHandler());
		this.setLocationRelativeTo(null);
		this.setCursor(mc);
		
	}
	
	//start
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		mainSpace ms = new mainSpace();
		
	}
	
	//start threads
	public void startGame() {
		p1.start();
	}
	
	//the painter
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		draw(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void sM(String message)
	{
		if(debugging)
		System.out.println(message);
	}
	
	public void drawLocImg(String filePath, int xLoc, int yLoc, Graphics g) {
		File file1 = new File(filePath);
		
		BufferedImage img1 = null;
		try {
			img1 = ImageIO.read(file1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img1, xLoc, yLoc, this);
	}
	public static Image createImg(String filePath) {
		File file1 = new File(filePath);
		
		BufferedImage img1 = null;
		try {
			img1 = ImageIO.read(file1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img1;
	}
	
	//the drawer
	public void draw(Graphics g) {
		
		switch(gameState) {
		
		case "menu":
			if(FWidth == 600 && FHeight == 600) {
				backg = createImg("C:/Users/michael/Desktop/resources/space600600.png");
			}else if(FWidth == 800 && FHeight == 600) {
				backg = createImg("C:/Users/michael/Desktop/resources/space800600.png");
			}else if(FWidth == 1024 && FHeight == 600) {
				backg = createImg("C:/Users/michael/Desktop/resources/space1024600.png");
			}else if(FWidth == 1280 && FHeight == 800) {
				backg = createImg("C:/Users/michael/Desktop/resources/space1280800.png");
			} g.drawImage(backg, gx, gy, null);
			if(gamestart) {
				stop = System.currentTimeMillis() / 1000;
				//make the "loading" screens 5 and 10
				if(debugging) {
					time1 = 2;
					time2 = 4;
				} else {
					time1 = 10;
					time2 = 20;
				}
					if(stop - start == time1) {
						runtimes = 10;
					}
					if(stop - start == time2) {
						runtimes = 20;
					}
					if(stop - start == 1) 
						percent = 0;
					if(stop - start == 2) 
						percent = 20;
					if(stop - start == 3)
						percent = ((int)(Math.random() * 100));
					if(stop - start == 4)
						percent = 30;
					if(stop - start == 5)
						percent = 69;
					if(stop - start == 6)
						percent = 42;
					if(stop - start == 7)
						percent = 29;
					if(stop - start == 8)
						percent = ((int)(Math.random() * 100));
					if(stop - start == 9)
						percent = 49;
					if(stop - start == 10)
						percent = 50;
					if(stop - start == 11) 
						percent = 36;
					if(stop - start == 12) 
						percent = 66;
					if(stop - start == 13)
						percent = 96;
					if(stop - start == 14)
						percent = ((int)(Math.random() * 50) + 50);
					if(stop - start == 15)
						percent = 76;
					if(stop - start == 16)
						percent = 99;
					if(stop - start == 17)
						percent = 100;
					if(stop - start == 18)
						percent = 0;
					if(stop - start == 19)
						percent = 116;
					if(stop - start == 20)
						percent = 112;
					g.setFont(menu);
					g.setColor(Color.MAGENTA);
					g.drawString("" + percent + "%", FWidth / 2 - 10, FHeight - 100);
					for(int i = 0; i < percent; i++) {
						g.fillRect(FWidth/2 - 147 + (i*3), FHeight - 150, 3, 10);
					}
					
			}
			if(gamestart) {
				if(gamestart) {
					if(runtimes < 10) {
						drawLocImg("C:/Users/michael/Desktop/resources/logo.png", FWidth/2 - 150, FHeight/2 - 225, g);
					}
					if(runtimes >= 10) {
						drawLocImg("C:/Users/michael/Desktop/resources/planetcracker1.png", FWidth/2 - 150, FHeight/2 - 180, g);
						if(runtimes == 20) {
							gamestart = false;
						}
					}
				}
			} else {
				drawLocImg("C:/Users/michael/Desktop/resources/menupc.png", FWidth/2 - 85, 75, g);
				drawLocImg("C:/Users/michael/Desktop/resources/menulogo.png", 0, FHeight - 160, g);
				if(OHover)
					g.drawImage(hbutton, options.x, options.y, null);
				else
					g.drawImage(button, options.x, options.y, null);
				g.setFont(menu);
				g.setColor(Color.BLACK);
				g.drawString("Options", options.x+28, options.y+17);
				if(SsHover)
					g.drawImage(hbutton, startrect.x, startrect.y, null);
				else
					g.drawImage(button, startrect.x, startrect.y, null);
				g.setFont(menu);
				g.setColor(Color.BLACK);
				g.drawString("Start Game", startrect.x+20, startrect.y+17);
				}
			break;
		case "options":
			if(FWidth == 600 && FHeight == 600) {
				backg = createImg("C:/Users/michael/Desktop/resources/space600600.png");
			}else if(FWidth == 800 && FHeight == 600) {
				backg = createImg("C:/Users/michael/Desktop/resources/space800600.png");
			}else if(FWidth == 1024 && FHeight == 600) {
				backg = createImg("C:/Users/michael/Desktop/resources/space1024600.png");
			}else if(FWidth == 1280 && FHeight == 800) {
				backg = createImg("C:/Users/michael/Desktop/resources/space1280800.png");
			} g.drawImage(backg, gx, gy, null);
			if(BHover)
				g.drawImage(hbutton, back.x, back.y, null);
			else
				g.drawImage(button, back.x, back.y, null);
			g.setFont(menu);
			g.setColor(Color.BLACK);
			g.drawString("Back", back.x+35, back.y+17);
			if(SHover)
				drawLocImg("C:/Users/michael/Desktop/resources/screensizebuttonh.png", screensize.x, screensize.y, g);
			else
				drawLocImg("C:/Users/michael/Desktop/resources/screensizebutton.png", screensize.x, screensize.y, g);
			g.setFont(menu);
			g.setColor(Color.BLACK);
			g.drawString("ScreenSize:" + " " + FWidth + ", " + FHeight, screensize.x+10, screensize.y+17);
			if(updatescreen) {
				//resizing based on FWidth and FHeight changes
				this.setSize(FWidth, FHeight);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
				Point newLocation = new Point(middle.x - (this.getWidth() / 2), middle.y - (this.getHeight() / 2));
				this.setLocation(newLocation);
				options.x=FWidth/2-50; options.y=FHeight/2+50;
				back.x=FWidth/2-50; back.y=FHeight/2+50;
				screensize.x=FWidth/2-75; screensize.y=FHeight/2;
				startrect.x=FWidth/2-50; startrect.y=FHeight/2-25;
				updatescreen = false;
			}
			break;
		case "game":
			player.px = FWidth / 2;
			player.py = FHeight / 2;
			this.setCursor(gc);
			switch(ship) {
			case 1:
				g.drawImage(ship1, player.px, player.py, null);
				break;
			case 2:
				g.drawImage(ship2, player.px, player.py, null);
				break;
			case 3:
				g.drawImage(ship3, player.px, player.py, null);
				break;
			case 4:
				g.drawImage(ship4, player.px, player.py, null);
				break;
			case 5:
				g.drawImage(ship5, player.px, player.py, null);
				break;
			case 6:
				g.drawImage(ship6, player.px, player.py, null);
				break;
			case 7:
				g.drawImage(ship7, player.px, player.py, null);
				break;
			case 8:
				g.drawImage(ship8, player.px, player.py, null);
				break;
				default:
					sM("um...what is ship");
					break;
			}
			if(player.combat) {
				g.drawImage(button, attack.x, attack.y, null);
				g.setFont(menu);
				g.setColor(Color.BLACK);
				g.drawString("Attack", attack.x + 10, attack.y +17);
			}
			Player.draw(g);
			break;
		case "credits":
			
			break;
			default: 
				System.out.println("idk what the gamestate is");
				break;			
				
				
		
		}
		
		repaint();
		
	}
	
	//draw menu while in game
	public void menudraw(Graphics g) {
		
	}
	
	//key listener / input manager
	public class AL extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
				Player.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			Player.keyReleased(e);
		}
		
	}
	
	//mouse inputs
	public class MouseHandler extends MouseAdapter {
		
		public void mouseMoved(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			Mx = mx;
			My = my;
			switch(gameState) {
			case "menu":
				if(mx > startrect.x && mx < startrect.x+startrect.width && my > startrect.y && my < startrect.y+startrect.height)
					SsHover = true;
				else
					SsHover = false;
				if(mx > options.x && mx < options.x+options.width && my > options.y && my < options.y+options.height)
					OHover = true;
				else
					OHover = false;
				break;
			case "options":
				if(mx > back.x && mx < back.x+back.width && my > back.y && my < back.y+back.height)
					BHover = true;
				else
					BHover = false;
				if(mx > screensize.x && mx < screensize.x+screensize.width && my > screensize.y && my < screensize.y+screensize.height)
					SHover = true;
				else
					SHover = false;
				break;
			case "game":
				if(Mx >= player.px && Mx <= player.px + 25 && My <= player.py && My >= player.py - 33)
					ship = 1;
				else if(Mx >= player.px + 25 && Mx <= player.px + 62.5 && My >= player.py && My <= player.py + 34)
					ship = 2;
				else if(Mx >= player.px && Mx <= player.px + 25 && My >= player.py + 34 && My <= player.py + 67)
					ship = 3;
				else if(Mx <= player.px && Mx >= player.px - 37.5 && My >= player.py && My <= player.py + 34)
					ship = 4;
				
				else if(Mx > player.px + 25 && Mx <= player.px + 62.5 && My < player.py && My >= player.py - 33)
					ship = 5;
				else if(Mx > player.px + 25 && Mx <= player.px + 62.5 && My <= player.py + 67 && My > player.py + 34)
					ship = 6;
				else if(Mx < player.px && Mx >= player.px - 37.5 && My > player.py + 34 && My <= player.py + 67)
					ship = 7;
				else if(Mx < player.px && Mx >= player.px - 37.5 && My < player.py && My >= player.py - 33)
					ship = 8;
				
				else if(Mx > player.px && Mx < player.px + 25 && My > player.py && My < player.py + 34)
					nothing = 0;
				
				else if(Mx >= player.px - 37.5 && Mx <= player.px + 62.5 && My < player.py - 33)
					ship = 1;
				else if(Mx > player.px + 62.5 && My >= player.py - 33 && My <= player.py + 67)
					ship = 2;
				else if(Mx >= player.px - 37.5 && Mx <= player.px + 62.5 && My > player.py + 67)
					ship = 3;
				else if(Mx < player.px - 37.5 && My <= player.py + 67 && My >= player.py - 33)
					ship = 4;
				
				else if(Mx > player.px + 62.5 && My < player.py - 33)
					ship = 5;
				else if(Mx > player.px + 62.5 && My > player.py + 67)
					ship = 6;
				else if(Mx < player.px - 37.5 && My > player.py + 67)
					ship = 7;
				else if(Mx < player.px - 37.5 && My < player.py - 33)
					ship = 8;
				
				break;
			default:
				System.out.println("gameState mouse moved wtf moment");
				break;
			}
			
		}
		
		public void mousePressed(MouseEvent e) {
			
		}
		
		public void mouseReleased(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			switch(gameState) {
			case "menu":
				if(mx > startrect.x && mx < startrect.x+startrect.width && my > startrect.y && my < startrect.y+startrect.height) {
					startGame();
					gameState = "game";
				}
				if(mx > options.x && mx < options.x+options.width && my > options.y && my < options.y+options.height)
					gameState = "options";
				break;
			case "options":
				if(mx > back.x && mx < back.x+back.width && my > back.y && my < back.y+back.height)
					gameState = "menu";
				if(mx > screensize.x && mx < screensize.x+screensize.width && my > screensize.y && my < screensize.y+screensize.height) {
					if(FWidth == 600 && FHeight == 600) {
						FWidth = 800; FHeight= 600;
					}else if(FWidth == 800 && FHeight == 600) {
						FWidth = 1024;
					}else if(FWidth == 1024 && FHeight == 600) {
						FWidth = 1280; FHeight = 800;
					}else if(FWidth == 1280 && FHeight == 800) {
						FWidth = 600; FHeight = 600;
					}else
						System.out.println("something went wrong in options with screensize switcher");
						updatescreen = true;
					}
					
				break;
			case "game":
				if(player.combat) {
					if(mx > attack.x && mx < attack.x+attack.width && my > attack.y && my < attack.y + attack.height) {
						player.attack = true;
					}
				}
				break;
				default:
					System.out.println("gameState mouse released wtf moooo ment");
			}
		}
		
	}

}
