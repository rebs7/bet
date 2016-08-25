package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import main.entities.Equipas;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Teste extends JFrame {

	private JPanel contentPane;
	 private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste frame = new Teste();
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
	public Teste() {

		
		        
		     
		         getContentPane().setLayout(null);
		         DefaultTableModel model = new DefaultTableModel(getTableData(), getTableHeaders());
		        table = new JTable(model );
		    
		        JScrollPane scrollPane = new JScrollPane(table);
		        scrollPane.setBounds(10, 278, 1564, 574);
		        getContentPane().add(scrollPane);
		        
		        Action delete = new AbstractAction()
		        {
		            public void actionPerformed(ActionEvent e)
		            {
		                JTable table = (JTable)e.getSource();
		                int modelRow = Integer.valueOf( e.getActionCommand() );
		                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
		            }
		        };
		         
		        ButtonColumn buttonColumn = new ButtonColumn(table, delete,3);
		        buttonColumn.setMnemonic(KeyEvent.VK_D);
	}

	
	
private String [] getTableHeaders(){
	return   new String[] {
            "Nome","Pais","Dcria","ACCAO"
        };
	
}	
private Object [][] getTableData(){
	
	List<Equipas>  list = new ArrayList<>();
	list=Equipas.listAll();
	 Object[][] data = new Object[list.size()][4];
	 
	 for (int i = 0; i < list.size(); i++) {
		 int j=0;
		 
		 data[i][j++]=list.get(i).getNome();
		 data[i][j++]=list.get(i).getPais();
		 data[i][j++]=list.get(i).getDcria();
		 
		 
		 
		 
	}
	return data;
}
}



