package barber.console;

import barber.Client;


public class Barber extends Thread {

	private String name;
	private int speed;
	
	public Barber(String name, int speed) {
		this.name = name;
		this.speed = speed;
	}
	
	@Override
	public void run() {
		
		while(true){
			
			try {
				BarberShopConsole.semaphoreForBarber.acquire();
				Client hairy = BarberShopConsole.barberShopSeats.remove(0);
				System.err.println("Barber: " +  name + " is cutting " + hairy);
				BarberShopConsole.semaphoreChairs.release();
			} catch (InterruptedException e1) {}
			
			
			
			try{
				sleep(speed);
			}catch(InterruptedException e){}
			
			
			
		}
	}

}
