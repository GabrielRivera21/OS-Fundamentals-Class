package banker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import main.Main;

/**
 * 
 * @author Gabriel Rivera Per-ossenkopp
 * Description: This class executes the Banker Algorithm
 *
 */
public class Banker {
	
	private int[] resources;
	
	static double iterations = 0;
	static int rejected = 0;
	static double processEnded = 0;
	
	private File filename;
	
	public Banker(String fname) {
		filename = new File(fname);
		
		try {
			Scanner file = new Scanner(filename);
			resources = setup(file);
			run(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	/**
	 * Runs the main loop of the algorithm
	 * @param file
	 */
	private void run(Scanner file) {
		//init process
		int[] resourcesLeft = Arrays.copyOf(resources, resources.length);
		int[][] need = new int[4][6];
		int[][] allocated = new int[4][6];
		boolean[] done = new boolean[4];
		done = init_done(done);
		
		//Loop
		do{
			//fills all 4 CPUs
			need = fillProcessors(need, done, file);
			for(int cpu = 0; cpu < need.length; cpu++){
				for(int res = 0; res < need[0].length; res++){
					//Allocates the necessary resources
					if(resourcesLeft[res] >= need[cpu][res])
						allocated[cpu][res] = need[cpu][res] + resourcesLeft[res] - resourcesLeft[res];
					need[cpu][res] = need[cpu][res] - allocated[cpu][res];
					if(need[cpu][res] != 0){
						//If it didn't kill the process, then it gives it back.
						need[cpu][res] += allocated[cpu][res];
						resourcesLeft = freeResource(resourcesLeft, allocated, cpu, res);
						allocated = freeAllocated(allocated, cpu, res);
						break;
					}
					resourcesLeft[res] -= allocated[cpu][res]; 
				}
			}
			iterations++;
			record("--------------------");
			done = freeProcessEnded(need, done); //ends the processes that are done
			resourcesLeft = freeProcessEndedResources(resourcesLeft, allocated,  done); //free(resource)
			allocated = freeProcessEndedAllocated(allocated, done); //free(allocated)
			Main.window.pause();
		}while(file.hasNext() || processesLeft(done));
		
		record("--------------------");
		show_results();
		
		
	}
	
	/**
	 * Frees up the resources used of the processes that ended
	 * @param resourcesLeft2
	 * @param allocated2
	 * @param done
	 * @return
	 */
	private int[] freeProcessEndedResources(int[] resourcesLeft2, int[][] allocated2, boolean[] done) {
		for(int cpu = 0; cpu < allocated2.length; cpu++){
			if(done[cpu]){
				for(int res = 0; res < allocated2[0].length; res++){
					resourcesLeft2[res] += allocated2[cpu][res];
				}
			}
		}
		return resourcesLeft2;
	}
	
	/**
	 * erases the allocated resources from allocated
	 * @param allocated2
	 * @param done
	 * @return
	 */
	private int[][] freeProcessEndedAllocated(int[][] allocated2, boolean[] done) {
		for(int cpu = 0; cpu < allocated2.length; cpu++){
			if(done[cpu]){
				for(int res = 0; res < allocated2[0].length; res++){
					allocated2[cpu][res] = 0;
				}
			}
		}
		return allocated2;
	}
	
	/**
	 * Verifies if there are any processes in any of the 4 CPUs
	 * @param done
	 * @return
	 */
	private boolean processesLeft(boolean[] done) {
		for(int x = 0; x < done.length; x++){
			if(!done[x]){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Ends the processes that that have 0 in needs
	 * @param currNeed
	 * @param done
	 * @return
	 */
	private boolean[] freeProcessEnded(int[][] currNeed, boolean[] done) {
		for(int cpu = 0; cpu < currNeed.length; cpu++){
			if(processEnded(currNeed, cpu)){
				GUIWindow.refreshEndedState(cpu);
				if(!done[cpu]){
					done[cpu] = true;
					processEnded++;
				}
				done[cpu] = true;
			}
		}
		return done;
	}
	
	/**
	 * Verifies if the processes has ended by checking if it needs any more resources
	 * @param currNeed
	 * @param cpu
	 * @return
	 */
	private boolean processEnded(int[][] currNeed, int cpu) {
		for(int res = 0; res < currNeed[0].length; res++){
			if(currNeed[cpu][res] > 0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Resets the allocated processes from the CPU to 0
	 * @param allocated2
	 * @param cpu
	 * @param res
	 * @return
	 */
	private int[][] freeAllocated(int[][] allocated2, int cpu, int res) {
		for(int x = res; x >= 0; x--){
			allocated2[cpu][x] = 0;
		}
		return allocated2;
	}
	
	/**
	 * frees up the resources used in one CPU
	 * @param resLeft
	 * @param allo
	 * @param cpu
	 * @param res
	 * @return
	 */
	private int[] freeResource(int[] resLeft, int[][] allo, int cpu, int res) {
		for(int x = res; x >= 0; x--){
			resLeft[x] += allo[cpu][x];
		}
		return resLeft;
	}
	
	/**
	 * Initializes the array done to true in order to say that all 4 CPUs are free
	 * @param done
	 * @return
	 */
	private boolean[] init_done(boolean[] done) {
		for(int x = 0; x < done.length; x++){
			done[x] = true;
		}
		return done;
	}
	
	/**
	 * Fills all CPUs with a process and refreshes the GUI
	 * @param currNeed
	 * @param done
	 * @param file
	 * @return
	 */
	private int[][] fillProcessors(int[][] currNeed, boolean[] done, Scanner file) {
		int[][] tmpNeed = currNeed;
		for(int cpu = 0; cpu < tmpNeed.length; cpu++){
			if(done[cpu]){
				try{
					String process = file.nextLine();
					String[] pResource = process.split(",");
					if(hasEnoughResources(pResource)){
						tmpNeed = processEnter(process, pResource, tmpNeed, cpu);
						done[cpu] = false;
						GUIWindow.refreshEnteredState(tmpNeed, pResource, cpu);
						Main.window.pause();
					}else{
						rejectProcess(process);
						GUIWindow.refreshRejectedState(cpu, pResource);
						cpu--;
						Main.window.pause();
					}
				}catch(NoSuchElementException e){}
			}
		}
		return tmpNeed;
	}
	
	/**
	 * Writes on the output text file that the process was rejected
	 * and increments rejected count.
	 * @param process
	 */
	private void rejectProcess(String process) {
		record(process + " rejected");
		rejected++;
	}
	
	/**
	 * Writes on the output text file that the process has entered
	 * in one of the CPU's
	 * @param process
	 */
	private int[][] processEnter(String process, String[] pResource, int[][] tmpNeed, int cpu) {
		Main.write(process + " accepted");
		for(int x = 0; x < tmpNeed[0].length; x++){
			tmpNeed[cpu][x] = Integer.parseInt(pResource[x + 1]);
		}
		return tmpNeed;
	}
	
	/**
	 * Verifies if the system has enough resources to take in the process.
	 * @param pResource
	 * @return
	 */
	private boolean hasEnoughResources(String[] pResource){
		for(int x = 1; x < pResource.length; x++){
			if(Integer.parseInt(pResource[x]) > resources[x - 1]){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Sets up the resources from the data file to the GUI
	 * @param file
	 * @return
	 */
	private int[] setup(Scanner file){
		int[] tmpResources = new int[6];
		String strResources = file.nextLine();
		init_record(strResources);
		String[] extractedRes = strResources.split(",");
		for(int x = 0;  x < tmpResources.length; x++){
			tmpResources[x] = Integer.parseInt(extractedRes[x]);
			GUIWindow.setTextForResource(tmpResources[x], x + 1);
		}
		return tmpResources;
	}
	
	/**
	 * Starts writing on the output text file the Resources
	 * @param line
	 */
	private void init_record(String line){
		record("Simulacion del Algoritmo del Banquero");
		record("Resources");
		record(line);
		record("");
	}
	
	/**
	 * Writes on the output text File.
	 * @param line
	 */
	private void record(String line){
		Main.write(line);
	}
	
	/**
	 * Writes the results in the output text file and
	 * shows it on the GUI.
	 */
	private void show_results(){
		record("The Performance is " + 
			 String.format("%.2f", (processEnded/iterations * 100)) + "%");
		record("The number of process ended is " + processEnded);
		record("The number of iterations is " + iterations);
		record("The number of process rejected is " + rejected);
		GUIWindow.writeStop();
	}
}
