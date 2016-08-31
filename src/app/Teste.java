package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import main.entities.Equipas;
import main.utils.Utils;

public class Teste extends JFrame {

	private JPanel contentPane;
	 private JTable table;
	 static Teste frame;
     DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new Teste();
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

		
		        
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		         getContentPane().setLayout(null);
		         JComboBox<String> comboBox_1 = new JComboBox<String>();
		          model = new DefaultTableModel(getTableData(Equipas.listAll()), getTableHeaders());
		        table = new JTable(model );
		        table.getColumn("ACCAO").setCellRenderer(new ButtonRenderer());
		     
		        table.getColumn("ACCAO").setCellEditor(new ButtonEditor(new JCheckBox()));
		        JScrollPane scrollPane = new JScrollPane(table);
		        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		        scrollPane.setBounds(10, 121, 1564, 731);
		        getContentPane().add(scrollPane);
		        
		        JLabel lblVlido = new JLabel("V\u00E1lido");
		        lblVlido.setBounds(370, 54, 46, 14);
		        getContentPane().add(lblVlido);
		        
		        JComboBox<String> comboBox = new JComboBox<String>();
		        comboBox.setBounds(438, 51, 103, 20);
		        comboBox.addItem("");
		        comboBox.addItem("SIM");
		        comboBox.addItem("NAO");
		        getContentPane().add(comboBox);
		        
		        JButton btnPesquisar = new JButton("Pesquisar");
		        btnPesquisar.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		
		        	/*	  model = new DefaultTableModel(getTableData(Equipas.getAll()), getTableHeaders());
		        		  model.fireTableDataChanged();
		        	      table.setModel(model);
		        	      table.getColumn("ACCAO").setCellRenderer(new ButtonRenderer());
		        		     
		        	      table.getColumn("ACCAO").setCellEditor(new ButtonEditor(new JCheckBox()));
		        	      */
		        		
		        		model = new DefaultTableModel(getTableData(Equipas.search(null, null,(String) comboBox_1.getSelectedItem(), (String)comboBox.getSelectedItem())), getTableHeaders());
		        		  model.fireTableDataChanged();
		        	      table.setModel(model);
		        	      table.getColumn("ACCAO").setCellRenderer(new ButtonRenderer());
		        		     
		        	      table.getColumn("ACCAO").setCellEditor(new ButtonEditor(new JCheckBox()));
		        		
		        	}
		        });
		        btnPesquisar.setBounds(1088, 50, 89, 23);
		        getContentPane().add(btnPesquisar);
		        
		        JLabel lblUser = new JLabel("User");
		        lblUser.setBounds(30, 54, 46, 14);
		        getContentPane().add(lblUser);
		        
		      
		        try {
					String sql ="";
					Connection conn = null;
					PreparedStatement stmt = null;
					Class.forName(Utils.JDBC_DRIVER);
					conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
					sql = "select distinct(user) from equipas";
					stmt = conn.prepareStatement(sql);
					comboBox_1.addItem("");
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
					
						comboBox_1.addItem(rs.getString("user"));
				
						
					}
					
				}catch (Exception e) {
				}
		        comboBox_1.setBounds(104, 51, 103, 20);
		        getContentPane().add(comboBox_1);
		        
		      
	}

	
	
	
	
private String [] getTableHeaders(){
	return   new String[] {
            "Nome","Pais","Data Criação","Data Alteração","User","Válido","ACCAO"
        };
	
}	
private Object [][] getTableData(List<Equipas> lista){
	
	List<Equipas>  list = new ArrayList<>();
	list=lista;
	 Object[][] data = new Object[list.size()][7];
	 
	 for (int i = 0; i < list.size(); i++) {
		 int j=0;
		 
		 data[i][j++]=list.get(i).getNome();
		 data[i][j++]=list.get(i).getPais();
		 data[i][j++]=list.get(i).getDcria();
		 data[i][j++]=list.get(i).getDaltera();
		 data[i][j++]=list.get(i).getUser();
		 data[i][j++]=list.get(i).getValido();
		 
		 
		 
	}
	return data;
}

class ButtonRenderer extends JButton implements TableCellRenderer {

	  public ButtonRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}

	/**
	 * @version 1.0 11/09/98
	 */

	class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;

	  private String label;

	  private boolean isPushed;

	  public ButtonEditor(JCheckBox checkBox) {
		   
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  String a ="";
		        if(table.getSelectedRow()>=0 ){
		        	
		        	a=(String) model.getValueAt(table.getSelectedRow(), 0);
		        	 
		        }
		        EquipasAlterar frame = new EquipasAlterar(a);
		        frame.setFrame(frame);
	  			frame.setVisible(true);


frame.addWindowListener(new WindowAdapter() {
  public void windowClosing(WindowEvent e) {
	  model = new DefaultTableModel(getTableData(Equipas.getAll()), getTableHeaders());
	  model.fireTableDataChanged();
      table.setModel(model);
      table.getColumn("ACCAO").setCellRenderer(new ButtonRenderer());
	     
      table.getColumn("ACCAO").setCellEditor(new ButtonEditor(new JCheckBox()));

  }
});
	      }
	    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value == null) ? "" : value.toString();
	    button.setText(label);
	    isPushed = true;
	    return button;
	  }

	  public Object getCellEditorValue() {
	    if (isPushed) {
	      frame.dispose();
	      // 
	    
	      JOptionPane.showMessageDialog(button, label + ": Ouch!");
	      // System.out.println(label + ": Ouch!");
	    }
	    isPushed = false;
	    return new String(label);
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	}
}



