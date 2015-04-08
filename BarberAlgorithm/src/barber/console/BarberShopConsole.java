package barber.console;

import java.util.Vector;
import java.util.concurrent.Semaphore;

import barber.Client;

public class BarberShopConsole {
	
	//BarberShop Properties
	private static final int SEATS = 5;
	static int ClientNum;
	static Vector<Client> barberShopSeats = new Vector<>(SEATS);
	
	//Semaphores
	static Semaphore semaphoreForBarber = new Semaphore(0);
	static Semaphore semaphoreChairs = new Semaphore(SEATS); //Counting Semaphores.
	
	
	public static void main(String[] args) {
		new Barber("Gaby", 10000).start();
		new Barber("Pepito", 8000).start();
		new Barber("Jose", 9000).start();
		
		
		while(true){
			Client hairyClient = new Client(ClientNum);
			//System.out.println(hairyClient + " arrived");
			try{
				semaphoreChairs.acquire();
				ClientNum++;
				barberShopSeats.add(hairyClient);
				
				System.out.print("\nClients Waiting: ");
				for(Client hairy : barberShopSeats)
					System.out.print(hairy + " ");
				System.out.println("\n");
				
				semaphoreForBarber.release();
			}catch(InterruptedException e){}
			
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			
			if(ClientNum == 50)
				break;
			
		}
			
	}
}
