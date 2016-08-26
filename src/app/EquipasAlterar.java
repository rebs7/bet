package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.entities.Equipas;
import main.utils.Utils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EquipasAlterar extends JFrame {

	private JPanel contentPane;
	static EquipasAlterar frame;
	private JTextField searchField;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public EquipasAlterar(String nome) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1174, 836);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEquipa = new JLabel(nome);
		lblEquipa.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipa.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEquipa.setBounds(308, 59, 493, 81);
		contentPane.add(lblEquipa);
		
		JComboBox comboBox = new JComboBox();
		ArrayList<String> listaEquipas = new ArrayList<>();
		listaEquipas = (ArrayList<String>) Equipas.getEquipas();
		for (int i = 0; i < listaEquipas.size(); i++) {
			comboBox.addItem(listaEquipas.get(i));
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBox.setBounds(533, 157, 417, 20);
		contentPane.add(comboBox);
		
		
		
		JButton changeButton = new JButton("Alterar");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	if(		JOptionPane.showConfirmDialog(null, "Deseja remover a equipa "+comboBox.getItemAt(comboBox.getSelectedIndex()) +" alterar pela equipa "+ nome)==JOptionPane.OK_OPTION){
		
		try {
			String sql ="";
			Connection conn = null;
			PreparedStatement stmt = null;
			Class.forName(Utils.JDBC_DRIVER);
			conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
			sql = "DELETE FROM EQUIPAS WHERE nome = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, (String) comboBox.getItemAt(comboBox.getSelectedIndex()));
			stmt.executeUpdate();
			
			sql = "UPDATE  EQUIPAS SET valido= 'SIM', daltera= now() , user='MAN' where nome=? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.executeUpdate();
			
			sql = "UPDATE  Jogos SET casa=?,daltera= now() where casa=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, (String) comboBox.getItemAt(comboBox.getSelectedIndex()));
	stmt.executeUpdate();
			
			
			sql = "UPDATE  Jogos SET fora=?,daltera= now() where fora=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, (String) comboBox.getItemAt(comboBox.getSelectedIndex()));
		stmt.executeUpdate();
		
			conn.close();
			

		} catch (Exception s) {
			s.printStackTrace();
		}
		
		
		
	};
			
			}
		});
		changeButton.setBounds(978, 153, 108, 29);
		contentPane.add(changeButton);
		
		searchField = new JTextField();
		
		
		searchField.setBounds(122, 157, 218, 20);
		searchField.setText(nome);
		contentPane.add(searchField);
		searchField.setColumns(10);
		JTextArea textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			JOptionPane.showMessageDialog(null, 	textArea.getText());
			}
		});
		textArea.setBounds(74, 243, 449, 469);
		contentPane.add(textArea);

		
		JButton srchbutton = new JButton("Procurar");
		srchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> listaSearch = new ArrayList<>();
				listaSearch= (ArrayList<String>) Equipas.searchLike(searchField.getText());
				String a="";
				for (int i = 0; i < listaSearch.size(); i++) {
				a=a+listaSearch.get(i)+"\n";
				}
				textArea.setText(a);
			}
		});
		srchbutton.setBounds(376, 156, 89, 23);
		contentPane.add(srchbutton);
		
			
		
	}
}
