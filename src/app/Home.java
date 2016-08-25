package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Home extends JFrame {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		
		  String[] columns = new String[] {
		            "Id", "Name", "Hourly Rate", "Part Time"
		        };
		         
		        //actual data for the table in a 2d array
		        Object[][] data = new Object[][] {
		            {1, "John", 40.0, false },
		            {2, "Rambo", 70.0, false },
		            {3, "Zorro", 60.0, true },
		        };
		 
		        //create table with data
		       JTable table = new JTable(data, columns);
		         
		        //add the table to the frame
		       getContentPane().add(new JScrollPane(table));
		         
		        this.setTitle("Table Example");
		        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
		       		
	
		
	}
}
