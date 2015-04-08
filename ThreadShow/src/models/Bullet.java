package models;

import java.io.File;

/**
 * 
 * @author Gabriel
 *
 */
public class Bullet extends Model{
	
	/**
	 * File containing the Sound of the Bullet
	 */
	private File file = new File("sounds/BulletSound.wav");
	
	/**
	 * the maxDistance ran by the bullet and it's start positions in X
	 */
	private int maxDist, startPosX;
	
	public void run(){
		while(true){
			try {
				sleep(3);
				update();
			} catch (InterruptedException e) {}
		}
	}
	
	/**
	 * Constructor of the bullet containing it's position, max distance to be ran
	 * and the image of the Bullet
	 * @param x
	 * @param y
	 * @param maxDist
	 * @param imageURL
	 */
	public Bullet(int x, int y, int maxDist, String imageURL){
		super(x, y, imageURL);
		this.maxDist = maxDist;
		this.startPosX = x;
		shootSound();
	}
	
	/**
	 * updates the position of the Bullet
	 */
	@Override
	public void update(){

		try{

			setX(getX() + 2 * -1);

			if( getX() >= getWidth()|| getX() <= maxDist ) {
				
				if( getX() > getWidth() )
					setX(getWidth());
				else{
					if(getX() < maxDist){
						getWindow().setVisible(false);
						sleep(1000);
						setX(startPosX);
						int c = 0;
						while(c++ <= 30)
							updateYPos();
						sleep(1000);
						getWindow().setVisible(true);
						shootSound();
					}
				}
			}
		}catch(Exception e){}

		getWindow().setLocation(getX(), getY());
	}
	
	/**
	 * Updates the bullet's Y position
	 */
	private void updateYPos() {
		setY(getY() + 2 * getDirectionY());

		if( getY() > getHeight() - 250 || getY() <= 150 ) {
			setDirectionY(getDirectionY() > 0 ? -1 : 1);

			if( getY() > getHeight() )
				setY(getHeight());
			else 
				if( getY() < 0 )
					setY(0);
		}
	}
	
	/**
	 * Plays the Bullet's sound File
	 */
	public void shootSound(){
		playSound(file);
	}
	
}



