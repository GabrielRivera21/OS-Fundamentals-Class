package barber.gui;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BarberGUI {

	static JLabel lblClientpic = new JLabel("");
	static JLabel lblClientpic_1 = new JLabel("");
	static JLabel lblClientpic_2 = new JLabel("");
	static JLabel lblClientpic_3 = new JLabel("");
	static JLabel lblClientpic_4 = new JLabel("");
	
	static String[] images = {"images/Circle.png", "images/x.png"};
	public static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					BarberGUI window = new BarberGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BarberGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Barber Shop Algorithm GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBarber = new JLabel("Gaby");
		lblBarber.setBounds(90, 44, 46, 14);
		frame.getContentPane().add(lblBarber);
		
		JLabel lblBarber_1 = new JLabel("Pepito");
		lblBarber_1.setBounds(181, 44, 46, 14);
		frame.getContentPane().add(lblBarber_1);
		
		JLabel lblBarber_2 = new JLabel("Jose");
		lblBarber_2.setBounds(268, 44, 46, 14);
		frame.getContentPane().add(lblBarber_2);
		
		
		JLabel lblChair = new JLabel("Chair");
		lblChair.setBounds(10, 209, 46, 14);
		frame.getContentPane().add(lblChair);
		
		JLabel lblChair_1 = new JLabel("Chair");
		lblChair_1.setBounds(90, 209, 46, 14);
		frame.getContentPane().add(lblChair_1);
		
		JLabel lblChair_2 = new JLabel("Chair");
		lblChair_2.setBounds(160, 209, 46, 14);
		frame.getContentPane().add(lblChair_2);
		
		JLabel lblChair_3 = new JLabel("Chair");
		lblChair_3.setBounds(240, 209, 46, 14);
		frame.getContentPane().add(lblChair_3);
		
		JLabel lblChair_4 = new JLabel("Chair");
		lblChair_4.setBounds(339, 209, 46, 14);
		frame.getContentPane().add(lblChair_4);
		
		
		lblClientpic.setBounds(10, 184, 46, 69);
		frame.getContentPane().add(lblClientpic);
		
		
		lblClientpic_1.setBounds(90, 184, 46, 69);
		frame.getContentPane().add(lblClientpic_1);
		
		
		lblClientpic_2.setBounds(160, 184, 46, 69);
		frame.getContentPane().add(lblClientpic_2);
		
		
		lblClientpic_3.setBounds(240, 184, 46, 69);
		frame.getContentPane().add(lblClientpic_3);
		
		
		lblClientpic_4.setBounds(339, 184, 46, 69);
		frame.getContentPane().add(lblClientpic_4);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BarberShop().start();
			}
		});
		btnStart.setBounds(64, 128, 155, 31);
		frame.getContentPane().add(btnStart);
		
		JButton btnNewButton = new JButton("Stop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(226, 128, 143, 31);
		frame.getContentPane().add(btnNewButton);
	}
}
