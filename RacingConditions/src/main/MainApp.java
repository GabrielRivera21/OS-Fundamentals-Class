package main;

import raceVar.ConsumerThread;
import raceVar.ProducerThread;

public class MainApp {
	
	public static final long PRODUCER_SPEED = 200;
	public static final long CONSUMER_SPEED = 1000;

	public static void main(String[] args) {
		new ProducerThread().start();
		new ProducerThread().start();
		new ProducerThread().start();
		new ProducerThread().start();
		
		new ConsumerThread().start();
		new ConsumerThread().start();
	}
}
