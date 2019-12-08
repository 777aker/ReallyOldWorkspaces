import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {
	
	public static Snake snake;
	
	public JFrame jframe;
	
	public RenderPanel renderPanel;
	
	public Timer timer = new Timer(20, this);
	
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	public ArrayList<Point> snake2Parts = new ArrayList<Point>();
	
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	
	public int ticks = 0, direction = DOWN, direction2 = DOWN, score, tailLength = 10, tailLength2 = 10, time;
	
	public Random random;
	
	public Point head, head2, cherry;
	
	public boolean over = false, paused;
	
	public Dimension dim;
	
	public static int loser;
	
	public Snake() {
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Copy of Snake 1.0");
		jframe.setVisible(true);
		jframe.setSize(1500, 850);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.add(renderPanel = new RenderPanel());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		startGame();
		
	}
	
	public void startGame() {
		
		over =  false;
		paused = false;
		time = 0;
		score = 0;
		tailLength = 10;
		tailLength2 = 10;
		ticks = 0;
		direction = DOWN;
		direction2 = DOWN;
		head = new Point(0, -1);
		head2 = new Point(30, -1);
		random = new Random();
		snakeParts.clear();
		snake2Parts.clear();
		cherry = new Point(random.nextInt(149), random.nextInt(84));
		timer.start();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		renderPanel.repaint();
		ticks++;
		
		if(ticks % 3 == 0 && head != null && !over && !paused) {
			
			time++;
			
			snakeParts.add(new Point(head.x, head.y));
			snake2Parts.add(new Point(head2.x, head2.y));
			
			if(direction == UP) {
				
				if(head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
					
					head = new Point(head.x, head.y - 1);
					
				} else {
					
					over = true;
					
				}
				
			}
			
			if(direction == DOWN) {
				
				if(head.y + 1 < 82 && noTailAt(head.x, head.y + 1)) {
					
					head = new Point(head.x, head.y + 1);
					
				} else {
					
					over = true;
					
				}
				
			}
			
			if(direction == LEFT) {
				
				if(head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
					
					head = new Point(head.x -  1, head.y);
					
				} else {
					
					over = true;
					
				}
				
			}
			
			if(direction == RIGHT) {
				
				if(head.x + 1 < 150 && noTailAt(head.x + 1, head.y)) {
					
					head = new Point(head.x + 1, head.y);
					
				} else {
					
					over = true;
					
				}
				
			}
			
			if(direction2 == UP) {
				
				if(head2.y - 1 >= 0 && noTailAt(head2.x, head2.y - 1)) {
					
					head2 = new Point(head2.x, head2.y - 1);
					
				} else {
					
					over = true;
					
				}
				
			}
			
			if(direction2 == DOWN) {
				
				if(head2.y + 1 < 82 && noTailAt(head2.x, head2.y + 1)) {
					
					head2 = new Point(head2.x, head2.y + 1);
					
				} else {
					
					over = true;
					
				}
				
			}
			
			if(direction2 == LEFT) {
				
				if(head2.x - 1 >= 0 && noTailAt(head2.x - 1, head2.y)) {
					
					head2 = new Point(head2.x -  1, head2.y);
					
				} else {
					
					over = true;
					
				}
				
			}
			
			if(direction2 == RIGHT) {
				
				if(head2.x + 1 < 150 && noTailAt(head2.x + 1, head2.y)) {
					
					head2 = new Point(head2.x + 1, head2.y);
					
				} else {
					
					over = true;
					
				}
				
			}
			
			if(snakeParts.size() > tailLength) {
				
				snakeParts.remove(0);
				
			}
			
			if(snake2Parts.size() > tailLength2) {
				
				snake2Parts.remove(0);
				
			}
			
			if(cherry != null) {
				
				if(head.equals(cherry)) {
					
					score += 10;
					tailLength += 10;
					cherry.setLocation(random.nextInt(79), random.nextInt(66));
					
				}
				
				if(head2.equals(cherry)) {
					
					score += 10;
					tailLength2 += 10;
					cherry.setLocation(random.nextInt(79), random.nextInt(66));
					
				}
				
			}
			
		}
		
	}
	
	public boolean noTailAt(int x, int y) {
		
		for(Point point : snakeParts) {
			
			if(point.equals(new Point(x, y))) {
				loser=1;
				return false;
			}
			
		}
		
		for(Point point : snake2Parts) {
			
			if(point.equals(new Point(x, y))) {
				loser=2;
				return false;
			}
			
		}
		
		return true;
		
	}
	
	public static void main(String[] args) {
		
		snake = new Snake();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int i = e.getKeyCode();
		
		if((i == KeyEvent.VK_LEFT || i == KeyEvent.VK_LEFT) && direction != RIGHT) {
			direction = LEFT;
		}
		if ((i == KeyEvent.VK_RIGHT || i == KeyEvent.VK_RIGHT) && direction != LEFT) {
			direction = RIGHT;
		}

		if ((i == KeyEvent.VK_UP || i == KeyEvent.VK_UP) && direction != DOWN) {
			direction = UP;
		}

		if ((i == KeyEvent.VK_DOWN || i == KeyEvent.VK_DOWN) && direction != UP) {
			direction = DOWN;
		}
		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_A) && direction2 != RIGHT) {
			direction2 = LEFT;
		}
		if((i == KeyEvent.VK_D || i == KeyEvent.VK_D) && direction2 != LEFT) {
			direction2 = RIGHT;
		}
		if((i == KeyEvent.VK_W || i == KeyEvent.VK_W) && direction2 != DOWN) {
			direction2 = UP;
		}
		if((i == KeyEvent.VK_S || i == KeyEvent.VK_S) && direction2 != UP) {
			direction2 = DOWN;
		}
		
		if(i == KeyEvent.VK_SPACE) {
			if(over) {
				startGame();
			} else {
				paused = !paused;
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
