package raceVar;

import main.MainApp;

public class ProducerThread extends Thread {
	
	public void run(){
		super.run();
		
		int p = this.hashCode();
		
		while(true){
			
			//critical Section
			try {
				//Si lo encuentra verde, cuando pase lo pone rojo.
				NumeroImportante.semaphore.acquire();
				System.out.println(p + " " + ++NumeroImportante.contadorImportante);
				
				NumeroImportante.semaphoreForConsumer.release();
				NumeroImportante.semaphoreForProducer.acquire();
				
				NumeroImportante.semaphore.release();
			} catch (InterruptedException e1) { e1.printStackTrace();}
			
			
			try {
				sleep(MainApp.PRODUCER_SPEED);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
