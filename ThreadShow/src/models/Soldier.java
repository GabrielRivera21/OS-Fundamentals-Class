package models;

import java.io.File;

/**
 * 
 * @author Gabriel
 *
 */
public class Soldier extends Model {
	
	/**
	 * File containing the Sound of the Soldier
	 */
	private File file = new File("sounds/yeah.wav");
	
	@Override
	public void run(){
		while(true){
			try{
				sleep(1500);
				int c = 0;
				while(c++ <= 30)
					update();
				playSound(file);
				sleep(1000);
			}catch(InterruptedException e){}
		}
	}
	
	/**
	 * Constructor for the Soldier holding his position and his imageURL
	 * @param x
	 * @param y
	 * @param imageURL
	 */
	public Soldier(int x, int y, String imageURL){
		super(x, y, imageURL);
	}
	
	/**
	 * Plays the Sound File of the Soldier
	 */
	public void soldierSound(){
		playSound(file);
	}
	
	/**
	 * Moves the Soldier's Position
	 */
	@Override
	public void update(){
		try {
			sleep(5);
			
			setY(getY() + 2 * getDirectionY());

			if( getY() > getHeight() - 250 || getY() <= 150 ) {
				setDirectionY(getDirectionY() > 0 ? -1 : 1);

				if( getY() > getHeight() )
					setY(getHeight());
				else 
					if( getY() < 0 )
						setY(0);

			} // if
		}catch(Exception e){}
		getWindow().setLocation(getX(), getY());
	}
	
}
