package raceVar;

import main.MainApp;

public class ConsumerThread extends Thread {
	
	@Override
	public void run() {
		super.run();
		
		while(true){
			try {
				NumeroImportante.semaphoreForConsumer.acquire();
				System.out.println("Consumer " + this.hashCode() + ": "+ NumeroImportante.contadorImportante);
				NumeroImportante.semaphoreForProducer.release();
			} catch (InterruptedException e1) {}
			
			
			
			try {
				sleep(MainApp.CONSUMER_SPEED);
			} catch (InterruptedException e) {}
		}
		
		
	}

}
