package sevenStateSimulation;

/**
 * 
 * @author Gabriel
 *
 */
public class DispatcherApp {
	
	public DispatcherApp() {}
	
	public static void main(String[] args) throws Exception {
		//IDs
		int processID = 1111, parentProcessID = 0, userID = 841116930;
		
		ProcessControlBlock firefox = new ProcessControlBlock(processID, parentProcessID, userID);
		ProcessControlBlock powerPoint = new ProcessControlBlock(2222, 0, userID);
		try{
		firefox.activate();
		}catch(Exception e){
			System.out.println("Error Activate firefox");
		}
		
		firefox.admitReadySuspend(8000);

		System.out.println( firefox );

		firefox.activate();

		System.out.println( firefox );

		firefox.dispatch();

		System.out.println( firefox );

		firefox.timeOut();

		System.out.println( firefox );

		powerPoint.admit(5000);

		System.out.println( powerPoint );

		powerPoint.dispatch();

		System.out.println( powerPoint );

		powerPoint.timeOut();

		System.out.println( powerPoint );

		firefox.dispatch();

		System.out.println( firefox );

		firefox.eventWait();

		System.out.println( firefox );

		firefox.suspend();

		System.out.println( firefox );

		firefox.eventOccurs();

		System.out.println( firefox );

		powerPoint.dispatch();

		System.out.println( powerPoint );

		powerPoint.timeOut();

		System.out.println( powerPoint );
		
		//added
		firefox.activate();
		
		System.out.println(firefox);
		
		firefox.dispatch();

		System.out.println( firefox );

		firefox.suspend();

		System.out.println( firefox );

		firefox.activate();

		System.out.println( firefox );
		
		//added
		firefox.dispatch();
		
		System.out.println(firefox);
		
		firefox.release();

		System.out.println( firefox );

		powerPoint.kill();
		
		powerPoint.suspend();

		System.out.println( powerPoint );




		
			
		
		
	}
}
