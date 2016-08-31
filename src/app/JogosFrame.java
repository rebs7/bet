package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import main.entities.Equipas;
import main.entities.Jogos;
import main.utils.Utils;

public class JogosFrame extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel contentPane;
	 DefaultTableModel model;
	 private JLabel lblCasa;
	 private JComboBox<String> comboBox;
	 private JLabel lblFora;
	 private JComboBox<String> comboBox_1;
	 private JLabel lblEstado;
	 private JComboBox<String> comboBox_2;
	 private JButton btnPesquisar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JogosFrame frame = new JogosFrame();
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
	public JogosFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		         getContentPane().setLayout(null);
		         model = new DefaultTableModel(getTableData(Jogos.search(null, null, null)), getTableHeaders());
			        table = new JTable(model );
		contentPane = new JPanel();
		
	
		setContentPane(contentPane);
		  contentPane.setLayout(null);
		  JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
	        scrollPane.setBounds(10, 109, 1564, 743);
	        getContentPane().add(scrollPane);
	        
	        lblCasa = new JLabel("Casa");
	        lblCasa.setBounds(55, 49, 74, 14);
	        contentPane.add(lblCasa);
	        
	        comboBox = new JComboBox<String>();
	        comboBox.setBounds(161, 46, 147, 20);
	        contentPane.add(comboBox);
	        
	        lblFora = new JLabel("Fora");
	        lblFora.setBounds(353, 49, 46, 14);
	        contentPane.add(lblFora);
	        
	        comboBox_1 = new JComboBox<String>();
	        comboBox_1.setBounds(408, 48, 147, 20);
	        contentPane.add(comboBox_1);
	        
	        lblEstado = new JLabel("Estado");
	        lblEstado.setBounds(594, 49, 46, 14);
	        contentPane.add(lblEstado);
	        
	        comboBox_2 = new JComboBox<String>();
	        comboBox_2.setBounds(676, 46, 147, 20);
	        contentPane.add(comboBox_2);
	        
	        btnPesquisar = new JButton("Pesquisar");
	        btnPesquisar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        		model = new DefaultTableModel(getTableData(Jogos.search((String) comboBox.getSelectedItem(), (String)comboBox_1.getSelectedItem(),(String)comboBox_2.getSelectedItem())), getTableHeaders());
	        		  model.fireTableDataChanged();
	        	      table.setModel(model);
	        	}
	        });
	        btnPesquisar.setBounds(1039, 45, 89, 23);
	        contentPane.add(btnPesquisar);
	        comboBox.addItem("");
	        comboBox_1.addItem("");
	    	ArrayList<String> listaEquipas = new ArrayList<>();
			listaEquipas = (ArrayList<String>) Equipas.getEquipas();
			for (int i = 0; i < listaEquipas.size(); i++) {
				comboBox.addItem(listaEquipas.get(i));
				comboBox_1.addItem(listaEquipas.get(i));

			}
			   try {
					String sql ="";
					Connection conn = null;
					PreparedStatement stmt = null;
					Class.forName(Utils.JDBC_DRIVER);
					conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
					sql = "select distinct(estado) from jogos";
					stmt = conn.prepareStatement(sql);
					comboBox_2.addItem("");
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
					
						comboBox_2.addItem(rs.getString("estado"));
				
						
					}
					
				}catch (Exception e) {
				}
	}
	
	private String [] getTableHeaders(){
		return   new String[] {
	            "Casa","Fora","Data","Resultado", "Odds", "Estado","Comepticao","Data Criação","Data Alteração"
	        };
		
	}	
	private Object [][] getTableData(List<Jogos> lista){
		
		List<Jogos>  list = new ArrayList<>();
		list=lista;
		 Object[][] data = new Object[list.size()][9];
		 
		 for (int i = 0; i < list.size(); i++) {
			 int j=0;
			 
			 data[i][j++]=list.get(i).getId().getCasa();
			 data[i][j++]=list.get(i).getId().getFora();
			 data[i][j++]=list.get(i).getDataReal();
			 data[i][j++]=list.get(i).getGolos1()+" - "+list.get(i).getGolos2();
			 data[i][j++]=list.get(i).getOdds1()+" / "+ list.get(i).getOddsx()+" / "+list.get(i).getOdds2();
			 data[i][j++]=list.get(i).getEstado();
			 data[i][j++]=list.get(i).getCompeticao();
			 data[i][j++]=list.get(i).getDcria();
			 data[i][j++]=list.get(i).getDaltera();
			 
			 
			 
		}
		return data;
	}

}
