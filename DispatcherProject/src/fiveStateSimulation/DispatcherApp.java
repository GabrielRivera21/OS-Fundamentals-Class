package fiveStateSimulation;

/**
 * 
 * @author Gabriel
 *
 */
public class DispatcherApp {
	
	public DispatcherApp(){}
	
	public static void main(String[] args) {
		//IDs
		int processID = 1111, parentProcessID = 0, userID = 841116930;
		
		ProcessControlBlock chrome = new ProcessControlBlock(processID, parentProcessID, userID);
		ProcessControlBlock steam = new ProcessControlBlock(2222, 0, userID);
		
		System.out.println(chrome);
		System.out.println(steam);
		try {
			chrome.admit(5000);
			steam.admit(8000);
			System.out.println(chrome);
			System.out.println(steam);
			System.out.println();
			int x =0;
			while(x < 5){
				chrome.dispatch();
				System.out.println(chrome);
				chrome.timeOut();
				System.out.println(chrome + "\n");
				
				
				steam.dispatch();
				System.out.println(steam);
				steam.timeOut();
				System.out.println(steam + "\n");
				
				Thread.sleep(1500);
				x++;
			}
			chrome.dispatch();
			chrome.release();
			steam.dispatch();
			steam.release();
			
			System.out.println(chrome);
			System.out.println(steam);
			System.out.println("Done!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
