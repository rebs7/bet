package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import main.entities.Equipas;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Teste extends JFrame {

	private JPanel contentPane;
	 private JTable table;
	 static Teste frame;
     DefaultTableModel model;
     private JTextField textField;
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

		
		        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		         getContentPane().setLayout(null);
		          model = new DefaultTableModel(getTableData(Equipas.listAll()), getTableHeaders());
		        table = new JTable(model );
		        table.getColumn("ACCAO").setCellRenderer(new ButtonRenderer());
		     
		        table.getColumn("ACCAO").setCellEditor(new ButtonEditor(new JCheckBox()));
		        JScrollPane scrollPane = new JScrollPane(table);
		        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		        scrollPane.setBounds(10, 121, 1564, 731);
		        getContentPane().add(scrollPane);
		        
		        JLabel lblNome = new JLabel("Nome");
		        lblNome.setBounds(57, 54, 46, 14);
		        getContentPane().add(lblNome);
		        
		        textField = new JTextField();
		        textField.setBounds(166, 51, 113, 20);
		        getContentPane().add(textField);
		        textField.setColumns(10);
		        
		        JLabel lblVlido = new JLabel("V\u00E1lido");
		        lblVlido.setBounds(370, 54, 46, 14);
		        getContentPane().add(lblVlido);
		        
		        JComboBox comboBox = new JComboBox();
		        comboBox.setBounds(438, 51, 103, 20);
		        comboBox.addItem("SIM");
		        comboBox.addItem("NAO");
		        getContentPane().add(comboBox);
		        
		        JButton btnPesquisar = new JButton("Pesquisar");
		        btnPesquisar.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		
		        		  model = new DefaultTableModel(getTableData(Equipas.getAll()), getTableHeaders());
		        		  model.fireTableDataChanged();
		        	      table.setModel(model);
		        	      table.getColumn("ACCAO").setCellRenderer(new ButtonRenderer());
		        		     
		        	      table.getColumn("ACCAO").setCellEditor(new ButtonEditor(new JCheckBox()));
		        	  
		        		
		        	}
		        });
		        btnPesquisar.setBounds(1088, 50, 89, 23);
		        getContentPane().add(btnPesquisar);
		        
		      
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



