package banker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

/**
 * 
 * @author Gabriel Rivera Per-ossenkopp
 * Description: Shows the current processes of the Banker Algorithm
 * in a GUI Window.
 *
 */
@SuppressWarnings("serial")
public class GUIWindow extends JFrame {
	
	private static JLabel[] resLabels;
	private static JLabel[][] cpus;
	
	public GUIWindow(){
		setTitle("Banker Algorithm version 2.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		resLabels = new JLabel[7];
		cpus = new JLabel[4][7];
		
		resLabels[0] = new JLabel("Resources");
		for(int x = 1; x < resLabels.length; x++){
			resLabels[x] = new JLabel("0");
		}
		
		for(int cpu = 0; cpu < cpus.length; cpu++){
			for(int res = 0; res < cpus[0].length; res++){
				cpus[cpu][res] = new JLabel("0");
			}
		}
		
		JPanel panel = new JPanel(new GridLayout(5, 7));
		
		for(int x = 0; x < resLabels.length; x++){
			panel.add(resLabels[x]);
		}
		
		for(int cpu = 0; cpu < cpus.length; cpu++){
			for(int res = 0; res < cpus[0].length; res++){
				panel.add(cpus[cpu][res]);
			}
		}
		
		JButton button = new JButton("Continue");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				advance();
			}
			
		});
		
		add(panel);
		add(button, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Sets the text on the labels for resources.
	 * @param res
	 * @param idx
	 */
	public static void setTextForResource(int res, int idx){
		resLabels[idx].setText(res + "");
	}
	
	/**
	 * Shows the processes that have ended on a CPU.
	 * @param cpu
	 */
	public static void refreshEndedState(int cpu){
		if(!cpus[cpu][0].getText().equals("0") && !cpus[cpu][2].getText().equals("Rejected"))
			record(cpus[cpu][0].getText() + " process ended.");
		cpus[cpu][0].setText("0");
		for(int res = 1; res < cpus[0].length; res++){
			cpus[cpu][res].setText("0");
			cpus[cpu][res].setForeground(Color.GREEN);
		}	
	}
	
	/**
	 * Shows the processes that have entered to the CPU
	 * @param tmpNeed
	 * @param pResource
	 * @param cpu
	 */
	public static void refreshEnteredState(int[][] tmpNeed, String[] pResource, int cpu){
		cpus[cpu][0].setText(pResource[0]);
		for(int res = 1; res < cpus[0].length; res++){
			cpus[cpu][res].setText(tmpNeed[cpu][res - 1] + "");
			cpus[cpu][res].setForeground(Color.BLUE);
		}
	}
	
	/**
	 * Shows the Processes that have been rejected.
	 * @param cpu
	 * @param pResource
	 */
	public static void refreshRejectedState(int cpu, String[] pResource) {
		cpus[cpu][0].setText(pResource[0]);
		cpus[cpu][1].setText("was");
		cpus[cpu][2].setText("Rejected");
		for(int res = 1; res < cpus[0].length; res++){
			cpus[cpu][res].setForeground(Color.RED);
		}
	}
	
	/**
	 * Pauses the program
	 */
	public synchronized void pause() {
		try {
			wait();
		} catch (InterruptedException e) {}
	}
	
	/**
	 * Resumes the program
	 */
	public synchronized void advance(){
		notify();
	}

	
	/**
	 * Writes out on the GUI the results
	 */
	public static void writeStop() {
		
		cpus[0][0].setText("The");
		cpus[0][1].setText("Perfomance");
		cpus[0][2].setText("is");
		cpus[0][3].setText(String.format("%.2f", (Banker.processEnded/Banker.iterations * 100)) + "%");
		
		cpus[1][0].setText("The");
		cpus[1][1].setText("Total");
		cpus[1][2].setText("# of");
		cpus[1][3].setText("Process");
		cpus[1][4].setText("Killed");
		cpus[1][5].setText("is");
		cpus[1][6].setText(Banker.processEnded + "");
		
		cpus[2][0].setText("The");
		cpus[2][1].setText("Total");
		cpus[2][2].setText("# of");
		cpus[2][3].setText("Process");
		cpus[2][4].setText("Iterations");
		cpus[2][5].setText("is");
		cpus[2][6].setText(Banker.iterations + "");
		
		cpus[3][0].setText("The");
		cpus[3][1].setText("Total");
		cpus[3][2].setText("# of");
		cpus[3][3].setText("Process");
		cpus[3][4].setText("Rejected");
		cpus[3][5].setText("is");
		cpus[3][6].setText(Banker.rejected + "");
	}
	
	private static void record(String line){
		Main.write(line);
	}
}
