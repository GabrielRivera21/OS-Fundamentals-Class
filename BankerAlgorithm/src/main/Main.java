package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import banker.Banker;
import banker.GUIWindow;

/**
 * 
 * @author Gabriel Rivera Per-ossenkopp
 * Description: starts the Main APP for the Banker Algorithm.
 */
public class Main {

	private static PrintWriter writer;
	public static GUIWindow window;
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("output.txt");
		
		writer = new PrintWriter(file);
		
		window = new GUIWindow();
		
		new Banker("data.csv");
		
		writer.close();

	}
	
	/**
	 * Writes to the output text file.
	 * @param line
	 */
	public static void write(String line){
		writer.println(line);
	}

}
