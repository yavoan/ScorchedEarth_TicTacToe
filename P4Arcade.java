import java.awt.EventQueue;
import javax.swing.JFrame;

public class P4Arcade {

	private Main_Menu frame; 

	//Launches the application 
	public static void main(String[] args) { 
		if (args.length>0){
			System.out.println("Error: No arguements are to be given.");
			System.exit(0);
		}
		EventQueue.invokeLater(new Runnable() { 
			public void run() { 
				try { 
					P4Arcade window = new P4Arcade(); 
					window.frame.setTitle("Arcade"); 
					window.frame.setVisible(true); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
			} 
		}); 
	} 

	//Constructor initializes the application 
	public P4Arcade() { 
		initialize(); 
	} 

	//Initializes frame contents 
	private void initialize() { 
		frame = new Main_Menu(); 
		frame.setSize(1000,700); 
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setResizable(false);
		frame.getContentPane().add(frame.getPane());
		frame.getPane().setLayout(null);
	} 

}


