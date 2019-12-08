import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class others {

	public static String type;
	Rectangle athers;
	Image other;
	boolean inorbit = false;
	boolean alive = true;
	int health;
	int speed = 1;
	Random rand = new Random();
	
	public others(int x, int y, String typ) {
		
		type = typ;
		
		if(type.equals("asteriod")) {
			athers = new Rectangle(x, y, 5, 3);
			switch(rand.nextInt(1)) {
			case 1:
				other = Main.asteriod1;
				break;
				default:
					other = Main.asteriod1;
					break;
			}
		}
		
	}
	
	public void gravity() {
		
		if(!inorbit) {
			if(athers.x < player.playr.x + player.size/2 - athers.width/2) {
				athers.x++;
			}
			if(athers.y < player.playr.y + player.size/2 - athers.height/2) {
				athers.y++;
			}
			if(athers.x > player.playr.x + player.size/2 - athers.width/2) {
				athers.x--;
			}
			if(athers.y > player.playr.y + player.size/2 - athers.height/2) {
				athers.y--;
			}
		}
		
	}
	
}
