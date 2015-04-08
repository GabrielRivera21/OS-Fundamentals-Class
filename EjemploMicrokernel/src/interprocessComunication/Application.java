package interprocessComunication;

import java.io.IOException;
import java.util.*;

/**
 * 
 * @author Gabriel
 *
 */
public class Application {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		Runtime rt = Runtime.getRuntime();
		
		System.out.println(rt);
		System.out.println();
		
		System.out.println("Escriba un comando/programa ahora: ");
		String comandoString = in.nextLine();
		System.out.println(comandoString);
		
		try {
			Process p = rt.exec(comandoString);
			System.out.printf("%s\n", p);
			Thread.sleep(5000);
			p.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		in.close();
	}
}
