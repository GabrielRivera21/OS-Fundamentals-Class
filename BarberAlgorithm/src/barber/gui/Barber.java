package barber.gui;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

import barber.Client;

public class Barber extends Thread {

	private String name;
	private int speed;
	private JLabel lblPic;
	int x, y, w, h;
	private int lblInt;
	
	public Barber(String name, int speed, int x, int y, int w, int h) {
		this.name = name;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		lblPic = new JLabel("");
		lblPic.setBounds(x, y, w, h);
		
		BarberGUI.frame.getContentPane().add(lblPic);
	}
	
	@Override
	public void run() {
		lblInt = 0;
		ImageIcon image = new ImageIcon(BarberGUI.images[lblInt]);
		lblPic.setIcon(image);
		while(true){
			
			try {
				BarberShop.semaphoreForBarber.acquire();
				
				//Changing the Image of one of the chairs to unoccupied
				image = new ImageIcon(BarberGUI.images[0]);
				BarberShop.pics.get(BarberShop.barberShopSeats.size() - 1).setIcon(image);
				
				Client hairy = BarberShop.barberShopSeats.remove(0);
				System.err.println("Barber: " +  name + " is cutting " + hairy);
				
				//changing the Image of one of the barbers to occupied
				lblInt = 1;
				image = new ImageIcon(BarberGUI.images[lblInt]);
				lblPic.setIcon(image);
				
				BarberShop.semaphoreChairs.release();
			} catch (InterruptedException e1) {}
			
			
			
			try{
				sleep(speed);
			}catch(InterruptedException e){}
			
			//Changing the image of one of the barbers to unoccupied
			lblInt = 0;
			image = new ImageIcon(BarberGUI.images[lblInt]);
			lblPic.setIcon(image);
			
			try{
				System.err.println(name + " taking 3 sec break.");
				sleep(3000);
			}catch(InterruptedException e){}
			
		}
	}

}
