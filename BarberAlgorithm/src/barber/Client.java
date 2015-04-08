package barber;


public class Client {
	
	private int clientID;
	
	public Client(int clientNum){
		this.clientID = clientNum;
	}
	
	public String toString(){
		return "Customer #" + clientID;
	}
	
	
}
