package primerEjemplo;

public class PrimerEjemploApp {
	
	public static void main(String[] args) {
		int i = 1;
		
		
		while(true){
			System.out.println(i++ + " !Mi Thread!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
