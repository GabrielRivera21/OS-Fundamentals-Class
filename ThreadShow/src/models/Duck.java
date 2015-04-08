package models;

import java.io.File;

/**
 * 
 * @author Gabriel
 *
 */
public class Duck extends Model{
	
	/**
	 * File containing the Sound of the Duck
	 */
	private File file = new File("sounds/DuckSound.wav");
	
	@Override
	public void run(){
		while(true){
			try {
				sleep(1500);
				duckSound();
				int c = 0;
				while(c++ <= 30)
					update();
				sleep(1000);
			} catch (InterruptedException e) {}
			
		}
		
	}
	
	/**
	 * Constructor for the Duck holding his position and his imageURL
	 * @param x
	 * @param y
	 * @param imageURL
	 */
	public Duck(int x, int y, String imageURL){
		super(x, y, imageURL);
	}
	
	/**
	 * Updates the position of the Duck
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
	
	/**
	 * Plays the sound of the File
	 */
	public void duckSound(){
		playSound(file);
	}
}
