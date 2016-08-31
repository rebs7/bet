package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.in.Algoritmo;
import main.in.FootballCSV;
import main.in.ZeroZeroLastResults;
import main.in.ZeroZeroNextGames;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		getContentPane().setLayout(null);
		setBounds(100, 100, 1600, 900);
		JButton btnNewButton = new JButton("Run Next Games");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Thread(new ZeroZeroNextGames())).start();
			}
		});
		btnNewButton.setBounds(125, 101, 211, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnRunPreviousGames = new JButton("Run Previous Games");
		btnRunPreviousGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Thread(new ZeroZeroLastResults())).start();
			}
		});
		btnRunPreviousGames.setBounds(125, 150, 211, 23);
		getContentPane().add(btnRunPreviousGames);
		
		JButton button = new JButton("Run CSV");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Thread(new FootballCSV())).start();
			}
		});
		button.setBounds(125, 202, 211, 23);
		getContentPane().add(button);
		
		JButton btnJogos = new JButton("Jogos");
		btnJogos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JogosFrame  teste =new JogosFrame();
				teste.setVisible(true);
			}
		});
		btnJogos.setBounds(547, 101, 89, 23);
		getContentPane().add(btnJogos);
		
		JButton btnEquipas = new JButton("Equipas");
		btnEquipas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teste  teste =new Teste();
				teste.setVisible(true);
			
			}
		});
		btnEquipas.setBounds(547, 150, 89, 23);
		getContentPane().add(btnEquipas);
		
		JButton button_1 = new JButton("Apostas");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlgoritmoFrame  teste =new AlgoritmoFrame();
				teste.setVisible(true);
			}
		});
		button_1.setBounds(547, 202, 89, 23);
		getContentPane().add(button_1);
		
		 
		       		
	
		
	}
}
