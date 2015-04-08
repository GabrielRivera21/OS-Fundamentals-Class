package infiniteBuffer;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Global {
	
	public static final long CONSUMERSPEED = 1000;
	public static final long PRODUCERSPEED = 1000;
	static ArrayList<Double> infiniteBuffer = new ArrayList<Double>();
	public static int out = 0;
	static Semaphore semaphoreConsumer = new Semaphore(0);
	static Semaphore semaphoreProducer = new Semaphore(1);
	
	public static void main(String[] args) {
		new ConsumerThread().start();
		new ProducerThread(0).start();
	}
}
