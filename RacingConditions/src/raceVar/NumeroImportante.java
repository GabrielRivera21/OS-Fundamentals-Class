package raceVar;

import java.util.concurrent.Semaphore;

public class NumeroImportante {
	
	static Semaphore semaphore = new Semaphore(1); // 1 is green, 0 is red.
	static Semaphore semaphoreForConsumer = new Semaphore(0);
	static Semaphore semaphoreForProducer = new Semaphore(0, true);
	
	static int contadorImportante = 0;

}
