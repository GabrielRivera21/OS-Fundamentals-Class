package barber.gui;


import java.util.Vector;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import barber.Client;


public class BarberShop extends Thread {

	//BarberShop Properties
	private static final int SEATS = 5;
	static int ClientNum;
	static Vector<Client> barberShopSeats = new Vector<>(SEATS);
	
	//Semaphores
	static Semaphore semaphoreForBarber = new Semaphore(0);
	static Semaphore semaphoreChairs = new Semaphore(SEATS); //Counting Semaphores.
	
	//Chairs
	static Vector<JLabel> pics = new Vector<>(SEATS);
	
	@Override
	public void run() {
		new Barber("Gaby", 1000, 69, 69, 99, 52).start();
		new Barber("Pepito", 1000, 181, 69, 46, 52).start();
		new Barber("Jose", 1000, 268, 69, 46, 52).start();
		pics.add(BarberGUI.lblClientpic);
		pics.add(BarberGUI.lblClientpic_1);
		pics.add(BarberGUI.lblClientpic_2);
		pics.add(BarberGUI.lblClientpic_3);
		pics.add(BarberGUI.lblClientpic_4);
		ImageIcon image = new ImageIcon(BarberGUI.images[0]);
		for(JLabel lbl : pics)
			lbl.setIcon(image);
		
		while(true){
			Client hairyClient = new Client(ClientNum);
			System.out.println(hairyClient + " arrived");
			try{
				semaphoreChairs.acquire();
				ClientNum++;
				barberShopSeats.add(hairyClient);
				image = new ImageIcon(BarberGUI.images[1]);;
				
				System.out.print("\nClients Waiting: ");
				for(int hairy = 0; hairy < barberShopSeats.size(); hairy++){
					System.out.print(barberShopSeats.get(hairy) + " ");
					pics.get(hairy).setIcon(image);
				}
				System.out.println("\n");
				
				semaphoreForBarber.release();
			}catch(InterruptedException e){}
			
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			
			
		}
			
	}
}
