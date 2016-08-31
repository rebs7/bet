package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import main.in.Algoritmo;

public class AlgoritmoFrame extends JFrame {

	private JTable table;
	private JPanel contentPane;
	 DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlgoritmoFrame frame = new AlgoritmoFrame();
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
	public AlgoritmoFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		         getContentPane().setLayout(null);
		         model = new DefaultTableModel(getTableData(Algoritmo.getList()), getTableHeaders());
			        table = new JTable(model );
		contentPane = new JPanel();
		
	
		setContentPane(contentPane);
		  contentPane.setLayout(null);
		  JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
	        scrollPane.setBounds(10, 109, 1564, 743);
	        getContentPane().add(scrollPane);
	        
	}
	
	private String [] getTableHeaders(){
		return   new String[] {
	            "Jogo","Odds Casa","PA Casa","PO Casa","Odds Empate","PA Empate","PO Empate","Odds Fora","PA Fora","PO Fora"
	        };
		
	}	
	private Object [][] getTableData(List<Algoritmo> lista){
		
		List<Algoritmo>  list = new ArrayList<>();
		list=lista;
		 Object[][] data = new Object[list.size()][10];
		 
		 for (int i = 0; i < list.size(); i++) {
			 int j=0;
			 
			 data[i][j++]=list.get(i).getHistory().getJogo();
			 
			 data[i][j++]=list.get(i).getHistory().getJogo().getOdds1();
			 data[i][j++]=list.get(i).getProbCasa()*100;
			 data[i][j++]=1/list.get(i).getHistory().getJogo().getOdds1()*100;

			 data[i][j++]=list.get(i).getHistory().getJogo().getOddsx();
			 data[i][j++]=list.get(i).getProbx()*100;
			 data[i][j++]=1/list.get(i).getHistory().getJogo().getOddsx()*100;
			 
			 data[i][j++]=list.get(i).getHistory().getJogo().getOdds2();
			 data[i][j++]=list.get(i).getProbFora()*100;
			 data[i][j++]=1/list.get(i).getHistory().getJogo().getOdds2()*100;
			 
	
			 
			 
			 
		}
		return data;
	}

}
