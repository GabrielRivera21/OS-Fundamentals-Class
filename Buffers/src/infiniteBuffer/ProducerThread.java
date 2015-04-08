package infiniteBuffer;

public class ProducerThread extends Thread {
	
	private double counter;

	public ProducerThread(double i) {
		this.counter = i;
	}
	
	@Override
	public void run(){
		while(true){
			Global.infiniteBuffer.add(counter++);

			for(Double num : Global.infiniteBuffer)
				System.out.print(num + " ");
			System.out.println("");

			Global.semaphoreConsumer.release();
			try {
				sleep(Global.PRODUCERSPEED);
			} catch (InterruptedException e) {}
			
		}
		
		
	}
}
