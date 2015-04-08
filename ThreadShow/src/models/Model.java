package models;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * 
 * @author Gabriel
 *
 */
public abstract class Model extends Thread {
	
	/**
	 * Properties of every Model
	 */
	private int x, y;
	private int directionX = -1, directionY = 1;
	private JWindow window = new JWindow();
	private int width = Toolkit.getDefaultToolkit().getScreenSize().width 
						- getWindow().getSize().width;
	private int height = Toolkit.getDefaultToolkit().getScreenSize().height 
						- getWindow().getSize().height;
	
	/**
	 * Constructor for Models for their Windows, Images and Positions
	 * @param x
	 * @param y
	 * @param imageURL
	 */
	public Model(int x, int y, String imageURL){
		window.add(new JLabel(new ImageIcon(imageURL)));

		window.setBackground( new Color(0,0,0,0));

		window.pack();

		window.setLocation(this.x = x, this.y = y);

		window.setVisible(true);
	}
	
	/**
	 * Returns the X value of Model
	 * @return X position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the X location of Model
	 * @param int X position : int
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the Y value of Model
	 * @return Y position 
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the Y location of Model
	 * @param Y position : int
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * a 1 or -1 to give direction of the screen
	 * @return 1 or -1
	 */
	public int getDirectionX() {
		return directionX;
	}

	/**
	 * Sets the direction in X with 1 or -1 allowed only.
	 * @param directionX : int
	 */
	public void setDirectionX(int directionX) {
		if(directionX == 1 || directionX == -1)
			this.directionX = directionX;
	}

	/**
	 * a 1 or -1 to give direction of the screen
	 * @return 1 or -1
	 */
	public int getDirectionY() {
		return directionY;
	}

	/**
	 * Sets the direction in Y with 1 or -1 allowed only.
	 * @param directionY : int
	 */
	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}
	
	/**
	 * the JWindow holding the Object
	 * @return window
	 */
	public JWindow getWindow(){
		return window;
	}
	
	/**
	 * Returns the Width of the Window holding the object
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the Height of the Window holding the object
	 * @return height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Plays the sound of the given file
	 * @param file : File
	 */
	protected synchronized void playSound(final File file) {
		new Thread(new Runnable() {
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
					clip.open(inputStream);
					clip.start(); 
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
	
	/**
	 * To be used by specific models to update themselves.
	 */
	public abstract void update();
}
