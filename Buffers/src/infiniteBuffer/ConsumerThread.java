package infiniteBuffer;

public class ConsumerThread extends Thread {
	

	@Override
	public void run() {
		
		while(true){
			try {
				Global.semaphoreConsumer.acquire();
				Double num = Global.infiniteBuffer.get(Global.out++);
				System.out.println("Consumer " + this.hashCode() + ": " + num);
			} catch (InterruptedException e1) {}
			
			try {
				sleep(Global.CONSUMERSPEED);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
