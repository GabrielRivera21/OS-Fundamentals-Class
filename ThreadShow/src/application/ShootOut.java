package application;

import models.Bullet;
import models.Duck;
import models.Soldier;

/**
 * 
 * @author Gabriel
 *
 */
public class ShootOut {
	
	/**
	 * Starts the main application	
	 * @param args
	 */
	public static void main(String[] args) {
		int soldierXPos, soldierYPos, DuckXPos;
		
		new Soldier(soldierXPos = 750, soldierYPos = 300, "images/sniper.png").start();
		new Duck(DuckXPos = 250, 300, "images/duck.png").start();
		new Bullet(soldierXPos - 110,soldierYPos, DuckXPos, "images/bullet.png").start();
		
		new Soldier(soldierXPos = 750, soldierYPos = 400, "images/sniper.png").start();
		new Duck(DuckXPos = 250, soldierYPos, "images/duck.png").start();
		new Bullet(soldierXPos - 110,soldierYPos, DuckXPos, "images/bullet.png").start();
		
		new Soldier(soldierXPos = 750, soldierYPos = 200, "images/sniper.png").start();
		new Duck(DuckXPos = 250, soldierYPos , "images/duck.png").start();
		new Bullet(soldierXPos - 110, soldierYPos, DuckXPos, "images/bullet.png").start();
		
		int x = 0;
		while(true){
			try{
				Thread.sleep(1500);
				if (x++ == 35)
					System.exit(0); //terminates the ShootOut Application
			}catch(InterruptedException e){}
		}
	}
}
